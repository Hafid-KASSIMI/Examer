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
public class RoomPage extends BasePage {

    private final LARectangle title, numero;
    
    public RoomPage() {
        super();
        float w = 427.438f, h = 29.253f;
        
        title =  new LARectangle();
        numero =  new LARectangle();
        
        title.setFormat(36, AvailableFonts.TIMES, true);
        numero.setFormat(26, AvailableFonts.TIMES);
        matter.setFormat(26, AvailableFonts.TIMES);
        branch.setFormat(26, AvailableFonts.TIMES);
        center.setFormat(26, AvailableFonts.TIMES);
        direction.setFormat(26, AvailableFonts.TIMES);
        level.setFormat(26, AvailableFonts.TIMES);
        number.setFormat(26, AvailableFonts.TIMES);
        from.setFormat(26, AvailableFonts.TIMES);
        to.setFormat(26, AvailableFonts.TIMES);
        absents.setFormat(26, AvailableFonts.TIMES);
        
        title.reset(56.447f, 28.346f, 729.026f, 64.664f);
        numero.reset(589.619f, 134.44f, 96.634f, h);
        branch.reset(200.615f, 295.474f, w, h);
        center.reset(225.935f, 174.604f, w, h);
        matter.reset(299.275f, 335.694f, w, h);
        direction.reset(202.895f, 214.954f, w, h);
        level.reset(271.775f, 255.154f, w, h);
        number.reset(243.695f, 376.014f, w, h);
        from.reset(382.401f, 416.285f, 131.462f, h);
        to.reset(95.431f, 416.285f, 131.462f, h);
        absents.reset(56.141f, 496.286f, 729.026f, 71.125f);

    }

    public LARectangle getTitle() {
        return title;
    }

    public LARectangle getNumero() {
        return numero;
    }
    
}
