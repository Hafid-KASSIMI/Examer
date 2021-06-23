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
import examer.db.BranchPageInfos;
import examer.db.Matter;
import static examer.output.ITEMS.*;
import examer.util.IntegerSpinner;
import examer.util.QuadaryFunction;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class BranchesOutputController implements Initializable {

    @FXML private ComboBox<Matter> mattersCMB; 
    @FXML private MenuItem refreshBtn;
    @FXML private MenuItem todayBtn;
    @FXML private MenuItem currentBtn;
    @FXML private CheckBox maxCB, includeAbsCB, considereSflCB;
    @FXML private Spinner<Integer> maxSp;
    @FXML private Button branchesGenBtn;
    @FXML private DestinationFolderController destDirController;
    
    private final BranchesPDF pdf = new BranchesPDF();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        new IntegerSpinner(maxSp).bind();
        
        maxCB.selectedProperty().addListener((obs, old, cur) -> {
            maxSp.setDisable(old);
            Settings.PREF_BUNDLE.update("BRANCHES_OUTPUT_MAX_ROOMS_USED", cur ? "Y" : "N");
        });
        
        maxSp.valueProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("BRANCHES_OUTPUT_MAX_ROOMS", cur + "");
        });
        
        considereSflCB.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("BRANCHES_OUTPUT_CONSIDERE_SFL", cur ? "Y" : "N");
        });
        
        includeAbsCB.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("BRANCHES_OUTPUT_INCLUDE_ABSENTS", cur ? "Y" : "N");
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
        
        branchesGenBtn.setOnAction(evt -> {
            if ( !mattersCMB.getSelectionModel().isEmpty() ) {
                QuadaryFunction<Integer, Integer, Integer, Integer, ArrayList<BranchPageInfos>> bf = null; 
                MATTER.clone(mattersCMB.getSelectionModel().getSelectedItem());
                switch (MATTER.getIdmatter()) {
                    case ALL:
                        bf = (exam, b, first, last) -> maxCB.isSelected() ? VL_TABLE.getAllBranchesPages(exam, first, last, considereSflCB.isSelected()) : VL_TABLE.getAllBranchesPages(exam, considereSflCB.isSelected());
                        break;
                    case TODAY:
                        bf = (exam, b, first, last) -> maxCB.isSelected() ? VL_TABLE.getTodayBranchesPages(exam, first, last, considereSflCB.isSelected()) : VL_TABLE.getTodayBranchesPages(exam, considereSflCB.isSelected());
                        break;
                    case CURRENT:
                        bf = (exam, b, first, last) -> maxCB.isSelected() ? VL_TABLE.getCurrentBranchesPages(exam, first, last, considereSflCB.isSelected()) : VL_TABLE.getCurrentBranchPages(exam, considereSflCB.isSelected());
                        break;
                    default:
                        switch (mattersCMB.getItems().get(0).getIdmatter()) {
                            case ALL:
                                bf = (exam, matter, first, last) -> maxCB.isSelected() ? VL_TABLE.getBranchMatterAllPages(exam, matter, first, last, considereSflCB.isSelected()) : VL_TABLE.getMatterAllPages(exam, matter, considereSflCB.isSelected());
                                break;
                            case TODAY:
                                bf = (exam, matter, first, last) -> maxCB.isSelected() ? VL_TABLE.getBranchMatterTodayPages(exam, matter, first, last, considereSflCB.isSelected()) : VL_TABLE.getMatterTodayPages(exam, matter, considereSflCB.isSelected());
                                break;
                            case CURRENT:
                                bf = (exam, matter, first, last) -> maxCB.isSelected() ? VL_TABLE.getBranchMatterCurrentPages(exam, matter, first, last, considereSflCB.isSelected()) : VL_TABLE.getMatterCurrentPages(exam, matter, considereSflCB.isSelected());
                                break;
                        }
                        break;
                }
                if ( bf != null ) {
                    ArrayList<BranchPageInfos> arr = new ArrayList();
                    if ( maxCB.isSelected() && maxSp.getValue() > 0 ) {
                        ArrayList<BranchPageInfos> tmp;
                        int firstRoom = 0;
                        int max = maxSp.getValue() - 1;
                        int lastRoom = max;
                        while ( (tmp = bf.apply(EXAM.getIdexam(), MATTER.getIdmatter(), firstRoom, lastRoom)).size() > 0  ) {
                            arr.addAll(tmp);
                            firstRoom = lastRoom + 1;
                            lastRoom = max + firstRoom;
                        }
                    }
                    else {
                        arr.addAll(bf.apply(EXAM.getIdexam(), MATTER.getIdmatter(), null, null));
                    }
                    pdf.generate(arr);
                }
            }
        });
        MattersHelper.refresh(mattersCMB, ALL, a -> MATTER.listExamPlannedMatters(a));
        includeAbsCB.setSelected(Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_INCLUDE_ABSENTS").equals("Y"));
        considereSflCB.setSelected(Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_CONSIDERE_SFL").equals("Y"));
        destDirController.setVariable("BRANCHES_OUTPUT_DESTINATION_FOLDER");
        maxCB.setSelected(Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_MAX_ROOMS_USED").equals("Y"));
        try {
            maxSp.getValueFactory().setValue(Integer.parseInt(Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_MAX_ROOMS")));
        } catch( NumberFormatException e ) {}
    }
    
}
