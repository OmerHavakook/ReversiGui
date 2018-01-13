package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ReversiGameController implements Initializable {

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
        StackPane.setAlignment(guiBoard, Pos.CENTER);
        guiBoard.setGameFlow(new GameFlow(guiBoard, this));
        this.logic = new GameLogic(firstPlayer, secondPlayer, board.getSize(), begginer);
        // draw the guiBoard
        guiBoard.draw(logic.findPossibleCells(this.board, begginer));
        // draw the info about the current player and the scores
        draw(firstPlayer.getColor(), 2, 2);
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
}
