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
package examer.bones.branch;

import examer.Settings;
import static examer.Settings.BRANCH;
import static examer.Settings.EXAM;
import examer.db.Branch;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * @author Sicut
 */
public class BranchesHelper {
    private final ComboBox<Branch> list;
    private final Button refresh;
    private final Boolean includeAllItem;

    public BranchesHelper(ComboBox<Branch> list, Button refresh) {
        this.list = list;
        this.refresh = refresh;
        this.includeAllItem = false;
    }

    public BranchesHelper(ComboBox<Branch> list) {
        this.list = list;
        this.refresh = null;
        this.includeAllItem = false;
    }

    public BranchesHelper(ComboBox<Branch> list, boolean includeAllItem) {
        this.list = list;
        this.refresh = null;
        this.includeAllItem = includeAllItem;
    }

    public BranchesHelper() {
        this.list = null;
        this.refresh = null;
        this.includeAllItem = null;
    }
    
    public void bindExamBranches() {
        this.refresh.setOnAction(evt -> {
            refreshExamBranchesList();
        });
        refreshExamBranchesList();
    }
    
    public void bind() {
        this.refresh.setOnAction(evt -> {
            refreshList();
        });
        refreshList();
    }
    
    public void bindNotInExamBranches() {
        this.refresh.setOnAction(evt -> {
            refreshNotInExamBranchesList();
        });
        refreshNotInExamBranchesList();
    }
    
    public void refreshExamBranchesList() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        if ( includeAllItem )
            list.getItems().add(new Branch(-1, "*"));
        list.getItems().addAll(BRANCH.list(EXAM.getIdexam(), true));
        list.getSelectionModel().select(index);
    }
    
    public void refreshNotInExamBranchesList() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        if ( includeAllItem )
            list.getItems().add(new Branch(-1, "*"));
        list.getItems().addAll(BRANCH.listNotPlanned(EXAM.getIdexam()));
        list.getSelectionModel().select(index);
    }
    
    public void refreshList() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        if ( includeAllItem )
            list.getItems().add(new Branch(-1, "*"));
        list.getItems().addAll(BRANCH.list());
        list.getSelectionModel().select(index);
    }
    
    public static void refresh(ComboBox<Branch> list, int items, Function<Integer, ArrayList<Branch>> f) {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        list.getItems().add(new Branch(items, Settings.I18N_BUNDLE.getString("ALL") + " (*)"));
        list.getItems().addAll(f.apply(EXAM.getIdexam()));
        list.getSelectionModel().select(index);
    }
}
