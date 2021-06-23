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
import static examer.Settings.BRANCH;
import static examer.Settings.EXAM;
import static examer.Settings.VL_TABLE;
import examer.bones.branch.BranchesHelper;
import examer.db.Branch;
import examer.db.TrialPageInfos;
import static examer.output.ITEMS.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class TrialOutputController implements Initializable {

    @FXML private TextField titleTF;
    @FXML private RadioButton normalSessRB;
    @FXML private ToggleGroup sessionTG;
    @FXML private RadioButton secondSessRB;
    @FXML private TextField presNameTF;
    @FXML private TextField presCodeTF;
    @FXML private TextField observNameTF;
    @FXML private TextField observCodeTF;
    @FXML private TextField secreNameTF;
    @FXML private TextField secreCodeTF;
    @FXML private Button genOpeningBtn;
    @FXML private Button genClosingBtn;
    @FXML private ComboBox<Branch> branchesCMB;
    @FXML private MenuItem braRefreshBtn;
    @FXML private MenuItem braTodayBtn;
    @FXML private MenuItem braCurrentBtn;
    @FXML private DestinationFolderController destDirController;
    @FXML private CheckBox duplicateCB;

    private final OpeningTrialPDF openingPdf = new OpeningTrialPDF();
    private final ClosingTrialPDF closingPdf = new ClosingTrialPDF();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if ( Settings.PREF_BUNDLE.get("TRIAL_ORDINARY_SESSION").equals("Y") )
            normalSessRB.setSelected(true);
        else
            secondSessRB.setSelected(true);
        
        duplicateCB.setSelected(Settings.PREF_BUNDLE.get("TRIALS_OUTPUT_DUPLICATE_PAGES").equals("Y"));
        
        sessionTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("TRIAL_ORDINARY_SESSION", cur.equals(normalSessRB) ? "Y" : "N");
        });
        
        duplicateCB.selectedProperty().addListener((obs, old, cur) -> {
            Settings.PREF_BUNDLE.update("TRIALS_OUTPUT_DUPLICATE_PAGES", cur ? "Y" : "N");
        });
        
        braRefreshBtn.setOnAction(evt -> {
            BranchesHelper.refresh(branchesCMB, ALL, a -> BRANCH.list(a));
        });
        
        braTodayBtn.setOnAction(evt -> {
            BranchesHelper.refresh(branchesCMB, TODAY, a -> BRANCH.todayList(a));
        });
        
        braCurrentBtn.setOnAction(evt -> {
            BranchesHelper.refresh(branchesCMB, CURRENT, a -> BRANCH.currentList(a));
        });
        
        genOpeningBtn.setOnAction(evt -> {
            generate(openingPdf::generate);
        });
        
        genClosingBtn.setOnAction(evt -> {
            generate(closingPdf::generate);
        });
        
        titleTF.setText(Settings.PREF_BUNDLE.get("TRIAL_TITLE"));
        presNameTF.setText(Settings.PREF_BUNDLE.get("TRIAL_PRESIDENT_NAME"));
        presCodeTF.setText(Settings.PREF_BUNDLE.get("TRIAL_PRESIDENT_CODE"));
        observNameTF.setText(Settings.PREF_BUNDLE.get("TRIAL_OBSERVER_NAME"));
        observCodeTF.setText(Settings.PREF_BUNDLE.get("TRIAL_OBSERVER_CODE"));
        secreNameTF.setText(Settings.PREF_BUNDLE.get("TRIAL_SECRETARY_NAME"));
        secreCodeTF.setText(Settings.PREF_BUNDLE.get("TRIAL_SECRETARY_CODE"));
        destDirController.setVariable("TRIALS_OUTPUT_DESTINATION_FOLDER");
        
    }
    
    private void generate(Function<ArrayList<TrialPageInfos>, Boolean> realGenerate) {
        if ( !branchesCMB.getSelectionModel().isEmpty() ) {
            ArrayList<TrialPageInfos> arr = new ArrayList();
            BiFunction<Integer, Integer, ArrayList<TrialPageInfos>> bf = null; 
            BRANCH.clone(branchesCMB.getSelectionModel().getSelectedItem());
            switch (BRANCH.getIdbranch()) {
                case ALL:
                    bf = (exam, dummy) -> VL_TABLE.getAllBranchTrialPages(exam);
                    break;
                case TODAY:
                    bf = (exam, dummy) -> VL_TABLE.getTodayBranchTrialPages(exam);
                    break;
                case CURRENT:
                    bf = (exam, dummy) -> VL_TABLE.getCurrentBranchTrialPages(exam);
                    break;
                default:
                    switch (branchesCMB.getItems().get(0).getIdbranch()) {
                        case ALL:
                            bf = (exam, branch) -> VL_TABLE.getAllBranchTrialPages(exam, branch + "");
                            break;
                        case TODAY:
                            bf = (exam, branch) -> VL_TABLE.getTodayBranchTrialPages(exam, branch + "");
                            break;
                        case CURRENT:
                            bf = (exam, branch) -> VL_TABLE.getCurrentBranchTrialPages(exam, branch + "");
                            break;
                    }
                    break;
            }

            if ( bf != null )
                arr.addAll(bf.apply(EXAM.getIdexam(), BRANCH.getIdbranch()));

            Settings.PREF_BUNDLE.update("TRIAL_TITLE", titleTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_PRESIDENT_NAME", presNameTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_PRESIDENT_CODE", presCodeTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_OBSERVER_NAME", observNameTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_OBSERVER_CODE", observCodeTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_SECRETARY_NAME", secreNameTF.getText());
            Settings.PREF_BUNDLE.update("TRIAL_SECRETARY_CODE", secreCodeTF.getText());
            realGenerate.apply(arr);
        }
    }
    
}
