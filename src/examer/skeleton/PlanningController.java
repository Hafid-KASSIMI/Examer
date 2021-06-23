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

import examer.Settings;
import examer.util.Refreshable;
import static examer.Settings.EXAM;
import static examer.Settings.VL_TABLE;
import examer.db.PlanningsInfos;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class PlanningController extends Refreshable implements Initializable {

    @FXML private TableView<PlanningsInfos> planTV;
    @FXML private TableColumn<PlanningsInfos, String> branchTC, matTC, hourTC, durationTC, endTC, remainingTC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        branchTC.setCellValueFactory(new PropertyValueFactory("branch"));
        matTC.setCellValueFactory(new PropertyValueFactory("matter"));
        hourTC.setCellValueFactory(new PropertyValueFactory("startTime"));
        durationTC.setCellValueFactory(new PropertyValueFactory("duration"));
        endTC.setCellValueFactory(new PropertyValueFactory("endTime"));
        remainingTC.setCellValueFactory(new PropertyValueFactory("remaining"));
        
        planTV.setPlaceholder(new Label(Settings.I18N_BUNDLE.getString("EMPTY_TABLE")));
    }
    
    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        planTV.getItems().clear();
        planTV.getItems().addAll(VL_TABLE.getTodayPlanning(EXAM.getIdexam()));
    }
    
}
