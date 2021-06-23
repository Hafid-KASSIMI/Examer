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

import static examer.Settings.PLAN;
import static examer.Settings.PLMB;
import examer.db.Plan;
import examer.util.Deleter;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class AddPlanController implements Initializable {

    @FXML private ComboBox<Plan> plansCMB;
    @FXML private ToggleGroup seanceTG, periodTG;
    @FXML private RadioButton oneRB, twoRB, fourRB, threeRB, morningRB, afternoonRB;
    @FXML private Spinner<LocalTime> hourSP, durationSP;
    @FXML private Button saveBtn, updateBtn, doUpdateBtn, deleteBtn, refreshBtn, cancelUpdateBtn;
    @FXML private Label updatePlanLbl, newPlanLbl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Deleter deleter = new Deleter(deleteBtn, b -> setDisable(b));
        
        PlansHelper ph = new PlansHelper(plansCMB, refreshBtn);
        
        LocalTimeSpinnerValueFactory hourSFD = new LocalTimeSpinnerValueFactory(LocalTime.of(5, 0), LocalTime.of(20, 0), LocalTime.of(8, 0), 10);
        LocalTimeSpinnerValueFactory durationSFD = new LocalTimeSpinnerValueFactory(LocalTime.of(1, 0), LocalTime.of(7, 00), LocalTime.of(1, 0), 10);
        
        TextFormatter hoursFormatter = new TextFormatter(hourSFD.getConverter(), hourSFD.getValue());
        TextFormatter durationFormatter = new TextFormatter(durationSFD.getConverter(), durationSFD.getValue());
        
        hourSP.setValueFactory(hourSFD);
        hourSP.getEditor().setTextFormatter(hoursFormatter);
        durationSP.setValueFactory(durationSFD);
        durationSP.getEditor().setTextFormatter(durationFormatter);
        
        hourSFD.valueProperty().bindBidirectional(hoursFormatter.valueProperty());
        durationSFD.valueProperty().bindBidirectional(durationFormatter.valueProperty());
        
        periodTG.selectedToggleProperty().addListener((o, old, cur) -> {
            hourSFD.setValue( cur.equals(morningRB) ? LocalTime.of(8, 0) : LocalTime.of(14, 0) );
        });

        updatePlanLbl.visibleProperty().addListener((obs, old, cur) -> {
            hide(newPlanLbl, cur);
        });

        doUpdateBtn.visibleProperty().addListener((obs, old, cur) -> {
            hide(saveBtn, cur);
            hide(cancelUpdateBtn, old);
        });
        
        plansCMB.getSelectionModel().selectedIndexProperty().addListener((obs, old, cur) -> {
            if ( cur != old ) {
                hide(updatePlanLbl, true);
                hide(doUpdateBtn, true);
            }
        });
        
        oneRB.setUserData(1);
        twoRB.setUserData(2);
        threeRB.setUserData(3);
        fourRB.setUserData(4);
        morningRB.setUserData("AM");
        afternoonRB.setUserData("PM");
        
        saveBtn.setOnAction(evt -> {
            PLAN.setPeriod(periodTG.getSelectedToggle().getUserData().toString());
            PLAN.setSeance((int) seanceTG.getSelectedToggle().getUserData());
            PLAN.setStarttime(hourSFD.getValue().toString());
            PLAN.setDuration(durationSFD.getValue().toString());
            if ( !PLAN.exists() ) {
                PLAN.setIdplan(PLAN.nextIdplan());
                if ( PLAN.save() ) {
                    ph.refresh();
                    plansCMB.requestFocus();
                }
            }
        });
        
        updateBtn.setOnAction(evt -> {
            if ( !plansCMB.getSelectionModel().isEmpty() ) {
                hide(doUpdateBtn, false);
                hide(updatePlanLbl, false);
                PLAN.clone(plansCMB.getSelectionModel().getSelectedItem());
                periodTG.selectToggle( PLAN.getPeriod().equals("AM") ? morningRB : afternoonRB );
                switch ( PLAN.getSeance() ) {
                    case 1:
                        seanceTG.selectToggle( oneRB );
                        break;
                    case 2:
                        seanceTG.selectToggle( twoRB );
                        break;
                    case 3:
                        seanceTG.selectToggle( threeRB );
                        break;
                    case 4:
                        seanceTG.selectToggle( fourRB );
                        break;
                }
                hourSFD.setValue(LocalTime.parse(PLAN.getStarttime(), DateTimeFormatter.ISO_LOCAL_TIME));
                durationSFD.setValue(LocalTime.parse(PLAN.getDuration(), DateTimeFormatter.ISO_LOCAL_TIME));
            }
            else {
                plansCMB.requestFocus();
            }
        });
        
        doUpdateBtn.setOnAction(evt -> {
            PLAN.setPeriod(periodTG.getSelectedToggle().getUserData().toString());
            PLAN.setSeance((int) seanceTG.getSelectedToggle().getUserData());
            PLAN.setStarttime(hourSFD.getValue().toString());
            PLAN.setDuration(durationSFD.getValue().toString());
            if ( !PLAN.exists() ) {
                if ( PLAN.update() ) {
                    ph.refresh();
                    plansCMB.requestFocus();
                }
            }
            hide(updatePlanLbl, true);
            hide(doUpdateBtn, true);
        });
        
        cancelUpdateBtn.setOnAction(evt -> {
            hide(updatePlanLbl, true);
            hide(doUpdateBtn, true);
        });
        
        deleteBtn.setOnAction(evt -> {
            if ( deleter.isDone() )
                deleter.start();
            else {
                int id = plansCMB.getSelectionModel().getSelectedItem().getIdplan();
                deleter.stop();
                PLAN.setIdplan(id);
                PLMB.setIdplan(id);
                PLMB.deletePlan();
                if ( PLAN.delete() ) {
                    ph.refresh();
                    plansCMB.requestFocus();
                }
            }
        });
        
        ph.bind();
        hide(updatePlanLbl, true);
        hide(doUpdateBtn, true);
    }

    private void hide(Node n, boolean b) {
        n.setVisible(!b);
        n.setManaged(!b);
    }

    private void setDisable(Boolean b) {
        plansCMB.setDisable(b);
        updateBtn.setDisable(b);
        doUpdateBtn.setDisable(b);
        cancelUpdateBtn.setDisable(b);
        saveBtn.setDisable(b);
        refreshBtn.setDisable(b);
    }
    
}
