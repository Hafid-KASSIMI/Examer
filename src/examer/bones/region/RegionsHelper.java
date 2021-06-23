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
package examer.bones.region;

import static examer.Settings.REGION;
import examer.db.Region;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Sicut
 */
public class RegionsHelper {
    private final ComboBox<Region> list;

    public RegionsHelper(ComboBox<Region> list) {
        this.list = list;
    }
    
    public void refresh() {
        list.getItems().clear();
        list.getItems().addAll(REGION.list());
    }
    
    public void select(int idRegion) {
        list.getSelectionModel().select(list.getItems().filtered(reg -> reg.getIdRegion() == idRegion).get(0));
    }
    
}
