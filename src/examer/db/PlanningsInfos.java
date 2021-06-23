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

import examer.Settings;
import examer.util.DateTime;
import java.time.Clock;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.MINUTES;

/**
 *
 * @author Sicut
 */
public class PlanningsInfos {
    private String branch;
    private String matter;
    private String startTime;
    private String endTime;
    private String duration;
    private String remaining;
    private String planDate;
    private int absents;
    private int femaleAbsents;

    public PlanningsInfos() {
    }

    public PlanningsInfos(String branch, String matter, String startTime, String endTime, String duration, String remaining) {
        this.branch = branch;
        this.matter = matter;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.remaining = remaining;
    }

    public PlanningsInfos(String branch, String matter, String startTime, String duration) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        this.branch = branch;
        this.matter = matter;
        this.startTime = startTime;
        this.duration = duration;
        endTime = "";
        remaining = "";
        try {
            LocalTime start, end, dur, now;
            start = LocalTime.parse(startTime, dtf);
            now = LocalTime.now(Clock.systemUTC());
            dur = LocalTime.parse(duration, dtf);
            end = start.plusHours(dur.getHour());
            end = end.plusMinutes(dur.getMinute());
            endTime = end.format(dtf);
            if ( now.isAfter(end) )
                remaining = "DONE";
            else if ( now.isBefore(start) )
                remaining = "WAITING";
            else {
                remaining = "RUNNING";
            }
        } catch ( Exception e ) { }
    }

    public PlanningsInfos(String branch, String matter, String startTime, String duration, String planDate, int absents, int femaleAbsents) {
        this(branch, matter, startTime, duration);
        this.absents = absents;
        this.planDate = planDate;
        this.femaleAbsents = femaleAbsents;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRemaining() {
        return Settings.I18N_BUNDLE.getString(remaining);
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public int getAbsents() {
        return absents;
    }

    public void setAbsents(int absents) {
        this.absents = absents;
    }

    public int getFemaleAbsents() {
        return femaleAbsents;
    }

    public void setFemaleAbsents(int femaleAbsents) {
        this.femaleAbsents = femaleAbsents;
    }

    public String getPlanDate() {
        return DateTime.getArabicDate(planDate);
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }
    
}
