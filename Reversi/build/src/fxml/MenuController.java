package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private GridPane pane;
    @FXML
    private ImageView startButton;
    @FXML
    private ImageView settingsButton;
    @FXML
    private ImageView exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set menu in the middle of the screen
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefWidth(newValue.doubleValue());
        });
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            pane.setPrefHeight(newValue.doubleValue());
        });
        // if the start button was clicked, than call gameSelect
        startButton.setOnMouseClicked((event) -> {
            gameSelect();
        });
        // if the exit button was clicked, than exit program
        exitButton.setOnMouseClicked((event) -> {
            Platform.exit();
            System.exit(0);
        });
        // if settingsButton was clicked, than call optionsSelect
        settingsButton.setOnMouseClicked((event) -> {
            optionsSelect();
        });
    }

    public void gameSelect() {
        // try to create FXMLLoader from "ReversiGame.fxml"
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("ReversiGame.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent, 800, 600);
            Stage theStage = (Stage) startButton.getScene().getWindow();
            // set the scene with this settingScene (the players scores)
            theStage.setScene(settingsScene);
        } catch (Exception ex) {
            System.out.println("selectGame error:");
            ex.printStackTrace();
        }
    }

    void optionsSelect() {
        // try to create FXMLLoader from "SettingsFXML.fxml"
        try {
            FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("SettingsFXML.fxml"));
            Parent settingsParent = settingsLoader.load();
            Scene settingsScene = new Scene(settingsParent, 800, 600);
            Stage theStage = (Stage) settingsButton.getScene().getWindow();
            // set the scene with this settingScene (settings)
            theStage.setScene(settingsScene);
        } catch (Exception ex) {
            System.out.println("ChangeSettings error:");
            ex.printStackTrace();
        }
    }
}
