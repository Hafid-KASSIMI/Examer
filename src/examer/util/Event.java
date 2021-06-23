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

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Sicut
 */
public class Event {
    
    private LocalDateTime planDateTime;
    private short planType;
    private Duration waitingDuration, duration;

    public Event() {
        waitingDuration = null;
        planDateTime = null;
        planType = -1;
    }

    public LocalDateTime getPlanDateTime() {
        return planDateTime;
    }

    public void setPlanDateTime(LocalDateTime planDateTime) {
        this.planDateTime = planDateTime;
        setWaitingDuration(LocalDateTime.now());
    }

    public short getPlanType() {
        return planType;
    }

    public void setPlanType(short planType) {
        this.planType = planType;
    }

    public Duration getWaitingDuration() {
        return waitingDuration;
    }

    public void setWaitingDuration(Duration waitingDuration) {
        this.waitingDuration = waitingDuration;
    }

    public void setWaitingDuration(LocalDateTime dateTime) {
        waitingDuration = Duration.between(planDateTime, dateTime);
    }

    public Duration calculateWaitingDuration(LocalDateTime dateTime) {
        return Duration.between(planDateTime, dateTime);
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
