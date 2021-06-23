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
package examer.bones.candidate;

import examer.Settings;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

/**
 *
 * @author Sicut
 */
public class CandidateInfosRow {
    
    private CandidateInfosRowController rowController;
    private HBox rowHBox;
    private Boolean loaded;
    
    public CandidateInfosRow() {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("candidateInfosRow.fxml"), Settings.I18N_BUNDLE);
            rowHBox = fl.load();
            rowController = (CandidateInfosRowController) fl.getController();
            loaded = true;
        } catch (IOException ex) { 
            rowController = null;
            loaded = false;
        }
    }

    public CandidateInfosRowController getRowController() {
        return rowController;
    }

    public HBox getRowHBox() {
        return rowHBox;
    }

    public Boolean isLoaded() {
        return loaded;
    }

}
