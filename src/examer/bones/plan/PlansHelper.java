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
package examer.bones.plan;

import static examer.Settings.PLAN;
import examer.db.Plan;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Sicut
 */
public class PlansHelper {
    private final ComboBox<Plan> list;
    private final Button refresh;

    public PlansHelper(ComboBox<Plan> list, Button refresh) {
        this.list = list;
        this.refresh = refresh;
    }
    
    public PlansHelper(ComboBox<Plan> list) {
        this.list = list;
        this.refresh = null;
    }
    
    public void bind() {
        this.refresh.setOnAction(evt -> {
            refresh();
        });
        refresh();
    }
    
    public void refresh() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        list.getItems().addAll(PLAN.list());
        list.getSelectionModel().select(index);
    }
    
    public boolean selectIdPlan(int idPlan) {
        FilteredList<Plan> fl = list.getItems().filtered(plan -> plan.getIdplan() == idPlan);
        if ( fl.size() > 0 ) {
            list.getSelectionModel().select(fl.get(0));
            return true;
        }
        return false;
    }
}
