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
import examer.bones.candidate.SpecialCandidateController;
import static examer.Settings.CHEATER;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class CheatersController extends Refreshable implements Initializable {

    @FXML SpecialCandidateController specialCandController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        specialCandController.setSpecialCand(CHEATER);
        specialCandController.setCheckTitle(rb.getString("CHECK_CHEATERS"));
        specialCandController.setTitle(rb.getString("CONTROL_CHEATERS_SC"));
    }    

    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        
    }
    
}
