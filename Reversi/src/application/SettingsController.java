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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    
    @FXML
    private Button saveBtn;

    private BuildSettings settings;

    ObservableList<Integer> sizes = FXCollections.observableArrayList(4, 6, 8, 10, 12, 14, 16, 18, 20);
    ObservableList<Character> players = FXCollections.observableArrayList('X', 'O');
    ObservableList<String> firstColors = FXCollections.observableArrayList("yellow", "blue", "white", "black");
    ObservableList<String> secondColors = FXCollections.observableArrayList("yellow", "blue", "white", "black");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        size.setItems(sizes);
        firstPlayerChoose.setItems(players);
        firstShape.setItems(firstColors);
        secondShape.setItems(firstColors);
        settings = BuildSettings.getInstance();
    }

    public void backToGame() {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/application/ReversiGame.fxml"));
            Scene scene = new Scene(root, 600, 600);
            Stage s = (Stage) saveBtn.sceneProperty().get().getWindow();
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            s.setScene(scene);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    public void addChosen() {
        Integer boardSize = size.getValue();
        Character firstPlayer = firstPlayerChoose.getValue();
        String firstImage = firstShape.getValue();
        String secondImage = secondShape.getValue();
        if (firstImage.equals(secondImage)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errorrrr");
            alert.setContentText("you chose two same player please changed");
            alert.showAndWait();
            return;
        }
        settings.setSize(boardSize);
        settings.setBeginner(firstPlayer);
        settings.setfName(firstImage);
        settings.setSName(secondImage);
        settings.saveToFile();
        backToGame();
    }
}
