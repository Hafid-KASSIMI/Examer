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
package examer.db;

/**
 *
 * @author Sicut
 */
public class RoomPageInfos extends OutputPageInfos {

    private String title;
    private int numero;

    public RoomPageInfos(String title, int numero, String center, String direction, String branch, String level, String matter, int papers, int firstCode, int lastCode, String absents) {
        super(center, direction, branch, level, matter, papers, firstCode, lastCode, absents);
        this.title = title;
        this.numero = numero;
    }

    public RoomPageInfos(int numero, String center, String direction, String branch, String level, String matter, int papers, int firstCode, int lastCode, String absents) {
        super(center, direction, branch, level, matter, papers, firstCode, lastCode, absents);
        this.numero = numero;
    }

    public RoomPageInfos(String title, int numero) {
        this.title = title;
        this.numero = numero;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}
