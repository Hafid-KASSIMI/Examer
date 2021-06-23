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
package examer.output;

import com.ibm.icu.text.ArabicShapingException;
import examer.Settings;
import examer.output.metrics.RoomPage;
import examer.db.RoomPageInfos;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.sicut.util.ALIGNMENT;
import org.sicut.util.pdf.BasePDFFile;

/**
 *
 * @author H. KASSIMI
 */
public class RoomsPDF extends BasePDFFile {

    private final RoomPage page;
    
    public RoomsPDF() {
        super();
        page = new RoomPage();
        tpl = tempaltesDir + "room.pdf";
    }
    
    public void generate(ArrayList<RoomPageInfos> rpi, String title) {
        if ( rpi.isEmpty() )
            return;
        reset();
        rpi.forEach(rp -> {
            PDPage pg = clonePage(doc.getPage(0));
            pageHeight = pg.getBBox().getHeight();
            try {
                try (PDPageContentStream pcs = new PDPageContentStream(doc, pg, PDPageContentStream.AppendMode.APPEND, true, true)) {
                    placeBidirectionalString(page.getTitle(), pcs, title);
                    placeString(page.getNumero(), pcs, ( rp.getNumero() > 9 ? "" : "0" ) + rp.getNumero(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getCenter(), pcs, rp.getCenter(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDirection(), pcs, rp.getDirection(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getLevel(), pcs, rp.getLevel(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getBranch(), pcs, rp.getBranch(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getMatter(), pcs, rp.getMatter(), ALIGNMENT.RIGHT);
                    placeString(page.getFrom(), pcs, rp.getFirstCode() + "", ALIGNMENT.RIGHT);
                    placeString(page.getTo(), pcs, rp.getLastCode() + "", ALIGNMENT.RIGHT);
                    placeString(page.getNumber(), pcs, ( rp.getPapers() > 9 ? "" : "0" ) + rp.getPapers(), ALIGNMENT.RIGHT);
                    if ( Settings.PREF_BUNDLE.get("ROOMS_OUTPUT_INCLUDE_ABSENTS").equals("Y") ) {
                        if ( rp.getAbsents().isEmpty() )
                            gray(pcs, page.getAbsents(), false);
                        else
                            wrapString(page.getAbsents(), pcs, prepareToWrap(page.getAbsents(), rp.getAbsents()));
                    }
                }
            } catch (IOException | ArabicShapingException | CloneNotSupportedException ex) { } 
        });
        save(Settings.PREF_BUNDLE.get("ROOMS_OUTPUT_DESTINATION_FOLDER") + generateFileName("Rooms", "pdf"));
    }
    
}
