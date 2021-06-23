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
package examer.util;

import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextFormatter;

/**
 *
 * @author Sicut
 */
public class IntegerSpinner {
    
    private final Spinner<Integer> sp;

    public IntegerSpinner(Spinner<Integer> sp) {
        this.sp = sp;
    }
    
    public void bind() {
        SpinnerValueFactory<Integer> spSFD = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        TextFormatter spFormatter = new TextFormatter(spSFD.getConverter(), spSFD.getValue());
        sp.setValueFactory(spSFD);
        sp.getEditor().setTextFormatter(spFormatter);
        spSFD.valueProperty().bindBidirectional(spFormatter.valueProperty());
    }
}
