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
package examer.output;

import examer.Settings;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import static org.sicut.util.EnvVariable.HOMEDirectory;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class DestinationFolderController implements Initializable {

    @FXML private TextField pathTF;
    @FXML private Button browseBtn;

    private String variable;
    private final DirectoryChooser dc = new DirectoryChooser();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        browseBtn.setOnAction(e -> {
            File dir = dc.showDialog(browseBtn.getScene().getWindow());
            if ( dir != null ) {
                Settings.PREF_BUNDLE.update(variable, dir.getPath());
                pathTF.setText(dir.getPath());
                dc.setInitialDirectory(dir);
            }
        });
        
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
        reset();
    }
    
    private void reset() {
        File f = new File(Settings.PREF_BUNDLE.get(variable));
        if ( !f.exists() ) {
            f = new File(HOMEDirectory() + "/Examer");
            f.mkdir();
        }
        pathTF.setText(f.getPath());
        Settings.PREF_BUNDLE.update(variable, f.getPath());
        dc.setInitialDirectory(f);
    }
    
}
