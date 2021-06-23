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
public class OpeningPage extends BaseTrialPage {
    
    private final LARectangle date1, from, to, date2, time;
//    private final LARectangle permaName, permaNum;

    public OpeningPage() {
        super();
        date1 =  new LARectangle();
        from =  new LARectangle();
        to =  new LARectangle();
        date2 =  new LARectangle();
        time =  new LARectangle();
//        permaName =  new LARectangle();
//        permaNum =  new LARectangle();
        
        center.setFormat(14, AvailableFonts.TIMES);
        center.reset(270.111f, 200.053f, 196.173f, 30f);
        direction.setFormat(14, AvailableFonts.TIMES);
        direction.reset(56.693f, 199.779f, 124.375f, 30f);
        matter.setFormat(14, AvailableFonts.TIMES);
        matter.reset(289.889f, 291.826f, 216.294f, 30f);
        level.setFormat(14, AvailableFonts.TIMES);
        level.reset(56.693f, 290.805f, 173.62f, 30f);
        branch.setFormat(14, AvailableFonts.TIMES);
        branch.reset(289.889f, 313.737f, 202.702f, 30f);
        date1.setFormat(14, AvailableFonts.TIMES);
        date1.reset(56.693f, 312.885f, 190.968f, 30f);
        from.setFormat(14, AvailableFonts.TIMES);
        from.reset(289.889f, 335.114f, 185.872f, 30f);
        to.setFormat(14, AvailableFonts.TIMES);
        to.reset(56.693f, 335.114f, 156.818f, 30f);
        date2.setFormat(14, AvailableFonts.TIMES);
        date2.reset(334.265f, 397.745f, 168.446f, 30f);
        time.setFormat(14, AvailableFonts.TIMES);
        time.reset(56.693f, 397.894f, 206.434f, 30f);
        directorName.setFormat(14, AvailableFonts.TIMES);
        directorName.reset(400.75f, 538.391f, 137.18f, 24.121f);
        directorNum.setFormat(14, AvailableFonts.TIMES);
        directorNum.reset(336.297f, 538.391f, 63.979f, 24.121f);
        observerName.setFormat(14, AvailableFonts.TIMES);
        observerName.reset(400.762f, 562.996f, 137.18f, 24.121f);
        observerNum.setFormat(14, AvailableFonts.TIMES);
        observerNum.reset(336.302f, 562.996f, 63.979f, 24.121f);
//        permaName.setFormat(14, AvailableFonts.TIMES);
//        permaName.reset(400.75f, 587.621f, 63.979f, 24.121f);
//        permaNum.setFormat(14, AvailableFonts.TIMES);
//        permaNum.reset(336.297f, 587.621f, 63.979f, 24.121f);
    }

    public LARectangle getDate1() {
        return date1;
    }

    public LARectangle getFrom() {
        return from;
    }

    public LARectangle getTo() {
        return to;
    }

    public LARectangle getDate2() {
        return date2;
    }

    public LARectangle getTime() {
        return time;
    }

//    public LARectangle getPermaName() {
//        return permaName;
//    }
//
//    public LARectangle getPermaNum() {
//        return permaNum;
//    }
//    
}
