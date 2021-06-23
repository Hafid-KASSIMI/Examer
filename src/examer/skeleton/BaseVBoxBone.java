/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package examer.skeleton;

import examer.Settings;
import examer.util.Refreshable;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class BaseVBoxBone {
    
    private VBox vb;
    private Refreshable controller;
    
    public BaseVBoxBone(String fxml) {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource(fxml), Settings.I18N_BUNDLE);
            vb = fl.load();
            vb.managedProperty().bind(vb.visibleProperty());
            controller = fl.getController();
        }
        catch(IOException ex) {}
    }
    
    public VBox getVBox() {
        return vb;
    }

    public Refreshable getController() {
        return controller;
    }
    
}
