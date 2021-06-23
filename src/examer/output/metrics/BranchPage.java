/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examer.output.metrics;

import org.sicut.util.pdf.AvailableFonts;
import org.sicut.util.pdf.LARectangle;
 
/**
 *
 * @author Sicut
 */
public class BranchPage extends BasePage {

    private final LARectangle session, absentsNumber, portion;
    
    public BranchPage() {
        super();
        float w = 427.438f, h = 30f;
        
        session =  new LARectangle();
        absentsNumber =  new LARectangle();
        portion =  new LARectangle();
        
        matter.setFormat(26, AvailableFonts.TIMES);
        branch.setFormat(26, AvailableFonts.TIMES);
        center.setFormat(26, AvailableFonts.TIMES);
        direction.setFormat(26, AvailableFonts.TIMES);
        level.setFormat(26, AvailableFonts.TIMES);
        number.setFormat(26, AvailableFonts.TIMES);
        from.setFormat(26, AvailableFonts.TIMES);
        to.setFormat(26, AvailableFonts.TIMES);
        absentsNumber.setFormat(26, AvailableFonts.TIMES);
        session.setFormat(26, AvailableFonts.TIMES);
        absents.setFormat(26, AvailableFonts.TIMES);
        portion.setFormat(30, AvailableFonts.ICOMOON);
        
        session.reset(288.155f, 45.11f, w, h);
        matter.reset(294.272f, 89.27f, w, h);
        branch.reset(195.612f, 133.43f, w, h);
        center.reset(220.932f, 177.59f, w, h);
        direction.reset(197.892f, 221.78f, w, h);
        level.reset(266.772f, 265.82f, w, h);
        number.reset(534.668f, 309.051f, 131.465f, h);
        from.reset(386.558f, 354.16f, 131.465f, h);
        to.reset(120.858f, 354.231f, 131.465f, h);
        absentsNumber.reset(523.388f, 398.32f, 131.465f, h);
        absents.reset(56.141f, 484.052f, 729.026f, 82.872f);
//        portion.reset(30f, 30f, 93.498f, 53.269f);
        portion.reset(0f, 64.441f, 112.429f, 112.429f);

    }

    public LARectangle getSession() {
        return session;
    }

    public LARectangle getAbsentsNumber() {
        return absentsNumber;
    }

    public LARectangle getPortion() {
        return portion;
    }
    
}
