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
public class RoomsInfos {
    private int idRoom;
    private int numero;
    private int candidates;
    private int firstCode;
    private int lastCode;
    private int absents;
    private int cheaters;
    private int femaleAbsents;
    private int femaleCheaters;
    private int females;
    private String branch;

    public RoomsInfos() {
    }
    
    public RoomsInfos(int idRoom, int numero, int candidates, int firstCode, int lastCode, int absents, int cheaters, String branch) {
        this.idRoom = idRoom;
        this.numero = numero;
        this.candidates = candidates;
        this.firstCode = firstCode;
        this.lastCode = lastCode;
        this.absents = absents;
        this.cheaters = cheaters;
        this.branch = branch;
    }
    
    public RoomsInfos(int idRoom, int numero, int candidates, int firstCode, int lastCode, int absents, int cheaters, String branch, int femaleAbsents, int femaleCheaters, int females) {
        this.idRoom = idRoom;
        this.numero = numero;
        this.candidates = candidates;
        this.firstCode = firstCode;
        this.lastCode = lastCode;
        this.absents = absents;
        this.cheaters = cheaters;
        this.branch = branch;
        this.femaleAbsents = femaleAbsents;
        this.femaleCheaters = femaleCheaters;
        this.females = females;
    }

    public RoomsInfos(int idRoom, int numero, int candidates, int firstCode, int lastCode, String branch) {
        this.idRoom = idRoom;
        this.numero = numero;
        this.candidates = candidates;
        this.firstCode = firstCode;
        this.lastCode = lastCode;
        this.branch = branch;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCandidates() {
        return candidates;
    }

    public void setCandidates(int candidates) {
        this.candidates = candidates;
    }

    public int getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(int firstCode) {
        this.firstCode = firstCode;
    }

    public int getLastCode() {
        return lastCode;
    }

    public void setLastCode(int lastCode) {
        this.lastCode = lastCode;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public int getCheaters() {
        return cheaters;
    }

    public void setCheaters(int cheaters) {
        this.cheaters = cheaters;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getFemaleAbsents() {
        return femaleAbsents;
    }

    public void setFemaleAbsents(int femaleAbsents) {
        this.femaleAbsents = femaleAbsents;
    }

    public int getFemaleCheaters() {
        return femaleCheaters;
    }

    public void setFemaleCheaters(int femaleCheaters) {
        this.femaleCheaters = femaleCheaters;
    }

    public int getFemales() {
        return females;
    }

    public void setFemales(int females) {
        this.females = females;
    }
    
    
}
