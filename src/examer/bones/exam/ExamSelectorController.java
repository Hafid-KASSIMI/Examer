/*
 * Copyright (C) 2020 Sicut
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
package examer.bones.exam;

import static examer.Settings.*;
import examer.db.Exam;
import examer.util.Refreshable;
import examer.util.Deleter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Hafid KASSIMI
 */
public class ExamSelectorController extends Refreshable implements Initializable {

    @FXML private Button proceedBtn, createBtn, deleteBtn, refreshBtn, updateBtn, doUpdateBtn;
    @FXML private TextField examTF, sessionTF, levelTF;
    @FXML private ComboBox<Exam> examsCMB;

    public ExamSelectorController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter deleter = new Deleter(deleteBtn, b -> setDisable(b));

        examsCMB.getSelectionModel().selectedItemProperty().addListener((obs, old, cur) -> {
            hide(doUpdateBtn, true);
            deleteBtn.setDisable(cur == null);
            updateBtn.setDisable(cur == null);
            proceedBtn.setDisable(cur == null);
        });
        
        deleteBtn.setOnAction(evt -> {
            if ( !examsCMB.getSelectionModel().isEmpty() ) {
               if ( deleter.isDone() )
                    deleter.start();
                else {
                    int id = examsCMB.getSelectionModel().getSelectedItem().getIdexam();
                    deleter.stop();
                    EXAM.setIdexam(id);
                    if ( EXAM.delete() ) {
                        BRANCH.deleteAll(id);
                        PLAN.deleteAll(id);
                        PLMB.deleteAll(id);
                        ROOM.listExamRooms(id).forEach(room -> {
                            CHEATER.deleteAll(room.getIdroom());
                            ABSENT.deleteAll(room.getIdroom());
                            CANDIDATE.cleanRoom(room.getIdroom());
                        });
                        ROOM.deleteAll(id);
                        EXAM_BRANCH.cleanExam(id);
                        refresh();
                    }
                }
            }
        });
        
        updateBtn.setOnAction(evt -> {
            EXAM.clone(examsCMB.getSelectionModel().getSelectedItem());
            examTF.setText(EXAM.getLabel());
            sessionTF.setText(EXAM.getExamSession());
            levelTF.setText(EXAM.getExamLevel());
            hide(doUpdateBtn, false);
        });
        
        doUpdateBtn.setOnAction(evt -> {
            if ( !examTF.getText().isEmpty() && !sessionTF.getText().isEmpty() && !levelTF.getText().isEmpty() ) {
                EXAM.setLabel(examTF.getText());
                EXAM.setExamSession(sessionTF.getText());
                EXAM.setExamLevel(levelTF.getText());
                if ( EXAM.count() == 0 ) {
                    EXAM.setIdexam(EXAM.nextIdexam());
                    if ( EXAM.update() ) {
                        examTF.setText("");
                        sessionTF.setText("");
                        levelTF.setText("");
                        hide(doUpdateBtn, true);
                    }
                }
            }
        });
        
        doUpdateBtn.visibleProperty().addListener((o, old, cur) -> {
            hide(createBtn, cur);
        });
        
        proceedBtn.setOnAction(evt -> {
            if ( !examsCMB.getSelectionModel().isEmpty() ) {
                EXAM.clone(examsCMB.getSelectionModel().getSelectedItem());
                ((SimpleStringProperty) proceedBtn.getScene().getUserData()).set("MAIN_WINDOW");
            }
        });
        
        createBtn.setOnAction(evt -> {
            EXAM.setIdexam(EXAM.nextIdexam());
            EXAM.setLabel(examTF.getText());
            EXAM.setExamSession(sessionTF.getText());
                EXAM.setExamLevel(levelTF.getText());
            if ( EXAM.save() ) {
                examTF.setText("");
                sessionTF.setText("");
                levelTF.setText("");
                refresh();
            }
        });
        
        refreshBtn.setOnAction(evt -> {
            refresh();
        });
        
        refresh();
        
    }    

    private void setDisable(Boolean b) {
        examsCMB.setDisable(b);
        proceedBtn.setDisable(b);
        createBtn.setDisable(b);
        refreshBtn.setDisable(b);
        updateBtn.setDisable(b);
        doUpdateBtn.setDisable(b);
    }
    
    @Override
    public void refresh() {
        examsCMB.getItems().clear();
        examsCMB.getItems().addAll(EXAM.list());
        examsCMB.getSelectionModel().select(null);
        examsCMB.requestFocus();
        hide(doUpdateBtn, true);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        proceedBtn.setDisable(true);
    }
    
    private void hide(Node n, boolean b) {
        n.setVisible(!b);
        n.setManaged(!b);
    }

    @Override
    public void forceRefresh() {
        refresh();
    }
}
