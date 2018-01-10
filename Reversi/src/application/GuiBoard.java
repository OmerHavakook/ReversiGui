package application;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class GuiBoard extends GridPane {
    private Board board;
    private ImageView imgBoard[][];
    private GuiPlayer firstPlayer;
    private GuiPlayer secondPlayer;
    private FXMLLoader fxmlLoader;
    public static Point p;

    public GuiBoard(Board b, GuiPlayer p1, GuiPlayer p2) {
        this.board = b;
        this.firstPlayer = p1;
        this.secondPlayer = p2;
        this.imgBoard = new ImageView[b.getSize()][b.getSize()];
        fxmlLoader = new FXMLLoader(getClass().getResource("GuiBoard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void draw() {
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
                    imgBoard[i][j].setImage(new Image(getClass().getClassLoader().getResourceAsStream("minion.jpg")));
                    this.add(new Circle(10, Paint.valueOf("black")), j, i);

                } else if (type == secondType) {
                    imgBoard[i][j].setImage(new Image(getClass().getClassLoader().getResourceAsStream("minion2.jpg")));
                    this.add(new Circle(10, Paint.valueOf("orange")), j, i);

                } else {
                    imgBoard[i][j].setImage(new Image(getClass().getClassLoader().getResourceAsStream("/application/res/minion.jpg")));
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