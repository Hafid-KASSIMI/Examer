/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/
 
package examer.output.metrics;

import org.sicut.util.pdf.LARectangle;

public abstract class BasePage {
    
    protected final LARectangle matter, branch, center, direction, level, number, from, to, absents;
    
    public BasePage() {
        matter =  new LARectangle();
        branch =  new LARectangle();
        center =  new LARectangle();
        direction =  new LARectangle();
        level =  new LARectangle();
        number =  new LARectangle();
        from =  new LARectangle();
        to =  new LARectangle();
        absents =  new LARectangle();
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

    public LARectangle getNumber() {
        return number;
    }

    public LARectangle getFrom() {
        return from;
    }

    public LARectangle getTo() {
        return to;
    }

    public LARectangle getAbsents() {
        return absents;
    }
}
