package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingsController implements Initializable {
    @FXML
    private ChoiceBox<Integer> size;
    @FXML
    private ChoiceBox<Character> firstPlayerChoose;
    @FXML
    private ChoiceBox<String> firstShape;
    @FXML
    private ChoiceBox<String> secondShape;

    private BuildSettings settings;
    @FXML
    private ImageView backButton;

    // set options for board size
    ObservableList<Integer> sizes = FXCollections.observableArrayList(4, 6, 8, 10, 12, 14, 16, 18, 20);
    // set options for begginer
    ObservableList<Character> players = FXCollections.observableArrayList('X', 'O');
    // set options for players colors
    ObservableList<String> firstColors = FXCollections.observableArrayList("yellow", "blue", "white", "black");
    ObservableList<String> secondColors = FXCollections.observableArrayList("yellow", "blue", "white", "black");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if the user clicked the back Button than set settings
        backButton.setOnMouseClicked((event) -> {
            addChosen();
        });
        // set boxes with there options
        size.setItems(sizes);
        firstPlayerChoose.setItems(players);
        firstShape.setItems(firstColors);
        secondShape.setItems(firstColors);
        settings = BuildSettings.getInstance();
    }

    /**
     * this method returns to the main
     */
    public void backToMenu() {
        Parent root;
        // try to set the root with the "MenuFXML.fxml"
        try {
            root = FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root, 800, 600);
            // set the property
            Stage s = (Stage) backButton.sceneProperty().get().getWindow();
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            // set the scene
            s.setScene(scene);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * this method save the user choice about board size, begginer and players
     * colors. than it updates the settings in the SettingsController member
     */
    public void addChosen() {
        // save user choice
        Integer boardSize = size.getValue();
        Character firstPlayer = firstPlayerChoose.getValue();
        String firstImage = firstShape.getValue();
        String secondImage = secondShape.getValue();
        // if both images are the same, send an error message to the user
        if (firstImage.equals(secondImage)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errorrrr");
            alert.setContentText("you chose two same player please changed");
            alert.showAndWait();
            return;
        }
        // set the settings
        settings.setSize(boardSize);
        settings.setBeginner(firstPlayer);
        settings.setfirstPlayerColor(firstImage);
        settings.setSecondPlayerColor(secondImage);
        // save settings to file
        settings.saveToFile();
        // back to menu
        backToMenu();
    }
}
