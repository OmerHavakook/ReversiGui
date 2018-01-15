package application;

public class Board {
    private int size;
    private char myMatrix[][];

    /**
     * constructor for board
     * @param size - the size of the board
     */
    public Board(int size) {
        this.size = size;
        // calculate the middle row and col of the board
        int midRow = size / 2 - 1;
        int midCol = size / 2 - 1;
        // initialize the board as matrix of char and fill it with the char 'E'
        this.myMatrix = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.myMatrix[i][j] = 'E';
            }
        }
        // change the middle cells with values
        this.myMatrix[midRow][midCol] = 'O';
        this.myMatrix[midRow][midCol + 1] = 'X';
        this.myMatrix[midRow + 1][midCol + 1] = 'O';
        this.myMatrix[midRow + 1][midCol] = 'X';
    }

    /**
     * this method goes over all of the boards cells and checks if the board is
     * full ( there are not any empty cell)
     * @return true if the board is full and false otherwise
     */
    public boolean checkAllCellsFull() {
        // going over all the matrix cells
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // if there is an empty cell than return false
                if (myMatrix[i][j] == 'E') {
                    return false;
                }
            }
        }
        // if all cells are full than return true
        return true;
    }

    /**
     * this method return the type of certain cell
     * @param row - as the cell's row location
     * @param column - as the cell's column location
     * @return char - the type of the cell
     */
    public char returnCellType(int row, int column) {
        return myMatrix[row][column];
    }

    /**
     * this method sets the cell's type
     * @param row - as the cell's row location
     * @param column - as the cell's column location
     * @param type - as the cell's new type
     */
    public void setCellInBoard(int row, int column, char type) {
        this.myMatrix[row][column] = type;
    }

    /**
     * this method returns the board size
     * @return int - the board's size
     */
    public int getSize() {
        return this.size;
    }
}
