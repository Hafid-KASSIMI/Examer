/*
 * Copyright (C) 2021 Sicut
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package examer.bones.candidate;

import static examer.Settings.ABSENT;
import static examer.Settings.CANDIDATE;
import static examer.Settings.EXAM;
import static examer.Settings.EXAM_BRANCH;
import static examer.Settings.ROOM;
import static examer.Settings.VL_TABLE;
import examer.bones.branch.BranchesHelper;
import examer.bones.matter.MattersHelper;
import examer.db.Branch;
import examer.db.Candidate;
import examer.db.Matter;
import examer.db.Room;
import examer.db.SpecialCandidate;
import examer.util.Deleter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class SpecialCandidateController implements Initializable {

    @FXML private ComboBox<Room> roomsCMB;
    @FXML private ComboBox<Matter> mattersCMB;
    @FXML private ComboBox<Branch> branchesCMB;
    @FXML private Button displayBtn, saveBtn, cleanBtn, refreshMattersBtn, refreshBranchesBtn;
    @FXML private VBox candsVB;
    @FXML private CheckBox selectAllCB, selectPointedCB;
    @FXML private FlowPane candsFP;
    @FXML private Label sumLbl, sumMLbl, sumFLbl, titleLbl, checkTitleLbl;
    
    private SpecialCandidate specialCand;
    private final ArrayList<CustomCheckBox<Candidate>> cands, pointedCands;
    private int idMatter;
    private final SimpleIntegerProperty sum, male, female;
    private MattersHelper mh;
    private BranchesHelper bh;
    
    public SpecialCandidateController() {
        cands = new ArrayList();
        pointedCands = new ArrayList();
        sum = new SimpleIntegerProperty(0);
        male = new SimpleIntegerProperty(0);
        female = new SimpleIntegerProperty(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter cleaner = new Deleter(cleanBtn, b -> setDisable(b));
        mh = new MattersHelper(mattersCMB);
        bh = new BranchesHelper(branchesCMB, true);
        
        mh.setIdExam(EXAM.getIdexam());
        EXAM_BRANCH.setIdexam(EXAM.getIdexam());
        
        bh.refreshExamBranchesList();
        
        cleanBtn.setOnAction(evt -> {
            if ( !roomsCMB.getSelectionModel().isEmpty() ) {
               if ( cleaner.isDone() )
                    cleaner.start();
                else {
                    cleaner.stop();
                    specialCand.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
                    specialCand.clean(roomsCMB.getSelectionModel().getSelectedItem().getIdroom());
                }
            }
        });
        
        branchesCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            branchChanged();
        });
        
        mattersCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            if ( cur != null )
                idMatter = cur.getIdmatter();
        });
        
        roomsCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            candsVB.setVisible(false);
            displayBtn.setDisable(cur == null);
            if ( cur != null )
                CANDIDATE.setIdroom(cur.getIdroom());
        });
        
        candsVB.visibleProperty().addListener((o, old, cur) -> {
            candsVB.setManaged(cur);
        });
        
        sum.addListener((n, old, cur) -> {
            sumLbl.setText(cur + "");
        });
        
        male.addListener((n, old, cur) -> {
            sumMLbl.setText(cur + "");
        });
        
        female.addListener((n, old, cur) -> {
            sumFLbl.setText(cur + "");
        });
        
        displayBtn.setOnAction(evt -> {
            if ( roomsCMB.getSelectionModel().isEmpty() || mattersCMB.getSelectionModel().isEmpty() )
                return;
            candsFP.getChildren().clear();
            cands.clear();
            pointedCands.clear();
            selectAllCB.setSelected(false);
            selectPointedCB.setSelected(true);
            CANDIDATE.setIdroom(roomsCMB.getSelectionModel().getSelectedItem().getIdroom());
            sum.set(0);
            male.set(0);
            female.set(0);
            CANDIDATE.listRoom().forEach((Candidate cand) -> {
                if ( specialCand.countConcurrent(cand.getIdcandidate(), idMatter) <= 0 ) {
                    CustomCheckBox cb = new CustomCheckBox(new Candidate(cand.getIdcandidate(), cand.getCode(), cand.getGender(), 0, 0));
                    cb.selectedProperty().addListener((o, old, cur) -> {
                        int op = ( cur ) ? 1 : -1;
                        sum.set(sum.getValue() + op);
                        if ( cand.getGender().equals("M") )
                            male.set(male.getValue() + op);
                        else
                            female.set(female.getValue() + op);
                    });
                    cb.setSelected(specialCand.count(cand.getIdcandidate(), idMatter) > 0);
                    if ( specialCand.count(cand.getIdcandidate()) > 0 ) {
                        pointedCands.add(cb);
                        cb.setSelected(true);
                    }
                    cands.add(cb);
                }
            });
            candsFP.getChildren().addAll(cands);
            candsVB.setVisible(!cands.isEmpty());
        });
        
        saveBtn.setOnAction(evt -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    ArrayList<Integer> postMatters = VL_TABLE.getPosteriorMatters(EXAM.getIdexam(), idMatter);
                    cands.forEach(ccb -> {
                        specialCand.setIdcandidate(ccb.getObject().getIdcandidate());
                        specialCand.setIdmatter(idMatter);
                        if ( ccb.isSelected() ) {
                            specialCand.setMessage("");
                            if ( specialCand.save() && specialCand == ABSENT ) {
                                postMatters.forEach(x -> {
                                    specialCand.setIdmatter(x);
                                    specialCand.setMessage("");
                                    specialCand.save();
                                });
                            }
                        }
                        else {
                            if ( specialCand.delete() && specialCand == ABSENT ) {
                                postMatters.forEach(x -> {
                                    specialCand.setIdmatter(x);
                                    specialCand.delete();
                                });
                            }
                        }
                    });
                    candsVB.setVisible(false);
                    roomsCMB.getSelectionModel().selectNext();
                });
            }).start();
        });
        
        selectAllCB.setOnAction(evt -> {
            cands.forEach(ccb -> {
                ccb.setSelected(selectAllCB.isSelected());
            });
        });
        
        selectPointedCB.setOnAction(evt -> {
            pointedCands.forEach(ccb -> {
                ccb.setSelected(selectPointedCB.isSelected());
            });
        });
        
        refreshMattersBtn.setOnAction(evt -> {
            mh.refreshPlannedMatters();
        });
        
        refreshBranchesBtn.setOnAction(evt -> {
            bh.refreshExamBranchesList();
        });
        
        candsVB.setVisible(false);
        
    }

    public SpecialCandidate getSpecialCand() {
        return specialCand;
    }

    public void setSpecialCand(SpecialCandidate specialCand) {
        this.specialCand = specialCand;
    }

    private void setDisable(Boolean b) {
        roomsCMB.setDisable(b);
        mattersCMB.setDisable(b);
        branchesCMB.setDisable(b);
        saveBtn.setDisable(b);
        displayBtn.setDisable(b);
    }
    
    private void branchChanged() {
        Branch bra = branchesCMB.getSelectionModel().getSelectedItem();
        candsVB.setVisible(false);
        if ( bra != null ) {
            ROOM.setIdbranch(bra.getIdbranch());
            roomsCMB.getItems().clear();
            if ( bra.getIdbranch() == -1 ) {
                roomsCMB.getItems().addAll(ROOM.listExamRooms(EXAM.getIdexam()));
                mh.refreshExamPlannedMatters();
            }
            else {
                roomsCMB.getItems().addAll(ROOM.listBranchRooms(EXAM.getIdexam()));
                mh.setIdBranch(bra.getIdbranch());
                mh.refreshPlannedMatters();
            }
        }
    }
    
    public void setCheckTitle(String subTitle) {
        checkTitleLbl.setText(subTitle);
    }
    
    public void setTitle(String title) {
        titleLbl.setText(title);
    }
}
