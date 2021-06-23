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
public class BranchPageInfos extends OutputPageInfos {
    
    private int idBranch, idMatter;
    private String session;
    private int nAbsents, portion;

    public BranchPageInfos(String session, int nAbsents, int portion, String center, String direction, String branch, String level, String matter, int papers, int firstCode, int lastCode, String absents) {
        super(center, direction, branch, level, matter, papers, firstCode, lastCode, absents);
        this.session = session;
        this.nAbsents = nAbsents;
        this.portion = portion;
    }

    public BranchPageInfos(String session, int nAbsents, String center, String direction, String branch, String level, String matter, int papers, int firstCode, int lastCode, String absents) {
        super(center, direction, branch, level, matter, papers, firstCode, lastCode, absents);
        this.session = session;
        this.nAbsents = nAbsents;
    }

    public BranchPageInfos(String session, int nAbsents, int portion) {
        this.session = session;
        this.nAbsents = nAbsents;
        this.portion = portion;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public int getnAbsents() {
        return nAbsents;
    }

    public void setnAbsents(int nAbsents) {
        this.nAbsents = nAbsents;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public int getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(int idBranch) {
        this.idBranch = idBranch;
    }

    public int getIdMatter() {
        return idMatter;
    }

    public void setIdMatter(int idMatter) {
        this.idMatter = idMatter;
    }
    
}
