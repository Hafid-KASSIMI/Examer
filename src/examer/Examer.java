 
package examer;

import static examer.Settings.BOSS;
import examer.windows.ExamSelector;
import examer.windows.MainWindow;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.sicut.db.Configurator;
import org.sicut.db.Translator;
import org.sicut.db.Preferences;

/**
 *
 * @author Hafid KASSIMI
 */
public class Examer extends Application {
    
    private final SimpleStringProperty command = new SimpleStringProperty("");
    private ExamSelector es;
    private MainWindow mw;
    private Stage stage;

    public Examer() {
        command.addListener((obs, old, cur) -> {
            switch (cur) {
                case "MAIN_WINDOW":
                    if ( mw == null ) {
                        mw = new MainWindow();
                        mw.getScene().setUserData(command);
                    }
                    else 
                        mw.refresh();
                    showMainWindow();
                    break;
                case "EXAM_SELECTOR":
                    showExamSelector();
                    break;
                case "QUIT":
                    stage.hide();
                    quit();
                    break;
            }
        });
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Configurator config = new Configurator();
        
        config.prepare(Settings.PREF_DB_PATH, Settings.I18N_DB_PATH, Settings.DB_FOLDER_PATH, getClass().getResource("/examer/db/source/preferences.sql").openStream(), getClass().getResource("/examer/db/source/i18n.sql").openStream());
        config.prepare(Settings.DB_PATH, getClass().getResource("/examer/db/source/structure.sql").openStream());
        Settings.PREF_BUNDLE = new Preferences(Settings.PREF_DB_PATH);
        Settings.SELECTED_LANG = Settings.PREF_BUNDLE.get("LANGUAGE");
        Settings.I18N_BUNDLE = new Translator(Settings.SELECTED_LANG, Settings.I18N_DB_PATH);
        
        this.stage = stage;
        es = new ExamSelector();
        es.getScene().setUserData(command);
        stage.setScene(es.getScene());
        stage.setTitle(Settings.I18N_BUNDLE.getString("APP_TITLE"));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/256.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/128.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/64.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/48.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/32.png")));
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/examer/resources/images/16.png")));
        stage.show();
        stage.setOnCloseRequest(evt -> {
            quit();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void showMainWindow() {
        stage.hide();
        stage.setScene(mw.getScene());
        stage.setResizable(true);
        stage.setMaximized(true);
        stage.show();
    }
    
    private void showExamSelector() {
        stage.hide();
        stage.setScene(es.getScene());
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.show();
    }

    private void quit() {
        Settings.PREF_BUNDLE.commit();
        BOSS.stop();
    }
    
}
