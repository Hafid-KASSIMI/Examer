/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examer.skeleton;

import examer.util.Refreshable;
import examer.Settings;
import static examer.Settings.CENTER;
import static examer.Settings.EXAM;
import examer.bones.region.RegionsHelper;
import examer.db.Center;
import examer.db.Region;
import examer.util.Deleter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class CentersController extends Refreshable implements Initializable {

    @FXML private Button chooseBtn, createBtn, deleteBtn, refreshBtn, updateBtn, doUpdateBtn;
    @FXML private TextField directionTF, nameTF;
    @FXML private ComboBox<Center> centersCMB;
    @FXML private ComboBox<Region> acadsCMB;

    public CentersController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter deleter = new Deleter(deleteBtn, b -> setDisable(b));
        RegionsHelper rh = new RegionsHelper(acadsCMB);
        
        centersCMB.getSelectionModel().selectedItemProperty().addListener((obs, old, cur) -> {
            hide(doUpdateBtn, true);
            deleteBtn.setDisable(cur == null);
            updateBtn.setDisable(cur == null);
            chooseBtn.setDisable(cur == null);
        });
        
        deleteBtn.setOnAction(evt -> {
            if ( !centersCMB.getSelectionModel().isEmpty() ) {
               if ( deleter.isDone() )
                    deleter.start();
                else {
                    int id = centersCMB.getSelectionModel().getSelectedItem().getIdCenter();
                    deleter.stop();
                    CENTER.setIdCenter(id);
                    EXAM.moveAllToDummyCenter();
                    if ( CENTER.delete() ) {
                        forceRefresh();
                    }
                }
            }
        });
        
        updateBtn.setOnAction(evt -> {
            CENTER.clone(centersCMB.getSelectionModel().getSelectedItem());
            nameTF.setText(CENTER.getLabel());
            directionTF.setText(CENTER.getDirection());
            rh.select(CENTER.getIdRegion());
            hide(doUpdateBtn, false);
        });
        
        doUpdateBtn.setOnAction(evt -> {
            if ( !nameTF.getText().isEmpty() && !directionTF.getText().isEmpty() && !acadsCMB.getSelectionModel().isEmpty() ) {
                CENTER.setLabel(nameTF.getText());
                CENTER.setDirection(directionTF.getText());
                CENTER.setIdRegion(acadsCMB.getSelectionModel().getSelectedItem().getIdRegion());
                if ( !CENTER.exists() ) {
                    if ( CENTER.update() ) {
                        nameTF.setText("");
                        directionTF.setText("");
                        acadsCMB.getSelectionModel().select(-1);
                        forceRefresh();
                        hide(doUpdateBtn, true);
                    }
                }
            }
        });
        
        doUpdateBtn.visibleProperty().addListener((o, old, cur) -> {
            hide(createBtn, cur);
        });
        
        chooseBtn.setOnAction(evt -> {
            if ( !centersCMB.getSelectionModel().isEmpty() ) {
                if ( EXAM.moveTo(centersCMB.getSelectionModel().getSelectedItem().getIdCenter()) ) {
                    centersCMB.getSelectionModel().select(null);
                    centersCMB.requestFocus();
                }
            }
        });
        
        createBtn.setOnAction(evt -> {
            if ( !nameTF.getText().isEmpty() && !directionTF.getText().isEmpty() && !acadsCMB.getSelectionModel().isEmpty() ) {
                CENTER.setIdCenter(CENTER.nextIdCenter());
                CENTER.setLabel(nameTF.getText());
                CENTER.setDirection(directionTF.getText());
                CENTER.setIdRegion(acadsCMB.getSelectionModel().getSelectedItem().getIdRegion());
                if ( CENTER.save() ) {
                    nameTF.setText("");
                    directionTF.setText("");
                    acadsCMB.getSelectionModel().select(-1);
                    forceRefresh();
                }
            }
        });
        
        refreshBtn.setOnAction(evt -> {
            forceRefresh();
        });
        
        rh.refresh();
        refresh();
        
    }    

    private void setDisable(Boolean b) {
        centersCMB.setDisable(b);
        chooseBtn.setDisable(b);
        createBtn.setDisable(b);
        refreshBtn.setDisable(b);
        updateBtn.setDisable(b);
        doUpdateBtn.setDisable(b);
    }
    
    private void hide(Node n, boolean b) {
        n.setVisible(!b);
        n.setManaged(!b);
    }
    
    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        centersCMB.getItems().clear();
        centersCMB.getItems().addAll(CENTER.list());
        centersCMB.getSelectionModel().select(null);
        centersCMB.requestFocus();
        hide(doUpdateBtn, true);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        chooseBtn.setDisable(true);
    }
}
