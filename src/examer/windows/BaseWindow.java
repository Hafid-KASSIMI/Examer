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
package examer.windows;

import examer.Settings;
import examer.util.Refreshable;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Sicut
 */
public abstract class BaseWindow {
    protected Scene scene;
    private Refreshable bwc;
    
    public BaseWindow(String view) {
        try {
            Parent root;
            FXMLLoader fl = new FXMLLoader(getClass().getResource(view), Settings.I18N_BUNDLE);
            root = fl.load();
            bwc = fl.getController();
            scene = new Scene(root);
            scene.setNodeOrientation( ( Settings.SELECTED_LANG.equals("AR") ? NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT ) );
        }
        catch(IOException ex) { }
    }
    
    public void refresh() {
        bwc.forceRefresh();
    }
    
    public Scene getScene() {
        return scene;
    }

    public abstract boolean isResizable();
    
    public abstract boolean isMaximized();
}
