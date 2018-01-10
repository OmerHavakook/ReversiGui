package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GuiMenu extends VBox {
    private FXMLLoader fxmlLoader;
    @FXML
    private Text CurrentPlayerStr;
    @FXML
    private Text firstPlayerScore;
    @FXML
    private Text secondPlayerScore;
    public GuiMenu(){
        CurrentPlayerStr = new Text();
        firstPlayerScore = new Text();
        secondPlayerScore = new Text();
        fxmlLoader = new FXMLLoader(getClass().getResource("GuiMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }
    
    public void draw(char currentType,int firstScore,int secondScore){
        this.getChildren().clear();
        
        CurrentPlayerStr.setText("Current Player: " + currentType);
        firstPlayerScore.setText("First Player Score: " + firstScore);
        secondPlayerScore.setText("Second Player Score: " + secondScore);
        this.getChildren().addAll(CurrentPlayerStr,firstPlayerScore,secondPlayerScore);
        
    }
}
