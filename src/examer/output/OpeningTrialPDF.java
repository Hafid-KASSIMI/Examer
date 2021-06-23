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
import examer.db.TrialPageInfos;
import examer.output.metrics.OpeningPage;
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
public class OpeningTrialPDF extends BasePDFFile {

    private final OpeningPage page;
    
    public OpeningTrialPDF() {
        super();
        page = new OpeningPage();
        tpl = tempaltesDir + "opening.pdf";
    }
    
    public Boolean generate(ArrayList<TrialPageInfos> bpi) {
        if ( bpi.isEmpty() )
            return false;
        String acad = Settings.PREF_BUNDLE.get("SELECTED_ACADEMY");
        reset();
        pageHeight = doc.getPage(0).getBBox().getHeight();
        bpi.forEach(bp -> {
            PDPage pg = clonePage(doc.getPage(0));
            try {
                try (PDPageContentStream pcs = new PDPageContentStream(doc, pg, PDPageContentStream.AppendMode.APPEND, true, true)) {
                    placeBidirectionalString(page.getTitle(), pcs, Settings.PREF_BUNDLE.get("TRIAL_TITLE"));
                    placeBidirectionalString(Settings.PREF_BUNDLE.get("TRIAL_ORDINARY_SESSION").equals("Y") ? page.getNormalSession() : page.getSecondSession(), pcs, "\uea10");
                    placeBidirectionalString(page.getCenter(), pcs, bp.getCenter(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDirection(), pcs, bp.getDirection(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getLevel(), pcs, bp.getLevel(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getBranch(), pcs, bp.getBranch(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getMatter(), pcs, bp.getMatter(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getFrom(), pcs, bp.getHour(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDate1(), pcs, bp.getDate(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDate2(), pcs, bp.getDate(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getTime(), pcs, bp.getOpening(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getTo(), pcs, bp.getEnd(), ALIGNMENT.RIGHT);
                    placeBidirectionalString(page.getDirectorName(), pcs, Settings.PREF_BUNDLE.get("TRIAL_PRESIDENT_NAME"));
                    placeBidirectionalString(page.getDirectorNum(), pcs, Settings.PREF_BUNDLE.get("TRIAL_PRESIDENT_CODE"));
                    placeBidirectionalString(page.getObserverName(), pcs, Settings.PREF_BUNDLE.get("TRIAL_OBSERVER_NAME"));
                    placeBidirectionalString(page.getObserverNum(), pcs, Settings.PREF_BUNDLE.get("TRIAL_OBSERVER_CODE"));
                    wrapString(page.getKingdomAr(), pcs, Settings.PREF_BUNDLE.get("KINGDOM-AR").replaceAll("%new_line%", "\n") + "\nجهة " + bp.getRegionAR(), ALIGNMENT.LEFT);
                    wrapString(page.getKingdomAm(), pcs, Settings.PREF_BUNDLE.get("KINGDOM-TA").replaceAll("%new_line%", "\n") + "\nn " + bp.getRegionTA(), ALIGNMENT.RIGHT);
                    
                }
            } catch ( CloneNotSupportedException | IOException | ArabicShapingException ex) { }
            if ( Settings.PREF_BUNDLE.get("TRIALS_OUTPUT_DUPLICATE_PAGES").equals("Y") ) {
                clonePage(pg);
                clonePage(pg);
            }
        });
        return save(Settings.PREF_BUNDLE.get("TRIALS_OUTPUT_DESTINATION_FOLDER") + generateFileName("Trials", "pdf"));
    }
    
}
