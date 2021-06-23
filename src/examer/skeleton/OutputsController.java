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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class OutputsController extends Refreshable implements Initializable {

    @FXML private ToggleButton branchesTB;
    @FXML private ToggleButton roomsTB;
    @FXML private ToggleButton trialsTB;
    @FXML private ToggleGroup commandsTG;
    @FXML private VBox branchesVB, roomsVB, trialsVB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roomsVB.setVisible(false);
        trialsVB.setVisible(false);
        
        commandsTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            if ( cur != null ) {
                if ( cur == branchesTB ) {
                    branchesVB.setVisible(true);
                    roomsVB.setVisible(false);
                    trialsVB.setVisible(false);
                }
                else if ( cur == roomsTB ) {
                    branchesVB.setVisible(false);
                    roomsVB.setVisible(true);
                    trialsVB.setVisible(false);
                }
                else if ( cur == trialsTB ) {
                    branchesVB.setVisible(false);
                    roomsVB.setVisible(false);
                    trialsVB.setVisible(true);
                }
                    
            }
            else
                commandsTG.selectToggle(old);
        });
        
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
