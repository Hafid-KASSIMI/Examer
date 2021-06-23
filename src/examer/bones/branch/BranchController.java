/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examer.bones.branch;

import static examer.Settings.*;
import examer.db.Branch;
import examer.util.Deleter;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Hafid KASSIMI
 */
public class BranchController implements Initializable {

    @FXML private Button addBtn, refreshBtn, deleteBtn, refreshNotInExBtn;
    @FXML private ComboBox<Branch> currentExamBranchesCMB, allBranchesCMB;

    private BranchesHelper bhCurEx, bhNotInEx;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter del = new Deleter(deleteBtn, b -> setDisable(b));
        bhCurEx = new BranchesHelper(currentExamBranchesCMB, refreshBtn);
        bhNotInEx = new BranchesHelper(allBranchesCMB, refreshNotInExBtn);
        
        bhCurEx.bindExamBranches();
        bhNotInEx.bindNotInExamBranches();
        
        EXAM_BRANCH.setIdexam(EXAM.getIdexam());
        PLMB.setIdexam(EXAM.getIdexam());
        
        addBtn.setOnAction(evt -> {
            if ( !allBranchesCMB.getSelectionModel().isEmpty() ) {
                int id = allBranchesCMB.getSelectionModel().getSelectedItem().getIdbranch();
                EXAM_BRANCH.setIdbranch(id);
                if ( EXAM_BRANCH.count() == 0 ) {
                    EXAM_BRANCH.setStartingdate(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
                    if ( EXAM_BRANCH.save() ) {
                        refreshLists();
                        currentExamBranchesCMB.requestFocus();
                    }
                }
            }
        });
        
        deleteBtn.setOnAction(evt -> {
            if ( !currentExamBranchesCMB.getSelectionModel().isEmpty() ) {
                if ( del.isDone() )
                    del.start();
                else {
                    del.stop();
                    int id = currentExamBranchesCMB.getSelectionModel().getSelectedItem().getIdbranch();
                    EXAM_BRANCH.setIdbranch(id);
                    PLMB.setIdbranch(id);
                    if( EXAM_BRANCH.delete() ) {
                        PLMB.cleanExamBranch();
                        refreshLists();
                        currentExamBranchesCMB.requestFocus();
                    }
                }
            }
        });
        
        refreshBtn.setOnAction(evt -> {
            bhCurEx.refreshExamBranchesList();
        });
        
        refreshNotInExBtn.setOnAction(evt -> {
            bhNotInEx.refreshNotInExamBranchesList();
        });
        Platform.runLater(() -> {
            refreshLists();
        });
    }
    
    private void setDisable(Boolean disable) {
        addBtn.setDisable(disable);
        refreshBtn.setDisable(disable);
        currentExamBranchesCMB.setDisable(disable);
    }

    private void refreshLists() {
        bhCurEx.refreshExamBranchesList();
        bhNotInEx.refreshNotInExamBranchesList();
    }
    
}
