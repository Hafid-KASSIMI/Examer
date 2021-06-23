/*
 * Copyright (C) 2020 Sicut
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
 
package examer;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hafid KASSIMI
 */
public class Main {
    
    private Stage stage;
    
    public Main() {
        stage = new Stage();
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/examer/skeleton/main.fxml"), Settings.I18N_BUNDLE);
            Parent root = fl.load();
            stage.setScene(new Scene(root));
            stage.setTitle(Settings.I18N_BUNDLE.getString("APP_TITLE"));
        } catch (IOException ex) { }
    }
    
    public void show() {
        stage.setMaximized(true);
        stage.show();
        stage.setOnCloseRequest(evt -> {
            Settings.PREF_BUNDLE.commitAndExit();
        });
    }

    public Stage getStage() {
        return stage;
    }
    
}
