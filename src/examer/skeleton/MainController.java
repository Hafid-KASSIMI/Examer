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
import static examer.Settings.BOSS;
import examer.skeleton.ribbonBar.RibbonController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class MainController extends Refreshable implements Initializable {
   
    @FXML StackPane bodySP;
    @FXML private Button refreshBtn, chooseExamBtn;
    @FXML private Label currentTimeLbl, todayLbl;
    @FXML private MenuItem quitMI, refreshMI, chooseExamMI, aboutMI;
    @FXML private RibbonController ribbonHBController;
    
    private Stage slideshow;
    private EventHandler refreshHandler, chooseExamHandler;
    private About about;
    private BaseVBoxBone currentVB, welcomeVB, planningVB, roomsVB, candidatesVB, absentsVB, cheatersVB, centerVB, outputsVB;

    public MainController() {
        BOSS.start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        refreshHandler = (EventHandler) -> {
            if ( currentVB.getController() != null )
                currentVB.getController().forceRefresh();
        };
        
        refreshBtn.setOnAction(refreshHandler);
        refreshMI.setOnAction(refreshHandler);
        
        chooseExamHandler = (EventHandler) -> {
            ((SimpleStringProperty) chooseExamBtn.getScene().getUserData()).set("EXAM_SELECTOR");
        };
        chooseExamBtn.setOnAction(chooseExamHandler);
        chooseExamMI.setOnAction(chooseExamHandler);
        
        quitMI.setOnAction(evt -> {
            ((SimpleStringProperty) chooseExamBtn.getScene().getUserData()).set("QUIT");
        });
        
        aboutMI.setOnAction(evt -> {
            if ( about == null )
                about = new About((Stage) todayLbl.getScene().getWindow());
            about.show();
        });
        
        todayLbl.textProperty().bind(BOSS.getCurrentDate());
        currentTimeLbl.textProperty().bind(BOSS.getCurrentTime());
        
//        startSlideShowTB.selectedProperty().addListener((obs, old, cur) -> {
//            if ( cur ) {
//                if ( slideshow == null ) {
//                    slideshow = new Stage();
//                    slideshow.setScene(new SlideShow().getScene());
//                    slideshow.setOnCloseRequest(evt -> {
//                        startSlideShowTB.setSelected(false);
//                    });
//                }
//                slideshow.setFullScreen(true);
//                slideshow.show();
//            }
//            else {
//                if ( slideshow != null ) {
//                    slideshow.hide();
//                }
//            }
//        });

        ribbonHBController.getCurrentMenu().addListener((obs, old, cur) -> {
            if ( currentVB != null )
                currentVB.getVBox().setVisible(false);
            switch ( cur ) {
                case "PLANNING":
                    if ( planningVB == null ) {
                        planningVB = new PlanningVBox();
                        bodySP.getChildren().add(planningVB.getVBox());
                    }
                    planningVB.getVBox().setVisible(true);
                    currentVB = planningVB;
                    break;
                case "ROOMS":
                    if ( roomsVB == null ) {
                        roomsVB = new RoomsVBox();
                        bodySP.getChildren().add(roomsVB.getVBox());
                    }
                    roomsVB.getVBox().setVisible(true);
                    currentVB = roomsVB;
                    break;
                case "CANDIDATES":
                    if ( candidatesVB == null ) {
                        candidatesVB = new CandidatesVBox();
                        bodySP.getChildren().add(candidatesVB.getVBox());
                    }
                    candidatesVB.getVBox().setVisible(true);
                    currentVB = candidatesVB;
                    break;
                case "ABSENTS":
                    if ( absentsVB == null ) {
                        absentsVB = new AbsentsVBox();
                        bodySP.getChildren().add(absentsVB.getVBox());
                    }
                    absentsVB.getVBox().setVisible(true);
                    currentVB = absentsVB;
                    break;
                case "CHEATERS":
                    if ( cheatersVB == null ) {
                        cheatersVB = new CheatersVBox();
                        bodySP.getChildren().add(cheatersVB.getVBox());
                    }
                    cheatersVB.getVBox().setVisible(true);
                    currentVB = cheatersVB;
                    break;
                case "CENTER":
                    if ( centerVB == null ) {
                        centerVB = new CentersVBox();
                        bodySP.getChildren().add(centerVB.getVBox());
                    }
                    centerVB.getVBox().setVisible(true);
                    currentVB = centerVB;
                    break;
                case "OUTPUTS":
                    if ( outputsVB == null ) {
                        outputsVB = new OutputsVBox();
                        bodySP.getChildren().add(outputsVB.getVBox());
                    }
                    outputsVB.getVBox().setVisible(true);
                    currentVB = outputsVB;
                    break;
                default:
                    if ( welcomeVB == null ) {
                        welcomeVB = new WelcomeVBox();
                        bodySP.getChildren().add(welcomeVB.getVBox());
                    }
                    welcomeVB.getVBox().setVisible(true);
                    currentVB = welcomeVB;
                    break;
            }
            
            if ( !currentVB.getController().getIsRefreshed() )
                currentVB.getController().refresh();
        });
        ribbonHBController.sayHi();
        
    } 

    private void hide(Node n, boolean b) {
        n.setVisible(!b);
        n.setManaged(!b);
    }

    @Override
    public void refresh() {
        if ( !isRefreshed )
            forceRefresh();
        isRefreshed = true;
    }

    @Override
    public void forceRefresh() {
        if ( currentVB != null )
            currentVB.getController().forceRefresh();
    }
    
}
