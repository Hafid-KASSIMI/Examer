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
package examer.bones.matter;

import examer.Settings;
import static examer.Settings.EXAM;
import static examer.Settings.MATTER;
import examer.db.Matter;
import java.util.ArrayList;
import java.util.function.Function;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Sicut
 */
public class MattersHelper {
    private final ComboBox<Matter> list;
    private final Button refresh;
    private int idExam, idBranch;

    public MattersHelper(ComboBox<Matter> list, Button refresh) {
        this.list = list;
        this.refresh = refresh;
    }
    
    public MattersHelper(ComboBox<Matter> list) {
        this.list = list;
        this.refresh = null;
    }
    
    public void bind() {
        this.refresh.setOnAction(evt -> {
            refresh();
        });
        refresh();
    }
    
    public void bindPlannedMatters() {
        this.refresh.setOnAction(evt -> {
            refreshPlannedMatters();
        });
        refreshPlannedMatters();
    }
    
    public void fill() {
        list.getItems().clear();
        list.getItems().addAll(MATTER.list());
    }
    
    public void refreshPlannedMatters() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        list.getItems().addAll(MATTER.listBranchPlannedMatters(idExam, idBranch));
        list.getSelectionModel().select(index);
    }
    
    public void refreshExamPlannedMatters() {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        list.getItems().addAll(MATTER.listExamPlannedMatters(idExam));
        list.getSelectionModel().select(index);
    }
    
    private void refresh() {
        list.getItems().clear();
        list.getItems().addAll(MATTER.list());
    }
    
    public static void refresh(ComboBox<Matter> list, int items, Function<Integer, ArrayList<Matter>> f) {
        int index = list.getSelectionModel().getSelectedIndex();
        list.getItems().clear();
        list.getItems().add(new Matter(items, Settings.I18N_BUNDLE.getString("ALL") + " (*)"));
        list.getItems().addAll(f.apply(EXAM.getIdexam()));
        list.getSelectionModel().select(index);
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }
    
    
}
