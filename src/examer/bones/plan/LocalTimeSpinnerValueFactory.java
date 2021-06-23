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

import java.time.LocalTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.converter.LocalTimeStringConverter;

/**
 *
 * @author Sicut
 */
public class LocalTimeSpinnerValueFactory extends SpinnerValueFactory<LocalTime> {

        public LocalTimeSpinnerValueFactory(LocalTime min, LocalTime max) {
            this(min, max, min);
        }

        public LocalTimeSpinnerValueFactory(LocalTime min, LocalTime max, LocalTime initialValue) {
            this(min, max, initialValue, 1);
        }

        public LocalTimeSpinnerValueFactory(LocalTime min, LocalTime max, LocalTime initialValue, int amountToStepBy) {
            setMin(min);
            setMax(max);
            setAmountToStepBy(amountToStepBy);
            setConverter(new LocalTimeStringConverter());

            valueProperty().addListener((o, oldValue, newValue) -> {
                if (newValue.isBefore(getMin())) {
                    setValue(getMin());
                } else if (newValue.isAfter(getMax())) {
                    setValue(getMax());
                }
            });
            setValue(initialValue.compareTo(min) >= 0 && initialValue.compareTo(max) <= 0 ? initialValue : min);
        }

        private SimpleObjectProperty<LocalTime> min = new SimpleObjectProperty<LocalTime>(this, "min") {
            @Override protected void invalidated() {
                LocalTime currentValue = LocalTimeSpinnerValueFactory.this.getValue();
                if (currentValue == null) {
                    return;
                }

                LocalTime newMin = get();
                if (newMin.isAfter(getMax())) {
                    setMin(getMax());
                    return;
                }

                if (currentValue.isBefore(newMin)) {
                    LocalTimeSpinnerValueFactory.this.setValue(newMin);
                }
            }
        };

        public final void setMin(LocalTime value) {
            min.set(value);
        }
        public final LocalTime getMin() {
            return min.get();
        }

        public final SimpleObjectProperty<LocalTime> minProperty() {
            return min;
        }

        private SimpleObjectProperty<LocalTime> max = new SimpleObjectProperty<LocalTime>(this, "max") {
            @Override protected void invalidated() {
                LocalTime currentValue = LocalTimeSpinnerValueFactory.this.getValue();
                if (currentValue == null) {
                    return;
                }

                LocalTime newMax = get();
                if (newMax.isBefore(getMin())) {
                    setMax(getMin());
                    return;
                }

                if (currentValue.isAfter(newMax)) {
                    LocalTimeSpinnerValueFactory.this.setValue(newMax);
                }
            }
        };

        public final void setMax(LocalTime value) {
            max.set(value);
        }
        public final LocalTime getMax() {
            return max.get();
        }

        public final SimpleObjectProperty<LocalTime> maxProperty() {
            return max;
        }

        private SimpleIntegerProperty amountToStepBy = new SimpleIntegerProperty(this, "amountToStepBy");
        public final void setAmountToStepBy(int value) {
            amountToStepBy.set(value);
        }
        public final int getAmountToStepBy() {
            return amountToStepBy.get();
        }

        public final SimpleIntegerProperty amountToStepByProperty() {
            return amountToStepBy;
        }


        @Override public void decrement(int steps) {
            final LocalTime min = getMin();
            final LocalTime newIndex = getValue().minusMinutes(steps * getAmountToStepBy());
            setValue(newIndex.compareTo(min) >= 0 ? newIndex : min);
        }

        @Override public void increment(int steps) {
            final LocalTime max = getMax();
            final LocalTime newIndex = getValue().plusMinutes(steps * getAmountToStepBy());
            setValue(newIndex.compareTo(max) <= 0 ? newIndex : max);
        }
    }