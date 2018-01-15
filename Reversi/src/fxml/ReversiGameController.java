package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Board;
import application.BuildSettings;
import application.GameFlow;
import application.GameLogic;
import application.GuiPlayer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReversiGameController implements Initializable {
    @FXML
    private AnchorPane baseRoot;
    @FXML
    private StackPane bRoot;
    @FXML
    private AnchorPane progressRoot;
    @FXML
    private Text curPlayer;
    @FXML
    private Text p1Score;
    @FXML
    private Text p2Score;
    private Board board;
    private GuiBoard guiBoard;
    private GuiPlayer firstPlayer;
    private GuiPlayer secondPlayer;
    private GameLogic logic;
    private BuildSettings settings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settings = BuildSettings.getInstance();
        // load settings from file and save members
        settings.loadFromFile();
        int size = settings.getSize();
        char begginer = settings.getBeginner();
        char last = settings.getLast();
        String firstColor = settings.getFirstPlayerColor();
        String secondColor = settings.getSecondPlayerColor();
        this.board = new Board(size);
        // if the begginer is 'O'
        this.firstPlayer = new GuiPlayer(begginer, secondColor);
        this.secondPlayer = new GuiPlayer(last, firstColor);
        if ('X' == begginer) { // if the begginer is 'X'
            this.firstPlayer = new GuiPlayer(begginer, firstColor);
            this.secondPlayer = new GuiPlayer(last, secondColor);
        }
        guiBoard = new GuiBoard(board, firstPlayer, secondPlayer);
        guiBoard.setPrefWidth(400);
        guiBoard.setPrefHeight(400);
        // add guiBoard to the stackpane
        bRoot.getChildren().add(guiBoard);
        guiBoard.setGameFlow(new GameFlow(guiBoard, this));
        this.logic = new GameLogic(firstPlayer, secondPlayer, board.getSize(), begginer);
        // listen to property, change screen if needed
        bRoot.setLayoutX(185);
        // set each text in the right place based on the screen's properties
        // set width
        baseRoot.widthProperty().addListener((observable, oldValue, newValue) -> {
            guiBoard.setPrefWidth(newValue.doubleValue() * 0.5);
            curPlayer.setLayoutX(newValue.doubleValue() * 0.10);
            p1Score.setLayoutX(newValue.doubleValue() * 0.40);
            p2Score.setLayoutX(newValue.doubleValue() * 0.65);

            guiBoard.draw(logic.findPossibleCells(this.board, begginer));
            draw(firstPlayer.getColor(), 2, 2);
        });
        // set height
        baseRoot.heightProperty().addListener((observable, oldValue, newValue) -> {
            guiBoard.setPrefHeight(newValue.doubleValue() * 2 / 3);
            curPlayer.setLayoutY(newValue.doubleValue() * 1 / 14);
            p1Score.setLayoutY(newValue.doubleValue() * 1 / 14);
            p2Score.setLayoutY(newValue.doubleValue() * 1 / 14);

            guiBoard.draw(logic.findPossibleCells(this.board, begginer));
            draw(firstPlayer.getColor(), 2, 2);
        });

    }

    /**
     * this method returns the logic of the game
     * @return GameLogic - the game logic
     */
    public GameLogic getLogic() {
        return logic;
    }

    /**
     * this method draws the texts on the screen
     * @param color - the curren player
     * @param player1Score - the score of the first player
     * @param player2Score - the score of the second player
     */
    public void draw(String color, int player1Score, int player2Score) {
        curPlayer.setText("  Current Player: " + color + "\n\n");
        p1Score.setText("  First Player Score: " + player1Score + "\n\n");
        p2Score.setText("  Second Player Score: " + player2Score);
    }

    /**
     * this method returns to the main
     * @param ok
     */
    public void backToMenu() {
        Parent root;
        // try to set the root with the "MenuFXML.fxml"
        try {
            root = FXMLLoader.load(getClass().getResource("MenuFXML.fxml"));
            Scene scene = new Scene(root, 800, 600);
            // set the property
            Stage s = (Stage) baseRoot.getScene().getWindow();
            // set the scene
            s.setScene(scene);
            settings.setDefauldSettings();
            s.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * this method is called when an error occurred
     * @param message - to print to the user to alert him with the error
     */
    public void alert(String message) {
        // create an Alert
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
        return;
    }

    /**
     * this method is called when the game is over
     * @param message
     */
    public void alertGameOver(String message) {
        // alert the user
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setContentText(message);
        alert.showAndWait();
        backToMenu();
        return;
    }
}
