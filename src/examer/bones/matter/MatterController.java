package examer.bones.matter;

import static examer.Settings.MATTER;
import examer.db.Matter;
import examer.util.Deleter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hafid KASSIMI
 */
public class MatterController implements Initializable {

    @FXML private TextField matterNameTF, newMatterNameTF;
    @FXML private Button addBtn, refreshBtn, updateBtn, deleteBtn, doUpdateBtn;
    @FXML private ComboBox<Matter> mattersCMB;
    @FXML private VBox updateVB;

    public MatterController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Deleter deleter = new Deleter(deleteBtn, b -> setDisable(b));
        
        updateVB.visibleProperty().addListener((obs, old, cur) -> {
            updateVB.setManaged(cur);
        });
        updateVB.setVisible(false);
        
        doUpdateBtn.setOnAction(evt -> {
            String name = newMatterNameTF.getText();
            if ( name.isEmpty() || MATTER.count(name) > 0 )
                return;
            MATTER.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
            MATTER.setLabel(name);
            if ( MATTER.update() ) {
                updateVB.setVisible(false);
                refresh();
            }
        });
        
        updateBtn.setOnAction(evt -> {
            if ( mattersCMB.getSelectionModel().isEmpty() )
                return;
            updateVB.setVisible(true);
            newMatterNameTF.setText(mattersCMB.getSelectionModel().getSelectedItem().getLabel());
        });
        
        deleteBtn.setOnAction(evt -> {
            updateVB.setVisible(false);
            if ( !mattersCMB.getSelectionModel().isEmpty() ) {
                if ( deleter.isDone() )
                    deleter.start();
                else {
                    int id = mattersCMB.getSelectionModel().getSelectedItem().getIdmatter();
                    deleter.stop();
                    MATTER.setIdmatter(mattersCMB.getSelectionModel().getSelectedItem().getIdmatter());
                    if( MATTER.delete() )
                        refresh();
                }
            }
        });
        
        addBtn.setOnAction(evt -> {
            String name = matterNameTF.getText();
            if ( !name.isEmpty() && MATTER.count(name) == 0 ) {
                MATTER.setIdmatter(MATTER.nextIdmatter());
                MATTER.setLabel(name);
                if ( MATTER.save() ) {
                    matterNameTF.setText("");
                    refresh();
                }
            }
        });
        
        mattersCMB.getSelectionModel().selectedItemProperty().addListener((o, old, cur) -> {
            updateVB.setVisible(false);
        });
        
        Platform.runLater(() -> {
            new MattersHelper(mattersCMB, refreshBtn).bind();
        });
    }
    
    private void setDisable(Boolean disable) {
        addBtn.setDisable(disable);
        refreshBtn.setDisable(disable);
        updateBtn.setDisable(disable);
        mattersCMB.setDisable(disable);
    }
    
    private void refresh() {
        mattersCMB.getItems().clear();
        mattersCMB.getItems().addAll(MATTER.list());
        updateVB.setVisible(false);
    }
    
}
