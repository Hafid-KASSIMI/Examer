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
package examer.bones.candidate;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Sicut
 * @param <T>
 */
public class CustomCheckBox<T> extends CheckBox {

    private T o;
    
    public CustomCheckBox(T o) {
        super();
        this.o = o;
        setText(o.toString());
        setMinWidth(80);
    }

    public CustomCheckBox(T o, String text) {
        super(text);
        this.o = o;
        setMinWidth(80);
    }

    public T getObject() {
        return o;
    }

    public void setObject(T o) {
        this.o = o;
    }
    
}
