package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ReversiGameController implements Initializable {
    @FXML
    private HBox hBoxRoot;
    @FXML
    private VBox child = new VBox();
    private Board board;
    private GuiBoard guiBoard;
    private GuiPlayer p1;
    private GuiPlayer p2;

    @FXML
    private Text CurrentPlayerStr = new Text();
    @FXML
    private Text firstPlayerScore = new Text();
    @FXML
    private Text secondPlayerScore = new Text();

    // private GuiGameProgress guiMenu;
    private GameLogic logic;

    public GameLogic getLogic() {
        return logic;
    }

    /*
     * public GuiGameProgress getGuiMenu() { return guiMenu; }
     */

    private BuildSettings settings;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        settings = BuildSettings.getInstance();
        settings.loadFromFile();
        int size = settings.getSize();
        char begginer = settings.getBeginner();
        char last = settings.getLast();
        String fColor = settings.getfName();
        String sColor = settings.getSName();
        this.board = new Board(size);
        this.p1 = new GuiPlayer(begginer, sColor);
        this.p2 = new GuiPlayer(last, fColor);
        if ('X' == begginer) {
            this.p1 = new GuiPlayer(begginer, fColor);
            this.p2 = new GuiPlayer(last, sColor);
        }
        guiBoard = new GuiBoard(board, p1, p2);
        guiBoard.setPrefWidth(400);
        guiBoard.setPrefHeight(400);
        hBoxRoot.getChildren().add(0, guiBoard);
        guiBoard.setGameFlow(new GameFlow(guiBoard, this));
        System.out.println(begginer);
        this.logic = new GameLogic(p1, p2, board.getSize(), begginer);
        guiBoard.draw(logic.findPossibleCells(this.board, begginer));
        draw(p1.getColor(), 2, 2);
    }


    public void draw(String color, int player1Score, int player2Score) {
        // TODO Auto-generated method stub
        child.getChildren().removeAll(CurrentPlayerStr, firstPlayerScore, secondPlayerScore);
        CurrentPlayerStr.setText("  Current Player: " + color + "\n\n");

        firstPlayerScore.setText("  First Player Score: " + player1Score + "\n\n");
        secondPlayerScore.setText("  Second Player Score: " + player2Score);
        child.getChildren().addAll(CurrentPlayerStr, firstPlayerScore, secondPlayerScore);

    }

}
