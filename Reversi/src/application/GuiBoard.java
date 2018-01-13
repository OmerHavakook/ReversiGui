package application;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GuiBoard extends GridPane {
    private Board board;
    private ImageView imgBoard[][];
    private GuiPlayer firstPlayer;
    private GuiPlayer secondPlayer;
    private FXMLLoader fxmlLoader;
    public static Point p;
    private Image cellR;
    private Image cellBolt;
    private GameFlow gameFlow;


    public GameFlow getGameFlow() {
        return gameFlow;
    }

    public void setGameFlow(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
    }

    public GuiBoard(Board b, GuiPlayer p1, GuiPlayer p2) {
        this.board = b;
        this.firstPlayer = p1;
        this.secondPlayer = p2;
        this.imgBoard = new ImageView[b.getSize()][b.getSize()];
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                imgBoard[i][j] = new ImageView();
                imgBoard[i][j].setOnMouseClicked(event -> {
                    Node source = (Node) event.getSource();
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    Point clickedPoint = new Point(rowIndex, colIndex);

                    gameFlow.runGame(clickedPoint);
                });
            }
        }
        this.cellR = new Image(getClass().getClassLoader().getResourceAsStream("images/Cell.jpg"));
        this.cellBolt = new Image(getClass().getClassLoader().getResourceAsStream("images/boltCell.jpg"));
        fxmlLoader = new FXMLLoader(getClass().getResource("GuiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void draw(List<Point> list) {
        this.getChildren().clear();
        int boardSize = board.getSize();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();

        int cellHeight = height / boardSize;
        int cellWidth = width / boardSize;
        char type;
        char firstType = firstPlayer.getType();
        char secondType = secondPlayer.getType();

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                type = board.returnCellType(i, j);
                if (type == firstType) {
                    imgBoard[i][j].setImage(firstPlayer.getIv());
                    // this.add(new Circle(10, Paint.valueOf("black")), j, i);
                    this.add(imgBoard[i][j], j, i);

                } else if (type == secondType) {
                    imgBoard[i][j].setImage(secondPlayer.getIv());
                    // this.add(new Circle(10, Paint.valueOf("orange")), j, i);
                    this.add(imgBoard[i][j], j, i);
                } else {
                    boolean bolt = false;
                    for (int t = 0; t < list.size(); t++) {
                        if (list.get(t).getX() == i && list.get(t).getY() == j) {
                            imgBoard[i][j].setImage(cellBolt);
                            bolt = true;
                        }
                    }
                    if (!bolt) {
                        imgBoard[i][j].setImage(cellR);
                    }
                    this.add(imgBoard[i][j], j, i);
                }
                imgBoard[i][j].setFitWidth(cellWidth);
                imgBoard[i][j].setFitHeight(cellHeight);
            }
        }

        /*
         * for (int i = 0; i < boardSize; i++) { for (int j = 0; j < boardSize;
         * j++) { Rectangle r = new Rectangle((double) cellWidth, (double)
         * cellHeight, Paint.valueOf("yellow"));
         * r.setStroke(Paint.valueOf("black")); this.add(r, j, i); type =
         * board.returnCellType(i, j); if (type == firstType) {
         * 
         * System.out.println(1); firstPlayer.getIv().setFitWidth(cellWidth);
         * firstPlayer.getIv().setFitHeight(cellHeight);
         * this.getChildren().remove(firstPlayer.getIv());
         * this.add(firstPlayer.getIv(),j,i);
         * 
         * this.add(new Circle(10, Paint.valueOf("black")), j, i);
         * 
         * } else if (type == secondType) {
         * 
         * System.out.println(2); secondPlayer.getIv().setFitWidth(cellWidth);
         * secondPlayer.getIv().setFitHeight(cellHeight);
         * this.getChildren().remove(secondPlayer.getIv());
         * this.add(secondPlayer.getIv(), j, i);
         * 
         * this.add(new Circle(10, Paint.valueOf("orange")), j, i);
         * 
         * } } }
         */

    }

    public GuiPlayer checkCurrentPlayer(char type) {
        if (firstPlayer.getType() == type) {
            return firstPlayer;
        }
        return secondPlayer;
    }

    public Point chooseMove(List<Point> possiblePoints) {
        try {
            fxmlLoader.load();
            this.setOnMouseClicked(event -> {
                Node source = (Node) event.getSource();
                int col = GridPane.getColumnIndex(source);
                int row = GridPane.getRowIndex(source);
                p = new Point(col, row);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return p;
    }
}