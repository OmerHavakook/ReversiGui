package application;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameFlow {
    private GuiBoard guiB;
    private ReversiGameController gameController;

    public GameFlow(GuiBoard gb, ReversiGameController controller) {
        this.guiB = gb;

        this.gameController = controller;
    }

    public void alert(String s) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
        return;
    }

    public void alertGameOver(String s) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("GAME OVER");
        alert.setContentText(s);
        alert.showAndWait();
        return;
    }

    public boolean requestPoint(List<Point> points, Point p) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).equals(p)) {
                return true;
            }
        }
        alert("Please try again...:)");
        return false;
    }

    public void runGame(Point p) {
        GameLogic logic = gameController.getLogic();
        Board board = guiB.getBoard();
        char otherType;
        char currentType = logic.getCurrent_Player_().getType();
        if (logic.checksIfMovesArePossible(currentType, board)) {
            List<Point> possiblePoints = logic.findPossibleCells(board, currentType);
            if (!requestPoint(possiblePoints, p)) {
                return;
            }
            otherType = logic.getOther_player_().getType();
            logic.updateBoard(p.getX(), p.getY(), otherType, board);
            guiB.setBoard(board);
            guiB.draw(logic.findPossibleCells(board, otherType));

            if (logic.checksIfGameOver(board)) {
                alertGameOver("Game is over");
                gameController.draw(logic.getCurrent_Player_().getColor(), logic.getPlayer1Score(),
                        logic.getPlayer2Score());
                return;
            }
            logic.changePlayer();

        } else {
            alert("There is No possible Move for you\n The turn change...");
            logic.changePlayer();
            guiB.draw(logic.findPossibleCells(board, logic.getCurrent_Player_().getType()));
        }
        // logic.changePlayer();
        currentType = logic.getCurrent_Player_().getType();
        if (!logic.checksIfMovesArePossible(currentType, board)) {
            alert("There is No possible Move for you\n The turn change...");
            logic.changePlayer();
            guiB.draw(logic.findPossibleCells(board, logic.getCurrent_Player_().getType()));

        }
        gameController.draw(logic.getCurrent_Player_().getColor(), logic.getPlayer1Score(),
                logic.getPlayer2Score());
    }
}
