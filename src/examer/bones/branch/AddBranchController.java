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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hafid KASSIMI
 */
public class AddBranchController implements Initializable {

    @FXML private TextField branchNameTF;
    @FXML private Button addBtn, refreshBtn, updateBtn, deleteBtn, doUpdateBtn;
    @FXML private ComboBox<Branch> branchesCMB;
    @FXML private TextField newBranchNameTF;
    @FXML private VBox updateVB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Deleter del = new Deleter(deleteBtn, b -> setDisable(b));
        BranchesHelper bh = new BranchesHelper(branchesCMB, refreshBtn);
        bh.bind();
        EXAM_BRANCH.setIdexam(EXAM.getIdexam());
        PLMB.setIdexam(EXAM.getIdexam());
        updateVB.visibleProperty().addListener((obs, old, cur) -> {
            updateVB.setManaged(cur);
        });
        updateVB.setVisible(false);
        branchesCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            updateVB.setVisible(false);
        });
        doUpdateBtn.setOnAction(evt -> {
            String name = newBranchNameTF.getText();
            BRANCH.setIdbranch(branchesCMB.getSelectionModel().getSelectedItem().getIdbranch());
            if ( !name.isEmpty() && BRANCH.isUnique(name) ) {
                BRANCH.setLabel(name);
                if ( BRANCH.update() ) {
                    updateVB.setVisible(false);
                    bh.refreshList();
                    newBranchNameTF.setText("");
                }
            }
        });
        
        updateBtn.setOnAction(evt -> {
            if ( !branchesCMB.getSelectionModel().isEmpty() ) {
                Branch b = branchesCMB.getSelectionModel().getSelectedItem();
                updateVB.setVisible(true);
                newBranchNameTF.setText(b.getLabel());
            }
        });
        
        deleteBtn.setOnAction(evt -> {
            updateVB.setVisible(false);
            if ( !branchesCMB.getSelectionModel().isEmpty() ) {
                if ( del.isDone() )
                    del.start();
                else {
                    int id = branchesCMB.getSelectionModel().getSelectedItem().getIdbranch();
                    del.stop();
                    BRANCH.setIdbranch(id);
                    CANDIDATE.cleanBranch(id);
                    PLMB.cleanBranch(id);
                    EXAM_BRANCH.cleanBranch(id);
                    ROOM.cleanBranch(id);
                    if( BRANCH.delete() )
                        bh.refreshList();
                }
            }
        });
        
        addBtn.setOnAction(evt -> {
            String name = branchNameTF.getText();
            updateVB.setVisible(false);
            if ( !name.isEmpty() && BRANCH.count(name) == 0 ) {
                BRANCH.setIdbranch(BRANCH.nextIdbranch());
                BRANCH.setLabel(name);
                if ( BRANCH.save() ) {
                    bh.refreshList();
                    branchNameTF.setText("");
                }
            }
        });
        
        refreshBtn.setOnAction(evt -> {
            updateVB.setVisible(false);
            bh.refreshList();
        });
        
        bh.refreshList();
    }
    
    private void setDisable(Boolean disable) {
        addBtn.setDisable(disable);
        refreshBtn.setDisable(disable);
        updateBtn.setDisable(disable);
        branchesCMB.setDisable(disable);
    }
    
}
