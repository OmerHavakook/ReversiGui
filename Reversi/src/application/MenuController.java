package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private ImageView startB;
    
    @FXML
    private ImageView settingsBtn;

    @FXML
    private ImageView exitBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startB.setOnMouseClicked((event) -> {
            gameSelect();
        });
        exitBtn.setOnMouseClicked((event) -> {
            Platform.exit();
            System.exit(0);
        });
        settingsBtn.setOnMouseClicked((event) -> {
            optionsSelect();
        });

    }

    public void gameSelect() {
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent);
            Stage theStage = (Stage) startB.getScene().getWindow();
            theStage.setScene(settingsScene);
        } catch (Exception ex) {
            // Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }

    }

    void optionsSelect() {
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("SettingsFXML.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent,800,600);
            Stage theStage = (Stage) settingsBtn.getScene().getWindow();
            theStage.setScene(settingsScene);
        } catch (Exception ex) {
            // Weird Error - if happens -> debug
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }

}
