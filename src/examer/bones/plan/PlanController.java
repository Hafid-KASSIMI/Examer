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
package examer.bones.plan;

import static examer.Settings.PLMB;
import examer.bones.branch.BranchesHelper;
import examer.bones.matter.MattersHelper;
import examer.db.Branch;
import examer.db.Matter;
import examer.db.Plan;
import examer.util.Deleter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import static examer.Settings.EXAM;
import examer.util.ARDatePicker;
import javafx.application.Platform;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class PlanController implements Initializable {

    @FXML private ComboBox<Branch> branchesCMB;
    @FXML private ComboBox<Matter> mattersCMB;
    @FXML private ComboBox<Plan> plansCMB;
    @FXML private DatePicker dateDP;
    @FXML private Button updateBtn, cancelBtn, planBtn, refreshBranchesBtn, refreshMattersBtn, refreshPlansBtn;

    private PlansHelper ph;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter deleter = new Deleter(cancelBtn, b -> setDisable(b));
        ph = new PlansHelper(plansCMB, refreshPlansBtn);
        
        Platform.runLater(() -> {
            new BranchesHelper(branchesCMB, refreshBranchesBtn).bindExamBranches();
            new MattersHelper(mattersCMB, refreshMattersBtn).bind();
            ph.bind();
        });
        
        ARDatePicker.apply(dateDP);
        
        branchesCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            matBranchPlanChanged();
        });
        
        mattersCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            matBranchPlanChanged();
        });
        
        updateBtn.visibleProperty().addListener((obs, old, cur) -> {
            hide(planBtn, cur);
            hide(cancelBtn, old);
        });
        
        planBtn.setOnAction(evt -> {
            if ( validateInputs() ) {
                PLMB.setIdbranch(branchesCMB.getSelectionModel().getSelectedItem().getIdbranch());
                PLMB.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
                PLMB.setIdplan(plansCMB.getSelectionModel().getSelectedItem().getIdplan());
                PLMB.setPlandate(dateDP.getValue().format(DateTimeFormatter.ISO_DATE));
                if ( PLMB.save() ) {
                    branchesCMB.getSelectionModel().select(-1);
                    mattersCMB.getSelectionModel().select(-1);
                    plansCMB.getSelectionModel().select(-1);
                    dateDP.setValue(null);
                }
            }
        });
        
        updateBtn.setOnAction(evt -> {
            if ( validateInputs() ) {
                PLMB.setIdbranch(branchesCMB.getSelectionModel().getSelectedItem().getIdbranch());
                PLMB.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
                PLMB.setIdplan(plansCMB.getSelectionModel().getSelectedItem().getIdplan());
                PLMB.setPlandate(dateDP.getValue().format(DateTimeFormatter.ISO_DATE));
                if ( PLMB.update() ) {
                    branchesCMB.getSelectionModel().select(-1);
                    mattersCMB.getSelectionModel().select(-1);
                    plansCMB.getSelectionModel().select(-1);
                    dateDP.setValue(null);
                }
            }
        });
        
        cancelBtn.setOnAction(evt -> {
            if ( deleter.isDone() )
                deleter.start();
            else {
                deleter.stop();
                PLMB.setIdbranch(branchesCMB.getSelectionModel().getSelectedItem().getIdbranch());
                PLMB.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
                if ( PLMB.delete() ) {
                    branchesCMB.getSelectionModel().select(-1);
                    mattersCMB.getSelectionModel().select(-1);
                    plansCMB.getSelectionModel().select(-1);
                    dateDP.setValue(null);
                    hide(updateBtn, true);
                }
            }
        });
        
        hide(updateBtn, true);
        PLMB.setIdexam(EXAM.getIdexam());
    }

    private boolean validateInputs() {
        return !( branchesCMB.getSelectionModel().isEmpty() || mattersCMB.getSelectionModel().isEmpty() || plansCMB.getSelectionModel().isEmpty() || dateDP.getValue() == null );
    }

    private void hide(Node n, boolean b) {
        n.setVisible(!b);
        n.setManaged(!b);
    }
    
    private void matBranchPlanChanged() {
        Matter mat = mattersCMB.getSelectionModel().getSelectedItem();
        Branch bra = branchesCMB.getSelectionModel().getSelectedItem();
        if ( mat != null && bra != null ) {
            PLMB.setIdbranch(bra.getIdbranch());
            PLMB.setIdmatter(mat.getIdmatter());
            if ( ph.selectIdPlan(PLMB.getReloadedIdPlan()) ) {
                dateDP.setValue(LocalDate.parse(PLMB.getPlandate(), DateTimeFormatter.ISO_DATE));
                hide(updateBtn, false);
            }
            else {
                plansCMB.getSelectionModel().select(-1);
                dateDP.setValue(null);
                hide(updateBtn, true);
            }
        }
    }

    private void setDisable(Boolean b) {
        branchesCMB.setDisable(b);
        mattersCMB.setDisable(b);
        refreshMattersBtn.setDisable(b);
        refreshBranchesBtn.setDisable(b);
        updateBtn.setDisable(b);
    }
    
}
