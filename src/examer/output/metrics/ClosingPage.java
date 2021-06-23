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
package examer.output.metrics;

import org.sicut.util.pdf.AvailableFonts;
import org.sicut.util.pdf.LARectangle;

/**
 *
 * @author Sicut
 */
public class ClosingPage extends BaseTrialPage {
    
    private final LARectangle date, hour;
    private final LARectangle secretaryName, secretaryNum;

    public ClosingPage() {
        
        super();
        
        date =  new LARectangle();
        hour =  new LARectangle();
        secretaryName =  new LARectangle();
        secretaryNum =  new LARectangle();
        
        matter.setFormat(14, AvailableFonts.TIMES);
        matter.reset(56.731f, 243.355f, 178.938f, 30f);
        branch.setFormat(14, AvailableFonts.TIMES);
        branch.reset(278.19f, 245.097f, 174.487f, 30f);
        center.setFormat(14, AvailableFonts.TIMES);
        center.reset(57.055f, 200.053f, 139.923f, 30f);
        direction.setFormat(14, AvailableFonts.TIMES);
        direction.reset(277.923f, 199.779f, 216.084f, 30f);
        level.setFormat(14, AvailableFonts.TIMES);
        level.reset(278.086f, 222.045f, 212.084f, 30f);
        date.setFormat(14, AvailableFonts.TIMES);
        date.reset(278.316f, 266.325f, 220.581f, 30f);
        hour.setFormat(14, AvailableFonts.TIMES);
        hour.reset(56.641f, 266.474f, 172.288f, 30f);
        directorName.setFormat(14, AvailableFonts.TIMES);
        directorName.reset(340.148f, 368.09f, 154.223f, 24.121f);
        directorNum.setFormat(14, AvailableFonts.TIMES);
        directorNum.reset(268.488f, 368.09f, 71.184f, 24.121f);
        observerName.setFormat(14, AvailableFonts.TIMES);
        observerName.reset(340.148f, 392.707f, 154.223f, 24.121f);
        observerNum.setFormat(14, AvailableFonts.TIMES);
        observerNum.reset(268.488f, 392.707f, 71.184f, 24.121f);
        secretaryName.setFormat(14, AvailableFonts.TIMES);
        secretaryName.reset(340.181f, 417.309f, 154.223f, 24.121f);
        secretaryNum.setFormat(14, AvailableFonts.TIMES);
        secretaryNum.reset(268.488f, 417.309f, 71.184f, 24.121f);
    }

    public LARectangle getMatter() {
        return matter;
    }

    public LARectangle getBranch() {
        return branch;
    }

    public LARectangle getCenter() {
        return center;
    }

    public LARectangle getDirection() {
        return direction;
    }

    public LARectangle getLevel() {
        return level;
    }

    public LARectangle getTitle() {
        return title;
    }

    public LARectangle getNormalSession() {
        return normalSession;
    }

    public LARectangle getSecondSession() {
        return secondSession;
    }

    public LARectangle getDate() {
        return date;
    }

    public LARectangle getHour() {
        return hour;
    }

    public LARectangle getDirectorName() {
        return directorName;
    }

    public LARectangle getDirectorNum() {
        return directorNum;
    }

    public LARectangle getObserverName() {
        return observerName;
    }

    public LARectangle getObserverNum() {
        return observerNum;
    }

    public LARectangle getSecretaryName() {
        return secretaryName;
    }

    public LARectangle getSecretaryNum() {
        return secretaryNum;
    }
    
    
}
