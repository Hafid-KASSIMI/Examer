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

import java.util.Locale;
import javafx.scene.control.DatePicker;

/**
 *
 * @author Sicut
 */
public final class ARDatePicker {
    
    public static void apply(DatePicker dp) {
        Locale org = Locale.getDefault(Locale.Category.FORMAT);
        dp.setOnAction(evt -> Locale.setDefault(Locale.Category.FORMAT, org));
        dp.setOnHiding(evt -> Locale.setDefault(Locale.Category.FORMAT, org));
        dp.setOnShowing(evt -> Locale.setDefault(Locale.Category.FORMAT, new Locale("AR")));
    }
    
}
