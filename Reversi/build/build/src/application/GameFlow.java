package application;

import java.util.List;

import fxml.GuiBoard;
import fxml.ReversiGameController;

public class GameFlow {
    private GuiBoard guiBoard;
    private ReversiGameController gameController;

    /**
     * constructor
     * @param guiBoard - GuiBoard object
     * @param controller - ReversiGameController object
     */
    public GameFlow(GuiBoard guiBoard, ReversiGameController controller) {
        this.guiBoard = guiBoard;
        this.gameController = controller;
    }

    /**
     * this method checks if point is valid (move). This method gets the list of
     * possible points and goes over this list and check if the requested point
     * if in the list
     * @param points - the list of possible points
     * @param point - the requested point
     * @return true if the point is valid and false otherwise
     */
    public boolean checkIfPointIsValid(List<Point> points, Point point) {
        // go over the list of points
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).equals(point)) {
                // point is in the list
                return true;
            }
        } // send message to the user to try again
        gameController.alert("Please try again...:)");
        // point is not in the list
        return false;
    }

    /**
     * this method runs the game, it's being called whenever a clicked occurred.
     * @param clicked_point
     */
    public void runGame(Point clicked_point) {
        // get the game logic from the game controller
        GameLogic logic = gameController.getLogic();
        // get the board from the gui board
        Board board = guiBoard.getBoard();
        char otherType;
        // get current type
        char currentType = logic.getCurrentPlayer().getType();
        // if moves are possible for the current player
        if (logic.checksIfMovesArePossible(currentType, board)) {
            // save the possible points
            List<Point> possiblePoints = logic.findPossibleCells(board, currentType);
            // check if the point is a valid move
            if (!checkIfPointIsValid(possiblePoints, clicked_point)) {
                return;
            }
            // save the other type
            otherType = logic.getOtherPlayer().getType();
            // update the board by the clicked point
            logic.updateBoard(clicked_point.getX(), clicked_point.getY(), otherType, board);
            // set the right Board obj in the gui board and draw it
            guiBoard.setBoard(board);
            guiBoard.draw(logic.findPossibleCells(board, otherType));
            // check if game is over
            if (logic.checksIfGameOver(board)) {
                // send the user a message and draw the all window ( with the
                // player's point and current player
                this.gameController.alertGameOver("Game is over");
                gameController.draw(logic.getCurrentPlayer().getColor(), logic.getPlayer1Score(),
                        logic.getPlayer2Score());
                return;
            }
            // if game is not over , just change the current player
            logic.changePlayer();
        } else { // no moves are possible for the current player
            // send the user a message, change the players and draw the board
            gameController.alert("There is No possible Move for you\n The turn change...");
            logic.changePlayer();
            guiBoard.draw(logic.findPossibleCells(board, logic.getCurrentPlayer().getType()));
        }
        // logic.changePlayer();
        currentType = logic.getCurrentPlayer().getType();
        // check if there are not possible moves for the current player
        if (!logic.checksIfMovesArePossible(currentType, board)) {
            // send the user a message, change the current player and draw the
            // board
            gameController.alert("There is No possible Move for you\n The turn change...");
            logic.changePlayer();
            guiBoard.draw(logic.findPossibleCells(board, logic.getCurrentPlayer().getType()));
        }
        // draw the all window
        gameController.draw(logic.getCurrentPlayer().getColor(), logic.getPlayer1Score(), logic.getPlayer2Score());
    }
}
