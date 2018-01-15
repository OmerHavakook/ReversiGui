package fxml;

import java.util.List;

import application.Board;
import application.GameFlow;
import application.GuiPlayer;
import application.Point;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GuiBoard extends GridPane {
    private Board board;
    private ImageView imageBoardMatrix[][];
    private GuiPlayer firstPlayer;
    private GuiPlayer secondPlayer;
    private FXMLLoader fxmlLoader;
    public static Point point;
    private Image emptyCell;
    private Image boltCell;
    private GameFlow gameFlow;

    /**
     * constructor
     * @param board - Board obj
     * @param firstPlayer - the first player
     * @param secondPlayer - the second player
     */
    public GuiBoard(Board board, GuiPlayer firstPlayer, GuiPlayer secondPlayer) {
        this.board = board;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        // allocate a matrix of images
        this.imageBoardMatrix = new ImageView[board.getSize()][board.getSize()];
        // allocate each cell with image and set mouse click
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                imageBoardMatrix[i][j] = new ImageView();
                // set mouse click and set an event
                imageBoardMatrix[i][j].setOnMouseClicked(event -> {
                    Node source = (Node) event.getSource();
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    Point clickedPoint = new Point(rowIndex, colIndex);
                    // for a click call runGame in gameFlow
                    gameFlow.runGame(clickedPoint);
                });
            }
        }
        // save the images of empty and bolt cells
        this.emptyCell = new Image(getClass().getClassLoader().getResourceAsStream("images/Cell.jpg"));
        this.boltCell = new Image(getClass().getClassLoader().getResourceAsStream("images/boltCell.jpg"));
        fxmlLoader = new FXMLLoader(getClass().getResource("GuiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

    }

    /**
     * this method returns the gameFlow member
     * @return GameFlow obj
     */
    public GameFlow getGameFlow() {
        return gameFlow;
    }

    /**
     * this method sets the gameFlow member
     * @param gameFlow - GameFlow obj
     */
    public void setGameFlow(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    /**
     * this method returns the board member
     * @return the board member
     */
    public Board getBoard() {
        return board;
    }

    /**
     * this method sets the board member
     * @param board - Board obj
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * this method draws the game, it fits each cell with the right image and
     * add the cell as a child of this gridpane.
     * @param list - list of points that should be drawn with bolt cells ( the
     * possible moves for the curren player )
     */
    public void draw(List<Point> list) {
        // clearing all children (all images)
        this.getChildren().clear();
        int boardSize = board.getSize();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / boardSize;
        int cellWidth = width / boardSize;
        char type;
        char firstType = firstPlayer.getType();
        char secondType = secondPlayer.getType();
        // going over the matrix and sets the correct images on the cells based
        // on the players type and the board state , than add the this gridpane
        // the cell with the right image as a child.
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                type = board.returnCellType(i, j);
                // if the type is the first player type than set the cell image
                // with the first player image
                if (type == firstType) {
                    imageBoardMatrix[i][j].setImage(firstPlayer.getIv());
                    this.add(imageBoardMatrix[i][j], j, i);
                    // if the type is the second player type than set the cell
                    // image with the second player image
                } else if (type == secondType) {
                    imageBoardMatrix[i][j].setImage(secondPlayer.getIv());
                    this.add(imageBoardMatrix[i][j], j, i);
                } else { // check if the cell should be drawn as bolt
                    boolean bolt = false;
                    // if the cell should be bolt set the correct image
                    for (int t = 0; t < list.size(); t++) {
                        if (list.get(t).getX() == i && list.get(t).getY() == j) {
                            imageBoardMatrix[i][j].setImage(boltCell);
                            bolt = true;
                        }
                    } // if the cell should not be bolt
                    if (!bolt) {
                        imageBoardMatrix[i][j].setImage(emptyCell);
                    } // add the cell as child
                    this.add(imageBoardMatrix[i][j], j, i);
                }
                imageBoardMatrix[i][j].setFitWidth(cellWidth);
                imageBoardMatrix[i][j].setFitHeight(cellHeight);
            }
        }
    }

    /**
     * this method returns the fit player based on his type.
     * @param type - the player type
     * @return GuiPlayer - the player that his type is equal to type
     */
    public GuiPlayer getPlayer(char type) {
        if (firstPlayer.getType() == type) {
            return firstPlayer;
        }
        return secondPlayer;
    }
}