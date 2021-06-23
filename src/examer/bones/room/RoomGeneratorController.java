/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examer.bones.room;

import examer.Settings;
import static examer.Settings.*;
import examer.db.Branch;
import examer.util.Deleter;
import examer.util.IntervalSpinners;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

/**
 * FXML Controller class
 *
 * @author Hafid KASSIMI
 */
public class RoomGeneratorController implements Initializable {

    @FXML
    private ComboBox<Branch> roomsBranchCMB;
    @FXML
    private Button roomsBranchesBtn;
    @FXML
    private Button cleanBranchRoomsBtn;
    @FXML
    private Spinner<Integer> roomsStartSp;
    @FXML
    private Spinner<Integer> roomsEndSp;
    @FXML
    private Button generateRoomsBtn;
    
    private Deleter deleter;

    public RoomGeneratorController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new IntervalSpinners(roomsStartSp, roomsEndSp).bind();
        deleter = new Deleter(cleanBranchRoomsBtn, b -> setDisableAllButClean(b));
        
        roomsBranchesBtn.setOnAction(evt -> {
            refresh();
        });
        
        cleanBranchRoomsBtn.setOnAction(evt -> {
            if ( !roomsBranchCMB.getSelectionModel().isEmpty() ) {
               if ( deleter.isDone() )
                    deleter.start();
                else {
                    deleter.stop();
                    ROOM.setIdbranch(roomsBranchCMB.getSelectionModel().getSelectedItem().getIdbranch());
                    if ( ROOM.cleanExamBranch(EXAM.getIdexam()) )
                        refresh();
                }
            }
        });
        
        generateRoomsBtn.setOnAction(evt -> {
            if ( roomsBranchCMB.getSelectionModel().isEmpty() ) {
                return;
            }
            setDisable(true);
            cleanBranchRoomsBtn.setDisable(true);
            int idBranch = roomsBranchCMB.getSelectionModel().getSelectedItem().getIdbranch();
            int id = ROOM.nextIdroom();
            new Thread(() -> {
                Boolean done;
                ROOM.connect();
                ROOM.setIdbranch(idBranch);
                for ( int i = roomsStartSp.getValue(), n = roomsEndSp.getValue() + 1, j = 0; i < n; i++, j++ ) {
                    ROOM.setIdroom(id + j);
                    ROOM.setNumero(i);
                    ROOM.batchSave();
                }
                done = ROOM.runBatch();
                ROOM.close();
                Platform.runLater(() -> {
                    if ( done ) {
                        refresh();
                    }
                    setDisable(false);
                });
            }).start();
        });
        
        Platform.runLater(() -> {
            refresh();
        });
    }
    
    private void setDisableAllButClean(Boolean disable) {
        roomsBranchCMB.setDisable(disable);
        roomsBranchesBtn.setDisable(disable);
        roomsStartSp.setDisable(disable);
        roomsEndSp.setDisable(disable);
        generateRoomsBtn.setDisable(disable);
    }
    
    private void setDisable(Boolean disable) {
        setDisableAllButClean(disable);
        cleanBranchRoomsBtn.setDisable(disable);
    }
    
    private void refresh() {
        int index = roomsBranchCMB.getSelectionModel().getSelectedIndex();
        roomsBranchCMB.getItems().clear();
        BRANCH.list(EXAM.getIdexam()).forEach( item -> {
            ROOM.setIdbranch(item.getIdbranch());
            item.setLabel(item.getLabel() + getRoomsCountString(ROOM.getExamBranchRoomsCount(EXAM.getIdexam())));
            roomsBranchCMB.getItems().add(item);
        });
        roomsBranchCMB.getSelectionModel().select(index);
    }
    
    private String getRoomsCountString(int n) {
        String tmp = "";
        if ( n == 0 )
            return tmp;
        if ( n == 1 )
            tmp = "ONE_ROOM";
        else if ( n == 2 )
            tmp = "TWO_ROOMS";
        else if ( n > 11 )
            tmp = "ABOVE_10_ROOMS";
        else
            tmp = "3_TO_10_ROOMS";
        return String.format(" (" + Settings.I18N_BUNDLE.getString(tmp)  + ")", n);
    }
    
}
