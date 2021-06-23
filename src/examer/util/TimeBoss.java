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

import static examer.Settings.EXAM;
import static examer.Settings.VL_TABLE;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Sicut
 */
public class TimeBoss {
    
    private Timer runner;
    private TimerTask task;
    private Event nextEvent;
    private final SimpleBooleanProperty nextEventProperty = new SimpleBooleanProperty(false);
    private final SimpleStringProperty currentDate = new SimpleStringProperty("");
    private final SimpleIntegerProperty secProperty = new SimpleIntegerProperty();
    private final SimpleStringProperty currentTime = new SimpleStringProperty("");
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd % uuuu");
    private final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm");
    private long waitingMinutesToStart;

    public TimeBoss() {
        runner = null;
    }
    
    public void start() {
        runner = new Timer();        
        task = new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() -> {
                    nextPlanRefresh();
                });
            }
        };
        nextEvent = VL_TABLE.getNextEvent(EXAM.getIdexam());
        runner.scheduleAtFixedRate(task, 0, 400);
    }
    
    public void stop() {
        if ( runner != null )
            runner.cancel();
    }
    
    public SimpleBooleanProperty getNextEventProperty() {
        return nextEventProperty;
    }
    
    public void refresh() {
        nextEvent = VL_TABLE.getNextEvent(EXAM.getIdexam());
        waitingMinutesToStart = ( nextEvent != null ) ? nextEvent.calculateWaitingDuration(LocalDateTime.now()).abs().toMinutes() : 0;
    }
    
    private void nextPlanRefresh() {
        LocalDateTime now = LocalDateTime.now();
        if ( nextEvent != null ) {
            nextEvent.setWaitingDuration(now);
        }
        currentDate.set(DateTime.DAYS[now.getDayOfWeek().getValue()] + " " + now.format(dtf).replace("%", DateTime.MONTHS[now.getMonthValue()]));
        currentTime.set(now.format(dtf2));
        secProperty.set(now.getSecond());
    }

    public SimpleStringProperty getCurrentDate() {
        return currentDate;
    }

    public SimpleIntegerProperty getSecProperty() {
        return secProperty;
    }

    public Event getNextEvent() {
        return nextEvent;
    }

    public SimpleStringProperty getCurrentTime() {
        return currentTime;
    }

    public long getWaitingMinutesToStart() {
        return waitingMinutesToStart;
    }
    
}
