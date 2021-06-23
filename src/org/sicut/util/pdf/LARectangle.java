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

package org.sicut.util.pdf;

import org.sicut.util.ALIGNMENT;

public class LARectangle implements Cloneable {
    private float x, y, width, height;
    private Format format;
    private final float padding = 2.5f;

    public LARectangle(float x, float y, float width, float height) {
        reset(x, y, width, height);
    }

    public LARectangle(float x, float y, float width, float height, Format format) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.format = format;
    }

    public LARectangle() {
        y = 0;
        x = 0;
    }

    public LARectangle(Format format) {
        y = 0;
        x = 0;
        this.format = format;
    }
    
    public LARectangle(float x, float y, float width, float height, int fontSize, String font) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.format = new Format(fontSize, font);
    }
    
    public final void reset(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void reset(float x, float y, float width, float height, Format format) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.format = format;
    }
    
    public void reset(float x, float y, float width, float height, int fontSize, String font) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.format = new Format(fontSize, font);
    }
    
    public void reset(float x, float y, float width, float height, int fontSize, String font, Boolean isBold) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.format = new Format(fontSize, font, isBold);
    }
    
    public float getX() {
        return x;
    }
    
    public float getTransformedX(float itemWidth) {
        return x + ( width - itemWidth ) / 2;
    }
    
    public float getTransformedX() {
        return x;
    }
    
    public float getTransformedX(float itemWidth, short hAlign) {
        float res = x;
        switch(hAlign) {
            case ALIGNMENT.LEFT:
                res = x + padding;
                break;
            case ALIGNMENT.RIGHT:
                res = x + width - itemWidth - padding;
                break;
            case ALIGNMENT.CENTER:
                res = x + ( width - itemWidth ) / 2;
                break;
        }
        return res;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getTransformedY(float pageHeight, float itemHeight) {
        return pageHeight - y - ( height + itemHeight ) / 2;
    }

    public float getTransformedY(float pageHeight) {
        return pageHeight - y;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
    
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public void setSize(float h, float w) {
        this.height = h;
        this.width = w;
    }
    
    public void setFormat(int fontSize, String font) {
        this.format = new Format(fontSize, font);
    }
    
    public void setFormat(int fontSize, String font, Boolean isBold) {
        this.format = new Format(fontSize, font, isBold);
    }

    public Format getFormat() {
        return format;
    }
    
    public void setYH(float y, float h) {
        this.height = h;
        this.y = y;
    }
    
    public void setXW(float x, float w) {
        this.x = x;
        this.width = w;
    }
    
    public void setFontSize(int fntSize) {
        this.format.setFontSize(fntSize);
    }
    
    public void setFont(String font) {
        this.format.setFont(font);
    }

    public int getFontSize() {
        return format.getFontSize();
    }

    public String getFont() {
        return format.getFont();
    }

    public Boolean getIsBold() {
        return format.getIsBold();
    }

    public void setIsBold(Boolean isBold) {
        format.setIsBold(isBold);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[ x : " + x + ", y : " + y + ", w : " + width + ", h : " + height + " ]";
    }
    
    public LARectangle transpose() {
        float tmp = width;
        width = height;
        height = tmp;
        return this;
    }
    
}
