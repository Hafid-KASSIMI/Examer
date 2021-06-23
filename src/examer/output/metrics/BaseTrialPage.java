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
public class BaseTrialPage {
    
    protected final LARectangle matter, branch, center, direction, level;
    protected final LARectangle title, normalSession, secondSession;
    protected final LARectangle directorName, directorNum;
    protected final LARectangle observerName, observerNum;
    protected final LARectangle kingdomAr, kingdomAm;

    public BaseTrialPage() {
        matter =  new LARectangle();
        branch =  new LARectangle();
        center =  new LARectangle();
        direction =  new LARectangle();
        level =  new LARectangle();
        title =  new LARectangle();
        normalSession =  new LARectangle();
        secondSession =  new LARectangle();
        directorName =  new LARectangle();
        directorNum =  new LARectangle();
        observerName =  new LARectangle();
        observerNum =  new LARectangle();
        kingdomAr =  new LARectangle();
        kingdomAm =  new LARectangle();
        
        title.setFormat(14, AvailableFonts.TIMES);
        title.reset(153.275f, 108.375f, 288.77f, 30f);
        normalSession.setFormat(14, AvailableFonts.ICOMOON);
        normalSession.reset(402.841f, 143.455f, 16f, 16f);
        secondSession.setFormat(14, AvailableFonts.ICOMOON);
        secondSession.reset(261.322f, 143.455f, 16f, 16f);
        kingdomAr.setFormat(10, AvailableFonts.MAGHRIBI);
        kingdomAr.reset(348.57f, 23.588f, 190f, 80.178f);
        kingdomAm.setFormat(9, AvailableFonts.TIFINAGH);
        kingdomAm.reset(56.728f, 23.588f, 190f, 80.178f);
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

    public LARectangle getKingdomAr() {
        return kingdomAr;
    }

    public LARectangle getKingdomAm() {
        return kingdomAm;
    }
    
}
