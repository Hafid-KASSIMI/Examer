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
package examer.output;

import examer.Settings;
import static examer.Settings.EXAM;
import static examer.Settings.MATTER;
import static examer.Settings.VL_TABLE;
import examer.bones.matter.MattersHelper;
import examer.db.Matter;
import examer.db.RoomPageInfos;
import static examer.output.ITEMS.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class RoomsOutputController implements Initializable {

    @FXML private TextField titleTF;
    @FXML private ComboBox<Matter> mattersCMB;    
    @FXML private MenuItem refreshBtn;
    @FXML private Button roomsGenBtn;
    @FXML private MenuItem todayBtn;
    @FXML private MenuItem currentBtn;
    @FXML private CheckBox includeAbsCB, considereSflCB;
    @FXML private DestinationFolderController destDirController;
    
    private final RoomsPDF pdf = new RoomsPDF();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        titleTF.setText(Settings.PREF_BUNDLE.get("ROOMS_OUTPUT_TITLE"));
        includeAbsCB.setSelected(Settings.PREF_BUNDLE.get("ROOMS_OUTPUT_INCLUDE_ABSENTS").equals("Y"));
        considereSflCB.setSelected(Settings.PREF_BUNDLE.get("ROOMS_OUTPUT_CONSIDERE_SFL").equals("Y"));
        destDirController.setVariable("ROOMS_OUTPUT_DESTINATION_FOLDER");
        
        MattersHelper.refresh(mattersCMB, ALL, a -> MATTER.listExamPlannedMatters(a));
        
        considereSflCB.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("ROOMS_OUTPUT_CONSIDERE_SFL", cur ? "Y" : "N");
        });
        
        includeAbsCB.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("ROOMS_OUTPUT_INCLUDE_ABSENTS", cur ? "Y" : "N");
        });
        
        refreshBtn.setOnAction(evt -> {
            MattersHelper.refresh(mattersCMB, ALL, a -> MATTER.listExamPlannedMatters(a));
        });
        
        todayBtn.setOnAction(evt -> {
            MattersHelper.refresh(mattersCMB, TODAY, a -> MATTER.listExamTodayMatters(a));
        });
        
        currentBtn.setOnAction(evt -> {
            MattersHelper.refresh(mattersCMB, CURRENT, a -> MATTER.listExamCurrentMatters(a));
        });
        
        roomsGenBtn.setOnAction(evt -> {
            if ( !mattersCMB.getSelectionModel().isEmpty() ) {
                BiFunction<Integer, Integer, ArrayList<RoomPageInfos>> bf = null; 
                MATTER.clone(mattersCMB.getSelectionModel().getSelectedItem());
                switch (MATTER.getIdmatter()) {
                    case ALL:
                            bf = (exam, matter) -> VL_TABLE.getAllRoomPages(exam, "%", considereSflCB.isSelected());
                        break;
                    case TODAY:
                            bf = (exam, matter) -> VL_TABLE.getTodayRoomPages(exam, "%", considereSflCB.isSelected());
                        break;
                    case CURRENT:
                        bf = (exam, matter) -> VL_TABLE.getCurrentRoomPages(exam, "%", considereSflCB.isSelected());
                        break;
                    default:
                        switch (mattersCMB.getItems().get(0).getIdmatter()) {
                            case ALL:
                                bf = (exam, matter) -> VL_TABLE.getAllRoomPages(exam, matter + "", considereSflCB.isSelected());
                                break;
                            case TODAY:
                                bf = (exam, matter) -> VL_TABLE.getTodayRoomPages(exam, matter + "", considereSflCB.isSelected());
                                break;
                            case CURRENT:
                                bf = (exam, matter) -> VL_TABLE.getCurrentRoomPages(exam, matter + "", considereSflCB.isSelected());
                                break;
                        }
                        break;
                }
                if ( bf != null ) {
                    pdf.generate(bf.apply(EXAM.getIdexam(), MATTER.getIdmatter()), titleTF.getText());
                    Settings.PREF_BUNDLE.update("ROOMS_OUTPUT_TITLE", titleTF.getText());
                }
            }
        });
    }
    
}
