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
package examer.skeleton.ribbonBar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class RibbonController implements Initializable {

    @FXML private Button welcomeBtn;
    @FXML private Button planRtn;
    @FXML private Button roomsBtn;
    @FXML private Button candidatesBtn;
    @FXML private Button absentsBtn;
    @FXML private Button cheatersBtn;
    @FXML private Button centerBtn;
    @FXML private Button outputsBtn;

    private Button selectedBtn;
    private final SimpleStringProperty currentMenu = new SimpleStringProperty();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EventHandler eh = (EventHandler) (Event event) -> {
            if ( selectedBtn != null ) 
                selectedBtn.getStyleClass().remove("selected");
            selectedBtn = (Button) event.getSource();
            currentMenu.set(selectedBtn.getUserData().toString());
            selectedBtn.getStyleClass().add("selected");
        };
        
        welcomeBtn.setUserData("WELCOME");
        planRtn.setUserData("PLANNING");
        roomsBtn.setUserData("ROOMS");
        candidatesBtn.setUserData("CANDIDATES");
        absentsBtn.setUserData("ABSENTS");
        cheatersBtn.setUserData("CHEATERS");
        centerBtn.setUserData("CENTER");
        outputsBtn.setUserData("OUTPUTS");
        
        welcomeBtn.setOnAction(eh);
        planRtn.setOnAction(eh);
        roomsBtn.setOnAction(eh);
        candidatesBtn.setOnAction(eh);
        absentsBtn.setOnAction(eh);
        cheatersBtn.setOnAction(eh);
        centerBtn.setOnAction(eh);
        outputsBtn.setOnAction(eh);
        
    }    

    public SimpleStringProperty getCurrentMenu() {
        return currentMenu;
    }
    
    public void sayHi() {
        welcomeBtn.fire();
    }
    
}
