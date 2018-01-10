package application;

public class Board {
    private int size;
    private char my_matrix_[][];

    public Board(int size) {
        this.size = size;
        int mid_row = size / 2 - 1;
        int mid_col = size / 2 - 1;
        this.my_matrix_ = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.my_matrix_[i][j] = 'E';
            }
        }
        this.my_matrix_[mid_row][mid_col] = 'O';
        this.my_matrix_[mid_row][mid_col + 1] = 'X';
        this.my_matrix_[mid_row + 1][mid_col + 1] = 'O';
        this.my_matrix_[mid_row + 1][mid_col] = 'X';
    }

    public boolean checkAllCellsFull() {
        // going over all the matrix cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (my_matrix_[i][j] == 'E') {
                    return false;
                }
            }
        }
        // if all cells are full
        return true;
    }

    public char returnCellType(int r, int c) {
        return my_matrix_[r][c];
    }

    public void setCellInBoard(int r, int c, char t) {
        this.my_matrix_[r][c] = t;
    }

    public int getSize() {
        return this.size;
    }
}
