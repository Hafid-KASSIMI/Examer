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
import examer.output.metrics.BranchPage;
import examer.db.BranchPageInfos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.sicut.util.ALIGNMENT;
import org.sicut.util.pdf.BasePDFFile;

/**
 *
 * @author H. KASSIMI
 */
public class BranchesPDF extends BasePDFFile {

    private final BranchPage page;
    
    public BranchesPDF() {
        super();
        page = new BranchPage();
        tpl = tempaltesDir + "branch.pdf";
    }
    
    public void generate(ArrayList<BranchPageInfos> bpi) {
        if ( bpi.isEmpty() )
            return;
        reset();
        pageHeight = doc.getPage(0).getBBox().getHeight();
        bpi.forEach(bp -> {
            PDPage pg = clonePage(doc.getPage(0));
            int n = 0;
            Iterator<BranchPageInfos> iter = bpi.stream().filter(a1 -> a1.getIdBranch() == bp.getIdBranch() && a1.getIdMatter() == bp.getIdMatter()).iterator();
            while ( iter.hasNext() ) {
                iter.next().setPortion(++n);
            }
            try {
                try (PDPageContentStream pcs = new PDPageContentStream(doc, pg, PDPageContentStream.AppendMode.APPEND, true, true)) {
                    placeBidirectionalString(page.getSession(), pcs, bp.getSession(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getCenter(), pcs, bp.getCenter(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDirection(), pcs, bp.getDirection(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getLevel(), pcs, bp.getLevel(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getBranch(), pcs, bp.getBranch(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getMatter(), pcs, bp.getMatter(), ALIGNMENT.RIGHT);
                    placeString(page.getFrom(), pcs, bp.getFirstCode() + "", ALIGNMENT.RIGHT);
                    placeString(page.getTo(), pcs, bp.getLastCode() + "", ALIGNMENT.RIGHT);
                    placeAndRotateString(page.getPortion(), pcs, bp.getPortion() + "/" + n, Math.PI / 4);
//                    placeString(page.getPortion(), pcs, bp.getPortion() + "/" + n, ALIGNMENT.RIGHT);
                    n = bp.getPapers();
                    placeString(page.getNumber(), pcs, ( n > 9 ? "" : "0" ) + n, ALIGNMENT.RIGHT);
                    if ( Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_INCLUDE_ABSENTS").equals("Y") ) {
                        n = bp.getnAbsents();
                        placeString(page.getAbsentsNumber(), pcs, ( n > 9 ? "" : "0" ) + n, ALIGNMENT.RIGHT);
                        if ( n > 0 )
                            wrapAndResizeString(page.getAbsents(), pcs, prepareToWrap(page.getAbsents(), bp.getAbsents()));
                        else
                            gray(pcs, page.getAbsents(), false);
                    }
                }
            } catch (IOException | ArabicShapingException | CloneNotSupportedException ex) {} 
        });
        save(Settings.PREF_BUNDLE.get("BRANCHES_OUTPUT_DESTINATION_FOLDER") + generateFileName("Branches", "pdf"));
    }
    
}
