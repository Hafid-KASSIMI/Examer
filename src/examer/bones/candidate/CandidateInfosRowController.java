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
package examer.bones.candidate;

import examer.db.Candidate;
import examer.util.Deleter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Sicut
 */
public class CandidateInfosRowController implements Initializable {

    @FXML private Label codeLbl;
    @FXML private Button deleteBtn;
    @FXML private ToggleButton maleTB, femaleTB, undefinedTB, englishTB, spanishTB, italianTB, germanTB;
    @FXML private ToggleGroup genderTG, languageTG;

    private final ArrayList<ToggleButton> langTBs;
    private final String langAbrevs = "##ENCAITGE";
    private Candidate cand;
    private final SimpleBooleanProperty rowDeleted = new SimpleBooleanProperty(false);

    public CandidateInfosRowController() {
        langTBs = new ArrayList();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        langTBs.addAll(Arrays.asList(undefinedTB, englishTB, spanishTB, italianTB, germanTB));
        genderTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            if ( cur != null && cand != null ) {
                cand.setGender(cur == femaleTB ? "F" : "M");
            }
            else if ( cur == null ) {
                genderTG.selectToggle(old);
            }
        });
        languageTG.selectedToggleProperty().addListener((obs, old, cur) -> {
            if ( cur != null && cand != null ) {
                cand.setIdsfl(langTBs.indexOf(cur));
            }
            else if ( cur == null ) {
                languageTG.selectToggle(old);
            }
        });
        Deleter deleter = new Deleter(deleteBtn, b -> dummy(b));
        deleteBtn.setOnAction(evt -> {
            if ( deleter.isDone() )
                 deleter.start();
            else {
                deleter.stop();
                rowDeleted.setValue(true);
            }
        });
    }
    
    public void setLanguage(String abrev) {
        int i = langAbrevs.indexOf(abrev);
        if ( i >= 0 )
            languageTG.selectToggle(langTBs.get(i / 2));
    }
    
    public void setLanguage(int idSfl) {
        if ( idSfl >= 0 && idSfl < langTBs.size() )
            languageTG.selectToggle(langTBs.get(idSfl));
    }
    
    public String getLanguage() {
        Toggle t = languageTG.getSelectedToggle();
        if ( t != null )
            return langAbrevs.substring(langTBs.indexOf(t) * 2, 2);
        return "##";
    }
    
    public void setCode(String code) {
        codeLbl.setText(code);
    }
    
    public void setCode(int code) {
        codeLbl.setText(code + "");
    }
    
    public void setGender(String gender) {
        genderTG.selectToggle(gender.equals("F") ? femaleTB : maleTB);
    }
    
    public String getGender() {
        Toggle t = genderTG.getSelectedToggle();
        if ( t != null )
            return t.equals(femaleTB) ? "F" : "M";
        return "M";
    }

    public Candidate getCandidate() {
        return cand;
    }

    public void setCandidate(Candidate cand) {
        this.cand = cand;
        setCode(cand.getCode());
        setGender(cand.getGender());
        setLanguage(cand.getIdsfl());
    }

    private void dummy(boolean b) {
        
    }
    
    public SimpleBooleanProperty getRowDeleted() {
        return rowDeleted;
    }
    
}
