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
public class OutputPageInfos {
    
    protected String center, direction, branch, level, matter;
    protected int papers, firstCode, lastCode;
    protected String absents;

    public OutputPageInfos(String center, String direction, String branch, String level, String matter, int papers, int firstCode, int lastCode, String absents) {
        this.center = center;
        this.direction = direction;
        this.branch = branch;
        this.level = level;
        this.matter = matter;
        this.papers = papers;
        this.firstCode = firstCode;
        this.lastCode = lastCode;
        this.absents = absents;
    }

    public OutputPageInfos() {
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public int getPapers() {
        return papers;
    }

    public void setPapers(int papers) {
        this.papers = papers;
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

    public String getAbsents() {
        return absents;
    }

    public void setAbsents(String absents) {
        this.absents = absents;
    }

    
}
