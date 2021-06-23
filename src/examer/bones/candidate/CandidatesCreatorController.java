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

import examer.util.Refreshable;
import examer.Settings;
import static examer.Settings.*;
import examer.bones.candidate.CandidateInfosRow;
import examer.db.Branch;
import examer.db.Candidate;
import examer.db.Room;
import examer.util.Deleter;
import examer.util.IntervalSpinners;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class CandidatesCreatorController extends Refreshable implements Initializable {

    @FXML private ComboBox<Branch> branchesCMB;
    @FXML private Button refreshBranchesBtn, refreshRoomsBtn, cleanBtn, generateBtn, deleteBtn, saveBtn;
    @FXML private ComboBox<Room> roomsCMB;
    @FXML private Spinner<Integer> firstCodeSp, lastCodeSp;
    @FXML private VBox candsInfosVB, candsListVB;
    @FXML private ToggleButton maleTB, femaleTB, undefinedTB, englishTB, spanishTB, italianTB, germanTB;
    @FXML private ToggleGroup genderTG, languageTG;
    
    private final ArrayList<CandidateInfosRow> cands;
    private int idRoom, idBranch;
    private final ArrayList<ToggleButton> langTBs;


    public CandidatesCreatorController() {
        cands = new ArrayList();
        langTBs = new ArrayList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter deleter = new Deleter(deleteBtn, b -> setDisableButDelete(b));
        Deleter cleaner = new Deleter(cleanBtn, b -> setDisableButClean(b));
        
        new IntervalSpinners(firstCodeSp, lastCodeSp).bind();
        
        candsInfosVB.visibleProperty().bindBidirectional(candsInfosVB.managedProperty());
        candsInfosVB.setVisible(false);
        
        branchesCMB.getSelectionModel().selectedItemProperty().addListener((evt, old, cur) -> {
            if ( cur != null ) {
                idBranch = cur.getIdbranch();
                refreshRooms();
            }
        });
        
        roomsCMB.getSelectionModel().selectedItemProperty().addListener((evt, old, cur) -> {
            if ( cur != null )
                idRoom = cur.getIdroom();
            candsInfosVB.setVisible(false);
        });
        
        cleanBtn.setOnAction(evt -> {
            if ( !roomsCMB.getSelectionModel().isEmpty() ) {
               if ( cleaner.isDone() )
                    cleaner.start();
                else {
                    cleaner.stop();
                    CANDIDATE.setIdroom(idRoom);
                    CANDIDATE.deleteAll();
                    refreshRooms();
                }
            }
        });
        
        deleteBtn.setOnAction(evt -> {
            if ( !roomsCMB.getSelectionModel().isEmpty() ) {
                if ( deleter.isDone() )
                    deleter.start();
                else {
                    deleter.stop();
                    CANDIDATE.setIdroom(idRoom);
                    ROOM.setIdroom(idRoom);
                    CANDIDATE.deleteAll();
                    if ( ROOM.delete() )
                        refreshRooms();
                }
            }
        });
        
        generateBtn.setOnAction(evt -> {
            if ( roomsCMB.getSelectionModel().isEmpty() ) {
                return;
            }
            int id = CANDIDATE.nextIdcandidate();
            candsListVB.getChildren().clear();
            cands.clear();
            for ( int i = firstCodeSp.getValue(), n = lastCodeSp.getValue() + 1, j = 0; i < n; i++, j++ ) {
                CandidateInfosRow row = new CandidateInfosRow();
                if ( row.isLoaded() ) {
                    row.getRowController().setCandidate(new Candidate(id + j, i, "M", 0, idRoom));
                    candsListVB.getChildren().add(row.getRowHBox());
                    cands.add(row);
                    row.getRowController().getRowDeleted().addListener((obs, old, cur) -> {
                        if ( cur ) {
                            candsListVB.getChildren().remove(row.getRowHBox());
                            cands.remove(row);
                        }
                    });
                }
            }
            genderTG.selectToggle(null);
            languageTG.selectToggle(null);
            candsInfosVB.setVisible(true);
        });
        
        saveBtn.setOnAction(evt -> {
            setDisable(true);
            new Thread(() -> {
                cands.forEach(c -> {
                    CANDIDATE.clone(c.getRowController().getCandidate());
                    CANDIDATE.save();
                });
                Platform.runLater(() -> {
                    int size = lastCodeSp.getValue() - firstCodeSp.getValue() + 1;
                    lastCodeSp.getValueFactory().increment(size);
                    firstCodeSp.getValueFactory().increment(size);
                    refreshRooms();
                    setDisable(false);
                    roomsCMB.getSelectionModel().selectNext();
                    
                });
            }).start();
        });
        
        refreshRoomsBtn.setOnAction(evt -> {
            refreshRooms();
        });
        
        refreshBranchesBtn.setOnAction(evt -> {
            refreshBranches();
        });
        
        langTBs.addAll(Arrays.asList(undefinedTB, englishTB, spanishTB, italianTB, germanTB));
        genderTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            if ( cur != null ) {
                cands.forEach(c -> {
                    c.getRowController().setGender(cur == femaleTB ? "F" : "M");
                });
            }
        });
        languageTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            if ( cur != null ) {
                cands.forEach(c -> {
                    c.getRowController().setLanguage(langTBs.indexOf(cur));
                });
            }
        });
    }

    private void refreshRooms() {
        int index = roomsCMB.getSelectionModel().getSelectedIndex();
        ROOM.setIdbranch(idBranch);
        roomsCMB.getItems().clear();
        ROOM.listBranchRooms(EXAM.getIdexam()).forEach(room -> {
            CANDIDATE.setIdroom(room.getIdroom());
            room.setCustomString(Settings.I18N_BUNDLE.getString("ROOM") + " " + room.stringifyNumero() + getCandidatesCountString(CANDIDATE.count()));
            roomsCMB.getItems().add(room);
        });
        roomsCMB.getSelectionModel().select(index);
        candsInfosVB.setVisible(false);
    }

    private void refreshBranches() {
        branchesCMB.getItems().setAll(BRANCH.list(EXAM.getIdexam(), true));
        branchesCMB.show();
        candsInfosVB.setVisible(false);
    }
    
    public void setDisable(Boolean disable) {
        branchesCMB.setDisable(disable);
        refreshBranchesBtn.setDisable(disable);
        roomsCMB.setDisable(disable);
        refreshRoomsBtn.setDisable(disable);
        firstCodeSp.setDisable(disable);
        deleteBtn.setDisable(disable);
        cleanBtn.setDisable(disable);
        lastCodeSp.setDisable(disable);
        generateBtn.setDisable(disable);
        candsInfosVB.setDisable(disable);
    }
    
    public void setDisableButClean(Boolean disable) {
        setDisable(disable);
        cleanBtn.setDisable(false);
    }
    
    public void setDisableButDelete(Boolean disable) {
        setDisable(disable);
        deleteBtn.setDisable(false);
    }
    
    private String getCandidatesCountString(int n) {
        String tmp = "";
        if ( n == 0 )
            return tmp;
        if ( n == 1 )
            tmp = "ONE_CANDIDATE";
        else if ( n == 2 )
            tmp = "TWO_CANDIDATES";
        else if ( n > 11 )
            tmp = "ABOVE_10_CANDIDATES";
        else
            tmp = "3_TO_10_CANDIDATES";
        return String.format(" (" + Settings.I18N_BUNDLE.getString(tmp)  + ")", n);
    }

    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        
    }
    
}
