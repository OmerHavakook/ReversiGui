package application;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private GuiPlayer firstPlayer;
    private GuiPlayer secondPlayer;
    private Board myBoard;
    char currentPlayer;

    /**
     * constructor
     * @param firstPlayer - GuiPlayer obj ( first player )
     * @param secondPlayer - GuiPlayer obj ( second player )
     * @param size - board size
     * @param beginner - the char of the begginer player
     */
    public GameLogic(GuiPlayer firstPlayer, GuiPlayer secondPlayer, int size, char beginner) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.myBoard = new Board(size);
        this.currentPlayer = beginner;
    }

    /**
     * this method returns which player won the game by comparing both players
     * scores.
     * @return the type of the winner player
     */
    public char returnsWhoWon() {
        // save the scores and compare them
        int firstPlayerScore, secondPlayerScore;
        firstPlayerScore = firstPlayer.getScore();
        secondPlayerScore = secondPlayer.getScore();
        if (firstPlayerScore == secondPlayerScore) { // if it's a tie
            return '=';
        } // if the first player won , than return his type
        if (firstPlayerScore > secondPlayerScore) {
            return firstPlayer.getType();
        } // return the second player type
        return secondPlayer.getType();
    }

    /**
     * this method checks if game is over
     * @param board - to check if the game is over for this board
     * @return true - if game is over and false otherwise
     */
    public boolean checksIfGameOver(Board board) {
        // if all cells are full or the are no possible moves for both sides
        if (board.checkAllCellsFull()
                || (!checksIfMovesArePossible('X', board) && !checksIfMovesArePossible('O', board))) {
            return true;
        } // if game is not over
        return false;
    }

    /**
     * this method checks if there are possible moves for a player
     * @param type - the player type
     * @param board - the board the check must be made on
     * @return true - if there are possible moves for the player and false
     * otherwise
     */
    public boolean checksIfMovesArePossible(char type, Board board) {
        // save the possible moves in a list
        List<Point> listToCheck = findPossibleCells(board, type);
        if (listToCheck.size() == 0) { // if it's empty
            return false;
        }
        return true;
    }

    /**
     * this method finds possible moves for a player by checking the matrix This
     * method calls findEmptyCellGeneral for each possible side of the cell and
     * saves the possible moves
     * @param board - the board the check must be made on
     * @param type - the player type
     * @return list of possible moves
     */
    public List<Point> findPossibleCells(Board board, char type) {
        // allocate list
        List<Point> listOfPoints = new ArrayList<Point>();
        char otherType = 'O';
        if (type == 'O') {
            otherType = 'X';
        }
        // going over the all matrix
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                // if the cell is on the current player type
                if (board.returnCellType(i, j) == type) {
                    // looking for the other type near it
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            if ((i + a >= 0) && (i + a < board.getSize()) && (j + b >= 0)
                                    && (j + b < board.getSize())) {
                                // found other type cell
                                if (board.returnCellType(i + a, j + b) == otherType) {
                                    Point point = findEmptyCellGeneral(i + a, j + b, otherType, a, b, board);
                                    // if the points exists add it to the vector
                                    if ((point != null)) {
                                        listOfPoints.add(point);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return listOfPoints;
    }

    /**
     * this method finds empty cells for a player, it's called 8 times for each
     * possible side of the cell.
     * @param row - the row location
     * @param col - the col location
     * @param type - the player type
     * @param rowChange - the direction of row (side to check)
     * @param colChange - the direction of col (side to check)
     * @param board - the board
     * @return Point - if there is a possible point in this side and null if
     * there is not a possible point in this side
     */
    public Point findEmptyCellGeneral(int row, int col, char type, int rowChange, int colChange, Board board) {
        int indexRow = row;
        int indexCol = col;
        for (int i = 1; i < board.getSize(); i++) {
            indexRow = changeNumber(indexRow, rowChange, row, i);
            indexCol = changeNumber(indexCol, colChange, col, i);
            // check if in limits
            if (indexRow >= 0 && indexRow < board.getSize() && indexCol >= 0 && indexCol < board.getSize()) {
                // if an empty cell was found
                if (board.returnCellType(indexRow, indexCol) == 'E') {
                    return new Point(indexRow, indexCol);

                } // if the cell is on the same player's type
                if (board.returnCellType(indexRow, indexCol) == type) {
                    continue;
                } // if the cell is on the other player's type
                if (board.returnCellType(indexRow, indexCol) != type) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * this method changes a number and return the new number
     * @param number - the number to change
     * @param flag - to add or to sub from the number
     * @param defaultNumber - the number to +\- on
     * @param addNumber - the number to add or sub
     * @return the new number
     */
    public int changeNumber(int number, int flag, int defaultNumber, int addNumber) {
        if (flag == 1) {
            number = defaultNumber + addNumber;
        }
        if (flag == -1) {
            number = defaultNumber - addNumber;
        }
        return number;
    }

    /**
     * this method makes a move ,updates the board, and updates the players
     * scores.
     * @param row - the point row location
     * @param col - the point col location
     * @param type - the player type
     * @param rowChange - the change in row
     * @param colChange - the change in col
     * @param board - the board
     */
    public void makeAMove(int row, int col, char type, int rowChange, int colChange, Board board) {
        int indexRow = row;
        int indexCol = col;
        char otherType = 'X';
        if (type == 'X') {
            otherType = 'O';
        }
        // go over the matrix
        for (int i = 0; i < board.getSize(); i++) {
            // change the index variables, add or reduce i from r\c
            indexRow = changeNumber(indexRow, rowChange, row, i);
            indexCol = changeNumber(indexCol, colChange, col, i);
            if (indexRow >= 0 && indexRow < board.getSize() && indexCol >= 0 && indexCol < board.getSize()) {
                if (board.returnCellType(indexRow, indexCol) == otherType) {
                    int rowNew = indexRow;
                    int colNew = indexCol;
                    rowNew += rowChange;
                    colNew += colChange;
                    if (rowNew >= 0 && rowNew < board.getSize() && colNew >= 0 && colNew < board.getSize()) {
                        // if an empty cell was found
                        if (board.returnCellType(rowNew, colNew) == 'E') {
                            return;
                        }
                    } // if the cell is out of the matrix
                    if (rowNew < 0 || rowNew >= board.getSize() || colNew < 0 || colNew >= board.getSize()) {
                        return;
                    } // if access is possible ,check its type
                    if (board.returnCellType(rowNew, colNew) == type) {
                        for (int j = 0; j <= i + 1; j++) {
                            int rowLoop = row;
                            int colLoop = col;
                            rowLoop = changeNumber(rowLoop, rowChange, rowLoop, j);
                            colLoop = changeNumber(colLoop, colChange, colLoop, j);
                            board.setCellInBoard(rowLoop, colLoop, type);
                            this.updateScore(board);
                        }
                        return;
                    }
                }
            }
        }
    }

    /**
     * this method updates the board
     * @param row - the row location of the point
     * @param col - the col location of the point
     * @param type - the player type
     * @param board - the current board
     * @return Board - after updating
     */
    public Board updateBoard(int row, int col, char type, Board board) {
        char otherType = 'O';
        if (type == 'O') {
            otherType = 'X';
        } // set the cell in the matrix
        board.setCellInBoard(row, col, otherType);
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if ((row + a >= 0) && (row + a < board.getSize()) && (col + b >= 0) && (col + b < board.getSize())) {
                    // make a move
                    if (board.returnCellType(row + a, col + b) == type) {
                        makeAMove(row + a, col + b, otherType, a, b, board);
                    }
                }
            }
        }
        return board;
    }

    /**
     * this method returns the current board
     * @return the current board
     */
    public Board getBoard() {
        return myBoard;
    }

    /**
     * this method updates the players' scores by going over the matrix and
     * calculating the scores
     * @param board - the board the calculation should be make at
     */
    public void updateScore(Board board) {
        // going over the all matrix and counting the number
        // of appearances to each player
        int firstPlayerScore = 0;
        int secondPlayerScore = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.returnCellType(i, j) == firstPlayer.getType()) {
                    firstPlayerScore++;
                }
                if (board.returnCellType(i, j) == secondPlayer.getType()) {
                    secondPlayerScore++;
                }
            }
        }
        this.firstPlayer.setScore(firstPlayerScore);
        this.secondPlayer.setScore(secondPlayerScore);
    }

    /**
     * this method returns the score of the second player
     * @return int - the second player score
     */
    public int getPlayer2Score() {
        return this.secondPlayer.getScore();
    }

    /**
     * this method returns the score of the first player
     * @return int - the score of the first player
     */
    public int getPlayer1Score() {
        return this.firstPlayer.getScore();
    }

    /**
     * this method updates the current player
     */
    public void changePlayer() {
        if (this.currentPlayer == 'O') {
            this.currentPlayer = 'X';
        } else {
            this.currentPlayer = 'O';
        }
    }

    /**
     * this method returns the requested player based on the player type
     * @param type - the type of the player this method returns
     * @return GuiPlayer - one of the players
     */
    public GuiPlayer getPlayer(char type) {
        if (firstPlayer.getType() == type) {
            return firstPlayer;
        }
        return secondPlayer;
    }

    /**
     * this method returns the current player
     * @return GuiPlayer - obj
     */
    public GuiPlayer getCurrentPlayer() {
        if (firstPlayer.getType() == currentPlayer) {
            return firstPlayer;
        }
        return secondPlayer;
    }

    /**
     * this player returns the other player (not the current one)
     * @return GuiPlayer - obj
     */
    public GuiPlayer getOtherPlayer() {
        if (firstPlayer.getType() != currentPlayer) {
            return firstPlayer;
        }
        return secondPlayer;
    }

}
