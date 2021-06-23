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
public class TrialPageInfos {
    
    private String matter, branch, center, direction, level;
    private String date, hour, duration, end, closing, opening, regionAR, regionTA;

    public TrialPageInfos(String matter, String branch, String center, String direction, String regionAR, String regionTA, String level, String date, String hour) {
        this.matter = matter;
        this.branch = branch;
        this.center = center;
        this.direction = direction;
        this.regionAR = regionAR;
        this.regionTA = regionTA;
        this.level = level;
        this.date = date;
        this.hour = hour;
    }

    public TrialPageInfos() {
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getRegionAR() {
        return regionAR;
    }

    public void setRegionAR(String regionAR) {
        this.regionAR = regionAR;
    }

    public String getRegionTA() {
        return regionTA;
    }

    public void setRegionTA(String regionTA) {
        this.regionTA = regionTA;
    }
    
    
    
}
