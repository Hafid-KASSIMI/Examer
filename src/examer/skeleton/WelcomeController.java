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

package examer.skeleton;

import examer.util.Refreshable;
import examer.Settings;
import static examer.Settings.BOSS;
import static examer.Settings.BRANCH;
import static examer.Settings.EXAM;
import static examer.Settings.VL_TABLE;
import examer.db.PlanningsInfos;
import examer.util.DateTime;
import examer.util.EVENT_TYPE;
import examer.util.Event;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class WelcomeController extends Refreshable implements Initializable {

    @FXML private Label examNameLbl, branchesLbl, currentMattersLbl, remainingTimeLbl;
    @FXML private Label candsCountLbl, absCountLbl, cheaCountLbl, maCheaCountLbl, maAbsCountLbl, maCandsCountLbl, feCheaCountLbl, feAbsCountLbl, feCandsCountLbl;
    @FXML private TableView<PlanningsInfos> planTV;
    @FXML private TableColumn<PlanningsInfos, String> branchTC, matTC, hourTC, durationTC, endTC, dateTC;
    @FXML private TableColumn<PlanningsInfos, Integer> absTC, feAbsTC;
    @FXML private ProgressIndicator progressPI;
    @FXML private Tooltip remainingTypeLbl;
    
    private final SimpleStringProperty cssClass = new SimpleStringProperty("");
    private final SimpleDoubleProperty progress = new SimpleDoubleProperty(0);
    private ChangeListener listener;

    public WelcomeController() {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cssClass.addListener((obs, old, cur) -> {
            progressPI.getStyleClass().remove(old);
            progressPI.getStyleClass().add(cur);
        });
        
        listener = (ChangeListener) (ObservableValue obs, Object old, Object cur) -> {
            Event next = BOSS.getNextEvent();
            if ( next != null ) {
                if ( next.getWaitingDuration().getSeconds() == 0 ) {
                    forceRefresh();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Platform.runLater(() -> {
                                refreshCurrentMatters();
                            });
                        }
                    }, 60001);
                }
                if ( next.getPlanType() == EVENT_TYPE.END ) {
                    cssClass.set("timer-end");
                    remainingTypeLbl.setText(Settings.I18N_BUNDLE.getString("REMAINING"));
                    progress.set((next.getDuration().abs().toMinutes() - next.getWaitingDuration().abs().toMinutes()) * 1.0 / next.getDuration().abs().toMinutes());
                }
                else {
                    cssClass.set("timer-start");
                    remainingTypeLbl.setText(Settings.I18N_BUNDLE.getString("REMAINING_NEXT"));
                    if ( BOSS.getWaitingMinutesToStart() > 0 )
                        progress.set((BOSS.getWaitingMinutesToStart() - next.getWaitingDuration().abs().toMinutes()) * 1.0 / BOSS.getWaitingMinutesToStart());
                }
                remainingTimeLbl.setText(DateTime.getTimeString(next.getWaitingDuration()));
            }
            else {
                cssClass.set("");
                remainingTypeLbl.setText(Settings.I18N_BUNDLE.getString("WELCOME"));
                remainingTimeLbl.setText(Settings.I18N_BUNDLE.getString("WELCOME"));
                progress.set(0);
            }
        };
        
        progressPI.progressProperty().bind(progress);
        
        planTV.setPlaceholder(new Label(Settings.I18N_BUNDLE.getString("EMPTY_TABLE")));
                
        Platform.runLater(() -> {
            refresh();
        });
    }
    
    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }
    
    public void refreshCurrentMatters() {
        String tmp;
        tmp = VL_TABLE.listCurrentMatters(EXAM.getIdexam());
        currentMattersLbl.setText(tmp.isEmpty() ? Settings.I18N_BUNDLE.getString("RAS") : tmp);
        tmp = BRANCH.listAsString(EXAM.getIdexam(), true);
        branchesLbl.setText(tmp.isEmpty() ? Settings.I18N_BUNDLE.getString("RAS") : tmp);
    }

    @Override
    public void forceRefresh() {
        int cands = VL_TABLE.getCandidatesCount(EXAM.getIdexam());
        int feCands = VL_TABLE.getCandidatesCount(EXAM.getIdexam(), "F");
        
        examNameLbl.setText(EXAM.getLabel() + " | " + EXAM.getExamSession());
        
        candsCountLbl.setText(String.format("%02d", cands));
        feCandsCountLbl.setText(String.format("%02d", feCands));
        maCandsCountLbl.setText(String.format("%02d", cands - feCands));
        
        cands = VL_TABLE.getAbsentsCount(EXAM.getIdexam());
        feCands = VL_TABLE.getAbsentsCount(EXAM.getIdexam(), "F");
        absCountLbl.setText(String.format("%02d", cands));
        feAbsCountLbl.setText(String.format("%02d", feCands));
        maAbsCountLbl.setText(String.format("%02d", cands - feCands));

        cands = VL_TABLE.getCheatersCount(EXAM.getIdexam());
        feCands = VL_TABLE.getCheatersCount(EXAM.getIdexam(), "F");
        cheaCountLbl.setText(String.format("%02d", cands));
        feCheaCountLbl.setText(String.format("%02d", feCands));
        maCheaCountLbl.setText(String.format("%02d", cands - feCands));

        branchTC.setCellValueFactory(new PropertyValueFactory("branch"));
        matTC.setCellValueFactory(new PropertyValueFactory("matter"));
        hourTC.setCellValueFactory(new PropertyValueFactory("startTime"));
        durationTC.setCellValueFactory(new PropertyValueFactory("duration"));
        endTC.setCellValueFactory(new PropertyValueFactory("endTime"));
        absTC.setCellValueFactory(new PropertyValueFactory("absents"));
        feAbsTC.setCellValueFactory(new PropertyValueFactory("femaleAbsents"));
        dateTC.setCellValueFactory(new PropertyValueFactory("planDate"));

        planTV.getItems().clear();
        planTV.getItems().addAll(VL_TABLE.getPlanning(EXAM.getIdexam()));
        
        BOSS.getSecProperty().removeListener(listener);
        BOSS.getSecProperty().addListener(listener);
        
        refreshCurrentMatters();
        BOSS.refresh();
    }
    
}
