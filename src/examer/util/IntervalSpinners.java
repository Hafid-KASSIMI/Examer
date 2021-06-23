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
public class IntervalSpinners {
    
    private final Spinner<Integer> from, to;

    public IntervalSpinners(Spinner<Integer> from, Spinner<Integer> to) {
        this.from = from;
        this.to = to;
    }
    
    public void bind() {
        SpinnerValueFactory<Integer> startSFD = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        SpinnerValueFactory<Integer> endSFD = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1);
        TextFormatter startFormatter = new TextFormatter(startSFD.getConverter(), startSFD.getValue());
        TextFormatter endFormatter = new TextFormatter(endSFD.getConverter(), endSFD.getValue());
        from.setValueFactory(startSFD);
        startSFD.valueProperty().addListener((observable, oldVal, newVal) -> {
            if ( newVal > to.getValue() )
                to.getValueFactory().setValue(newVal);
        });
        from.getEditor().setTextFormatter(startFormatter);
        startSFD.valueProperty().bindBidirectional(startFormatter.valueProperty());
        
        to.setValueFactory(endSFD);
        endSFD.valueProperty().addListener((observable, oldVal, newVal) -> {
            if ( newVal < from.getValue() )
                from.getValueFactory().setValue(newVal);
        });
        to.getEditor().setTextFormatter(endFormatter);
        endSFD.valueProperty().bindBidirectional(endFormatter.valueProperty());
    }
}
