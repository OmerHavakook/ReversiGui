package application;

import javafx.scene.image.Image;

public class GuiPlayer{
    private char type;
    private int score;
    private String color;
    private Image iv;

    public GuiPlayer(char type, String color) {
        this.color = color;
        this.type = type;
        this.score = 2;
        switch (color) {
        case "yellow":
            iv = new Image(getClass().getClassLoader().getResourceAsStream("yellowCell.png"));
            break;
        case "blue":
            iv = new Image(getClass().getClassLoader().getResourceAsStream("blueCell.png"));
            break;
        case "white":
            iv = new Image(getClass().getClassLoader().getResourceAsStream("whiteCell.png"));
            break;
        default:
            iv = new Image(getClass().getClassLoader().getResourceAsStream("blackCell.png"));
            break;
        }
        
    }

    public String getColor() {
        return color;
    }

    public Image getIv() {
        return iv;
    }

    public char getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int number) {
        this.score = number;
    }

}
