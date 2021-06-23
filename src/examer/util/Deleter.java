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

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.scene.control.Button;

/**
 *
 * @author Sicut
 */
public class Deleter {
    
    private final int DURATION = 5;
    private int counter = DURATION;
    private final Button deleteBtn;
    private Timer runner;
    private TimerTask task;
    private final Consumer<Boolean> command;
    private Boolean done;
    private final String btnText;

    public Deleter(Button deleteBtn, Consumer<Boolean> command) {
        this.deleteBtn = deleteBtn;
        this.command = command;
        btnText = deleteBtn.getText();
        done = true;
    }
    
    public void start() {
        runner = new Timer();
        task = new TimerTask(){
            @Override
            public void run() {
                if ( --counter <= 0 ) {
                    Platform.runLater(() -> {
                        deleteBtn.setText(btnText);
                        command.accept(false);
                    });
                    done = true;
                    counter = DURATION;
                    cancel();
                    runner.cancel();
                }
                else {
                    Platform.runLater(() -> {
                        deleteBtn.setText(counter + "");
                    });
                }
            }
        };
        done = false;
        runner.scheduleAtFixedRate(task, 0, 1000);
        command.accept(true);
    }
    
    public void stop() {
        runner.cancel();
        command.accept(false);
        deleteBtn.setText(btnText);
        done = true;
        counter = DURATION;
    }

    public Boolean isDone() {
        return done;
    }
    
}
