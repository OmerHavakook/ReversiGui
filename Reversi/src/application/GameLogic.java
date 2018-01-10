package application;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private GuiPlayer first_player_;
    private GuiPlayer second_player_;
    private Board my_board_;
    char current_Player_;

    public GameLogic(GuiPlayer first_player_, GuiPlayer second_player_, int size, char startP) {
        this.first_player_ = first_player_;
        this.second_player_ = second_player_;
        this.my_board_ = new Board(size);
        this.current_Player_ = startP;
    }

    public char returnsWhoWon() {
        // save the scores and compare
        int player_X_score, player_O_score;
        player_X_score = first_player_.getScore();
        player_O_score = second_player_.getScore();
        if (player_X_score == player_O_score) { // if it's a tie
            return '=';
        }
        if (player_X_score > player_O_score) {
            return first_player_.getType();
        }
        return second_player_.getType();
    }

    public boolean checksIfGameOver(Board board) {
        // if all cells are full or the are no possible moves for each side
        if (board.checkAllCellsFull()
                || (!checksIfMovesArePossible('X', board) && !checksIfMovesArePossible('O', board))) {
            return true;
        }
        return false;
    }

    public boolean checksIfMovesArePossible(char type, Board board) {
        List<Point> list_to_check = findPossibleCells(board, type);
        if (list_to_check.size() == 0) { // if it's empty
            return false;
        }
        return true;
    }

    public List<Point> findPossibleCells(Board board, char type) {
        List<Point> list_of_points = new ArrayList<Point>();
        char other_type = 'O';
        if (type == 'O') {
            other_type = 'X';
        }
        // going over the all matrix
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                // if the cell is on the current player type
                if (board.returnCellType(i, j) == type) {
                    // looking for the other type near it
                    for (int a = -1; a < 2; a++) {
                        for (int b = -1; b < 2; b++) {
                            if ((i + a >= 0) && (i + a < board.getSize())
                                && (j + b >= 0)
                                && (j + b < board.getSize())) {
                                // found other type cell
                                if (board.returnCellType(i + a, j + b) == other_type) {
                                    Point point = findEmptyCellGeneral(
                                            i + a, j + b, other_type, a, b, board);
                                    //if the points exists add it to the vector
                                    if ((point != null)) {
                                        list_of_points.add(point);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return list_of_points;
    }

    public Point findEmptyCellGeneral(int r, int c, char t, int row_change, int col_change, Board board) {
        int index_row = r;
        int index_col = c;
        for (int i = 1; i < board.getSize(); i++) {
            index_row = changeNumber(index_row, row_change, r, i);
            index_col = changeNumber(index_col, col_change, c, i);
            // check if in limits
            if (index_row >= 0 && index_row < board.getSize()
                && index_col >= 0 && index_col < board.getSize()) {
                // if an empty cell was found
                if (board.returnCellType(index_row, index_col) == 'E') {
                    return new Point(index_row,index_col);

                } // if the cell is on the same player's type
                if (board.returnCellType(index_row, index_col) == t) {
                    continue;
                } // if the cell is on the other player's type
                if (board.returnCellType(index_row, index_col) != t) {
                    break;
                }
            }
        }
        return null;
    }
    
    public int changeNumber(int number, int flag, int default_number, int add_number) {
        if (flag == 1) {
            number = default_number + add_number;
        }
        if (flag == -1) {
            number = default_number - add_number;
        }
        return number;
    }

    public void makeAMove(int r, int c, char t, int row_change, int col_change, Board board) {
        int index_row = r;
        int index_col = c;
        char other_type = 'X';
        if (t == 'X') {
            other_type = 'O';
        }
        for (int i = 0; i < board.getSize(); i++) {
            // change the index variables, add or reduce i from r\c
            index_row = changeNumber(index_row, row_change, r, i);
            index_col = changeNumber(index_col, col_change, c, i);
            if (index_row >= 0 && index_row < board.getSize()
                && index_col >= 0 && index_col < board.getSize()) {
                if (board.returnCellType(index_row, index_col)
                    == other_type) {
                    int row_new = index_row;
                    int col_new = index_col;
                    row_new += row_change;
                    col_new += col_change;
                    if (row_new >= 0 && row_new < board.getSize()
                        && col_new >= 0 && col_new < board.getSize()) {
                        // if an empty cell was found
                        if (board.returnCellType(row_new, col_new)
                            == 'E') {
                            return;
                        }
                    } // if the cell is out of the matrix
                    if (row_new < 0 || row_new >= board.getSize()
                        || col_new < 0 || col_new >= board.getSize()) {
                        return;
                    } // if access is possible ,check its type
                    if (board.returnCellType(row_new, col_new) == t) {
                        for (int j = 0; j <= i + 1; j++) {
                            int row_loop = r;
                            int col_loop = c;
                            row_loop = changeNumber(row_loop, row_change, row_loop, j);
                            col_loop = changeNumber(col_loop, col_change, col_loop, j);
                            board.setCellInBoard(row_loop, col_loop, t);
                            this.updateScore(board);
                        }
                        return;
                    }
                }
            }
        }
    }

    

    public Board updateBoard(int x, int y, char type, Board board) {
        char other_type = 'O';
        if (type == 'O') {
            other_type = 'X';
        } // set the cell in the matrix
        board.setCellInBoard(x, y, other_type);
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if ((x + a >= 0) && (x + a < board.getSize())
                    && (y + b >= 0) && (y + b < board.getSize())) {
                    // make a move
                    if (board.returnCellType(x + a, y + b) == type) {
                        makeAMove(x + a, y + b, other_type, a, b, board);
                    }
                }
            }
        }
        return board;
    }


    public Board getBoard() {
        return my_board_;
    }

    public void updateScore(Board board) {
        // going over the all matrix and counting the number
        // of appearances to each player
        int player_X = 0;
        int player_O = 0;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.returnCellType(i, j) == 'X') {
                    player_X++;
                }
                if (board.returnCellType(i, j) == 'O') {
                    player_O++;
                }
            }
        }
        this.first_player_.setScore(player_X);
        this.second_player_.setScore(player_O);
    }

   

    public int getPlayer2Score()  {
        return this.second_player_.getScore();
    }

    public int getPlayer1Score() {
        return this.first_player_.getScore();
    }

    

    public void changePlayer() {
        if (this.current_Player_ == 'O') {
            this.current_Player_ = 'X';
        } else {
            this.current_Player_ = 'O';
        }
    }

    public GuiPlayer getPlayer(char type) {
        if (first_player_.getType() == type){
            return first_player_;
        }
        return second_player_;
    }

    public GuiPlayer getCurrent_Player_() {
        if (first_player_.getType() == current_Player_){
            return first_player_;
        }
        return second_player_;
    }
    
    public GuiPlayer getOther_player_(){
        if (first_player_.getType() != current_Player_){
            return first_player_;
        }
        return second_player_;
    }


}
