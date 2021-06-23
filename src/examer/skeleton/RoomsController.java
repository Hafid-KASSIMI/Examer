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
import static examer.Settings.ROOM;
import static examer.Settings.VL_TABLE;
import examer.db.RoomsInfos;
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
public class RoomsController extends Refreshable implements Initializable {

    @FXML private Label maxCandsLbl, minCandsLbl, avgCandsLbl, roomsCountLbl;
    @FXML private TableView<RoomsInfos> roomsTV;
    @FXML private TableColumn<RoomsInfos, Integer> numTC, candsNumTC, cands1stTC, candsLastTC, absTC, cheaTC, feAbsTC, feCheaTC, feTC;
    @FXML private TableColumn<RoomsInfos, String> braTC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        numTC.setCellValueFactory(new PropertyValueFactory("numero"));
        candsNumTC.setCellValueFactory(new PropertyValueFactory("candidates"));
        cands1stTC.setCellValueFactory(new PropertyValueFactory("firstCode"));
        candsLastTC.setCellValueFactory(new PropertyValueFactory("lastCode"));
        braTC.setCellValueFactory(new PropertyValueFactory("branch"));
        absTC.setCellValueFactory(new PropertyValueFactory("absents"));
        cheaTC.setCellValueFactory(new PropertyValueFactory("cheaters"));
        feAbsTC.setCellValueFactory(new PropertyValueFactory("femaleAbsents"));
        feCheaTC.setCellValueFactory(new PropertyValueFactory("femaleCheaters"));
        feTC.setCellValueFactory(new PropertyValueFactory("females"));    
        
        roomsTV.setPlaceholder(new Label(Settings.I18N_BUNDLE.getString("EMPTY_TABLE")));    
    }
    
    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        roomsCountLbl.setText(String.format("%02d", ROOM.count(EXAM.getIdexam())));
        minCandsLbl.setText(String.format("%02d", VL_TABLE.getCandidatesMinCount(EXAM.getIdexam())));
        avgCandsLbl.setText(String.format("%02.2f", VL_TABLE.getCandidatesAvgCount(EXAM.getIdexam())));
        maxCandsLbl.setText(String.format("%02d", VL_TABLE.getCandidatesMaxCount(EXAM.getIdexam())));
        roomsTV.getItems().clear();
        roomsTV.getItems().addAll(VL_TABLE.getRoomsInfos(EXAM.getIdexam()));
    }
    
}
