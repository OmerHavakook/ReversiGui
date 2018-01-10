package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

public class SettingsController implements Initializable {
    @FXML
    private ChoiceBox<Integer> size;
    @FXML
    private ChoiceBox<Character> firstPlayerChoose;
    @FXML
    private ChoiceBox<String> firstShape;
    @FXML
    private ChoiceBox<String> secondShape;
    
    ObservableList<Integer> sizes = FXCollections.observableArrayList(4,6,8,10,12,14,16,18,20);
    ObservableList<Character> players = FXCollections.observableArrayList('X','O');
    ObservableList<String> firstColors = FXCollections.observableArrayList("yellow","blue","white","black");
    ObservableList<String> secondColors = FXCollections.observableArrayList("yellow","blue","white","black");
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        size.setItems(sizes);
        firstPlayerChoose.setItems(players);
        firstShape.setItems(firstColors);
        secondShape.setItems(firstColors);
    }
    
    @FXML
    public void addChosen(){
        Integer boardSize =  size.getValue();
        Character firstPlayer = firstPlayerChoose.getValue();
        String firstImage = firstShape.getValue();
        String secondImage =  secondShape.getValue();
        if(firstImage.equals(secondImage)){
            Alert alert=new Alert(AlertType.ERROR);
            alert.setTitle("Errorrrr");
            alert.setContentText("you chose two same player please changed");
            alert.showAndWait();
            return;
        }
        
    }

}
