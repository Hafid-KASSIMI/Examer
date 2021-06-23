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

import com.ibm.icu.text.ArabicShaping;
import com.ibm.icu.text.ArabicShapingException;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.util.Matrix;
import org.sicut.util.ALIGNMENT;
import org.sicut.util.Misc;

public class BasePDFFile {
    protected PDDocument doc;
    protected String tpl;
    protected final int grayingGap;
    protected final float lineGap = 6f;
    protected final String tempaltesDir = "/examer/output/resources/";
    protected PDFont tahomaBd, times, timesBd, tahoma, maghribi, tifinaghe, icomoon;
    protected float pageHeight;
    protected final ArabicShaping AR_SHAPING = new ArabicShaping(ArabicShaping.LETTERS_SHAPE);
    
    public BasePDFFile(){
        grayingGap = 24;
    }
    
    public final void reset(){
        try {
            doc = PDDocument.load(getClass().getResource(tpl).openStream());
            tahomaBd = PDType0Font.load(doc, getClass().getResource(AvailableFonts.TAHOMA).openStream());
            tahoma = PDType0Font.load(doc, getClass().getResource(AvailableFonts.TAHOMA_NORMAL).openStream());
            times = PDType0Font.load(doc, getClass().getResource(AvailableFonts.TIMES_NORMAL).openStream());
            timesBd = PDType0Font.load(doc, getClass().getResource(AvailableFonts.TIMES).openStream());
            maghribi = PDType0Font.load(doc, getClass().getResource(AvailableFonts.MAGHRIBI).openStream());
            tifinaghe = PDType0Font.load(doc, getClass().getResource(AvailableFonts.TIFINAGH).openStream());
            icomoon = PDType0Font.load(doc, getClass().getResource(AvailableFonts.ICOMOON).openStream());
        } catch (IOException ex) {
            doc = null;
        }
    }
    
    public boolean save(String path) {
        doc.removePage(0);
        try ( FileOutputStream fos = new FileOutputStream(path) ) {
            doc.save(fos);
            doc.close();
            return true;
        } catch (IOException ex) { 
//            System.out.println(ex);
            return false;
        }
    }
    
    protected PDPage clonePage(PDPage srcPg){
        COSDictionary dict = new COSDictionary(srcPg.getCOSObject());
        dict.removeItem(COSName.ANNOTS);
        PDPage np = new PDPage(dict);
        List<PDStream> x = new ArrayList();
        Iterator<PDStream> y = srcPg.getContentStreams();
        while (y.hasNext()){
            x.add(y.next());
        }
        np.setContents(x);
        doc.addPage(np);
        return np;
    }
    
    protected PDFont getFont(String name) {
        PDFont f = timesBd;
        switch ( name ) {
            case AvailableFonts.TAHOMA:
                f = tahomaBd;
                break;
            case AvailableFonts.TAHOMA_NORMAL:
                f = tahoma;
                break;
            case AvailableFonts.TIMES_NORMAL:
                f = times;
                break;
            case AvailableFonts.MAGHRIBI:
                f = maghribi;
                break;
            case AvailableFonts.TIFINAGH:
                f = tifinaghe;
                break;
            case AvailableFonts.ICOMOON:
                f = icomoon;
                break;
        }
        return f;
    }
    
    protected Short getAlignment(String alignment) {
        switch ( alignment ) {
            case "L":
                return ALIGNMENT.LEFT;
            case "R":
                return ALIGNMENT.RIGHT;
            default:
                return ALIGNMENT.CENTER;
        }
    }
    
    protected void placeString(LARectangle rectangle, PDPageContentStream pcs, String str) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        if ( Misc.isArabic(str) )
            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }
    
    protected void placeString(LARectangle rectangle, PDPageContentStream pcs, String str, short hAlign) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        if ( Misc.isArabic(str) )
            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef, hAlign), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }
    
    protected void placeString(LARectangle rectangle, PDPageContentStream pcs, Matrix mx, String str, short hAlign) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        if ( Misc.isArabic(str) )
            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.setTextMatrix(mx);
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef, hAlign), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }
    
    protected String prepareToWrap(LARectangle rectangle, String str) throws ArabicShapingException, IOException {
        String result = "";
        String line = "";
        float fntSize, coef, w;
        PDFont fnt;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        w = rectangle.getWidth();
        coef = fntSize / 1000f;
        String[] parts = str.replace("\n", " ").split(" ");
        for ( String part : parts ) {
            String tmp;
            if ( Misc.isArabic(part) ) {
                part = new StringBuilder(AR_SHAPING.shape(part)).reverse().toString();
                tmp = part + " " + line;
            }
            else {
                tmp = line + " " + part;
            }
            if ( fnt.getStringWidth(tmp) * coef > w ) {
                result += line.trim() + "\n";
                line = part;
            }
            else {
                line = tmp;
            }
        }
        if ( !line.isEmpty() )
            result += line;
        return result;
    }
    
    protected void wrapString(LARectangle rectangle, PDPageContentStream pcs, String str) throws IOException, ArabicShapingException, CloneNotSupportedException {
        String[] lines = str.split("\n");
        int n = lines.length;
        float h = rectangle.getHeight() / n;
        for ( int i = 0; i < n; i++ ) {
            LARectangle box = (LARectangle) rectangle.clone();
            box.setHeight(box.getHeight() / n);
            box.setY(box.getY() + h * i);
            placeString(box, pcs, lines[i]);
        }
    }
    
    protected void wrapAndResizeString(LARectangle rectangle, PDPageContentStream pcs, String str) throws IOException, ArabicShapingException, CloneNotSupportedException {
        String[] lines = str.split("\n");
        int n = lines.length;
        float h = rectangle.getHeight() / n;
        float height;
        height = getFont(rectangle.getFont()).getFontDescriptor().getCapHeight() * rectangle.getFontSize() / 1000f;
        if ( height > ( h - lineGap ) ) {
            int org = rectangle.getFontSize();
            rectangle.setFontSize(rectangle.getFontSize() - 1);
            wrapAndResizeString(rectangle, pcs, prepareToWrap(rectangle, str));
            rectangle.setFontSize(org);
        }
        else {
            for ( int i = 0; i < n; i++ ) {
                LARectangle box = (LARectangle) rectangle.clone();
                box.setHeight(h);
                box.setY(box.getY() + h * i);
                placeString(box, pcs, lines[i]);
            }
        }
    }
    
    protected void wrapString(LARectangle rectangle, PDPageContentStream pcs, String str, short hAlign) throws IOException, ArabicShapingException, CloneNotSupportedException {
        String[] lines = str.split("\n");
        int n = lines.length;
        float h = rectangle.getHeight() / n;
        for ( int i = 0; i < n; i++ ) {
            LARectangle box = (LARectangle) rectangle.clone();
            box.setHeight(box.getHeight() / n);
            box.setY(box.getY() + h * i);
            placeString(box, pcs, lines[i], hAlign);
        }
    }
    
    protected void wrapString(LARectangle rectangle, PDPageContentStream pcs, Matrix mx, String str, short hAlign) throws IOException, ArabicShapingException, CloneNotSupportedException {
        String[] lines = str.split("\n");
        int n = lines.length;
        float h = rectangle.getHeight() / n;
        for ( int i = 0; i < n; i++ ) {
            LARectangle box = (LARectangle) rectangle.clone();
            box.setHeight(box.getHeight() / n);
            box.setY(box.getY() + h * i);
            placeString(box, pcs, mx, lines[i], hAlign);
        }
    }
    
//    protected void placeAndRotateString(LARectangle rectangle, PDPageContentStream pcs, String str, double theta) throws IOException, ArabicShapingException {
//        float fntSize, coef;
//        PDFont fnt;
//        Matrix mx;
//        float x, y;
//        fntSize = rectangle.getFontSize();
//        fnt = getFont(rectangle.getFont());
//        coef = fntSize / 1000f;
//        coef /= 2;
//        x = rectangle.getTransformedX();
//        y = rectangle.getTransformedY(pageHeight);
//        if ( Misc.isArabic(str) )
//            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
//        pcs.setFont(fnt, fntSize);
//        pcs.beginText();
//        mx = new Matrix();
//        mx.translate(x + 100, 0);
//        mx.rotate(theta);
////        mx.translate(rectangle.getHeight() / 2 - fnt.getStringWidth(str) * coef, rectangle.getWidth() / 2 - (fnt.getFontDescriptor().getCapHeight() - fnt.getFontDescriptor().getDescent()) * coef);
//        pcs.setTextMatrix(mx);
//        pcs.showText(str);
//        pcs.endText();
//    }
    
    protected void placeAndMinusPiRotateString(LARectangle rectangle, PDPageContentStream pcs, String str) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        Matrix mx;
        float x, y;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        coef /= 2;
        x = rectangle.getTransformedX();
        y = rectangle.getTransformedY(pageHeight);
        if ( Misc.isArabic(str) )
            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        mx = new Matrix();
        mx.translate(x, y);
        mx.rotate(-Math.PI / 2);
        mx.translate(rectangle.getHeight() / 2 - fnt.getStringWidth(str) * coef, rectangle.getWidth() / 2 - (fnt.getFontDescriptor().getCapHeight() - fnt.getFontDescriptor().getDescent()) * coef);
        pcs.setTextMatrix(mx);
        pcs.showText(str);
        pcs.endText();
    }
    
    protected void placeAndRotateString(LARectangle rectangle, PDPageContentStream pcs, String str, double theta) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        Matrix mx;
        float x, y;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        coef /= 2;
        x = rectangle.getTransformedX();
        y = rectangle.getTransformedY(pageHeight);
        if ( Misc.isArabic(str) )
            str = Misc.cleanReversedAR(new StringBuilder(AR_SHAPING.shape(str)).reverse().toString());
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        mx = new Matrix();
        mx.translate(x, y);
        mx.rotate(theta);
//        mx.translate(0, 20);
        mx.translate(rectangle.getHeight() / 2 - fnt.getStringWidth(str) * coef, -rectangle.getWidth() / 2 + (fnt.getFontDescriptor().getCapHeight() - fnt.getFontDescriptor().getDescent()) * coef);
        pcs.setTextMatrix(mx);
        pcs.showText(str);
        pcs.endText();
    }
    
    protected void placeBidirectionalString(LARectangle rectangle, PDPageContentStream pcs, String string1, String string2) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        String str;
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        if ( Misc.isArabic(string1) )
            string1 = new StringBuilder(AR_SHAPING.shape(string1)).reverse().toString();
        if ( Misc.isArabic(string2) )
            string2 = new StringBuilder(AR_SHAPING.shape(string2)).reverse().toString();
        str = string1 + " " + string2;
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }

    protected void placeBidirectionalString(LARectangle rectangle, PDPageContentStream pcs, String string) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        String str = "";
        String parts[] = string.split(" ");
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        for ( String part : parts ) {
            str = ( ( Misc.isArabic(part) ) ? new StringBuilder(AR_SHAPING.shape(part)).reverse().toString() : part ) + " " + str;
        }
        str = str.trim();
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }

    protected void placeBidirectionalString(LARectangle rectangle, PDPageContentStream pcs, String string, short hAlign) throws IOException, ArabicShapingException {
        float fntSize, coef;
        PDFont fnt;
        String str = "";
        String parts[] = string.split(" ");
        fntSize = rectangle.getFontSize();
        fnt = getFont(rectangle.getFont());
        coef = fntSize / 1000f;
        for ( String part : parts ) {
            str = ( ( Misc.isArabic(part) ) ? new StringBuilder(AR_SHAPING.shape(part)).reverse().toString() : part ) + " " + str;
        }
        str = str.trim();
        pcs.setFont(fnt, fntSize);
        pcs.beginText();
        pcs.newLineAtOffset(rectangle.getTransformedX(fnt.getStringWidth(str) * coef, hAlign), rectangle.getTransformedY(pageHeight, ( fnt.getFontDescriptor().getCapHeight()) * coef));
        pcs.showText( str );
        pcs.endText();
    }

    protected void gray(PDPageContentStream pcs, LARectangle rect, Boolean whiten) throws IOException {
        float startX, endX, startY, endY, x, y, wX, hY, tg;
        x = startX = rect.getX();
        y = startY = rect.getTransformedY(pageHeight);
        wX = rect.getWidth();
        hY = rect.getHeight();
        if ( whiten ) {
            pcs.setNonStrokingColor(Color.WHITE);
            pcs.addRect(x, y, wX, -hY);
            pcs.fill();
        }
        endX = x + wX + hY + 0.001f;
        endY = y - hY - wX + 0.001f;
        tg = (float) Math.tan(Math.PI / 4);
        hY = startY - hY;
        wX = startX + wX;
        pcs.setStrokingColor(new Color(128, 128, 128));
        while ( (x += grayingGap) < endX && (y -= grayingGap) > endY ) {
            if ( y < hY ) {
                pcs.moveTo(startX + (hY - y) * tg, hY);
            }
            else {
                pcs.moveTo(startX, y);
            }
            if ( x > wX ) {
                pcs.lineTo(wX, startY - (x - wX) * tg);
            }
            else {
                pcs.lineTo(x, startY);
            }
        }
        pcs.closeAndStroke();
        pcs.setNonStrokingColor(Color.BLACK);
    }
    
    protected String generateFileName(String name, String extension) {
        return "/" + name + " " + DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss").format(LocalDateTime.now()) + "." + extension;
    }
}
