package application;

import java.awt.Menu;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ReversiGameController implements Initializable {
    @FXML
    private HBox hBoxRoot;
    private Board board = new Board(8);
    private GuiBoard guiBoard;
    private GuiPlayer p1 = new GuiPlayer('X');
    private GuiPlayer p2 = new GuiPlayer('O');

    private GuiMenu guiMenu;
    private GameLogic logic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        guiBoard = new GuiBoard(board, p1, p2);
        guiBoard.setPrefWidth(400);
        guiBoard.setPrefHeight(400);
        hBoxRoot.getChildren().add(0, guiBoard);
        guiBoard.draw();
        this.logic = new GameLogic(p1, p2, board.getSize(), 'X');
        guiMenu = new GuiMenu();
        guiMenu.setPrefWidth(200);
        guiMenu.setPrefHeight(400);
        hBoxRoot.getChildren().add(guiMenu);
        guiMenu.draw('X', 2, 2);
    }

    public void runGame() {
        while (!logic.checksIfGameOver(board)) {
            char currentType = logic.getCurrent_Player_().getType();
            if (logic.checksIfMovesArePossible(currentType, board)) {
                List<Point> possiblePoints = logic.findPossibleCells(board, currentType);
                Point userChoice = this.guiBoard.chooseMove(possiblePoints);
                logic.updateBoard(userChoice.getX(), userChoice.getY(), currentType, board);
                guiBoard.setBoard(board);
                guiBoard.draw();
                guiMenu.draw(currentType, logic.getPlayer1Score(), logic.getPlayer2Score());
                
            } else {
                System.out.println("No possible moves\n");
            }
            logic.changePlayer();
        }
    }
}
