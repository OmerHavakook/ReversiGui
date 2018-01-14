package application;

import javafx.scene.image.Image;

public class GuiPlayer {
    private char type;
    private int score;
    private String color;
    private Image image;

    /**
     * constructor
     * @param type - the player type
     * @param color - the color of the player
     */
    public GuiPlayer(char type, String color) {
        this.color = color;
        this.type = type;
        this.score = 2;
        // set the player image based on its color member
        switch (color) {
        case "yellow":
            image = new Image(getClass().getClassLoader().getResourceAsStream("images/yellowCell.jpg"));
            break;
        case "blue":
            image = new Image(getClass().getClassLoader().getResourceAsStream("images/blueCell.jpg"));
            break;
        case "white":
            image = new Image(getClass().getClassLoader().getResourceAsStream("images/whiteCell.jpg"));
            break;
        default:
            image = new Image(getClass().getClassLoader().getResourceAsStream("images/blackCell.jpg"));
            break;
        }
    }

    /**
     * this method returns the color member
     * @return color - the color of the player
     */
    public String getColor() {
        return color;
    }

    /**
     * this method returns the image of the player
     * @return Image - the player image
     */
    public Image getIv() {
        return image;
    }

    /**
     * this method returns the type of the player
     * @return char - the player type
     */
    public char getType() {
        return type;
    }

    /**
     * this method returns the score of the player
     * @return int - score
     */
    public int getScore() {
        return score;
    }

    /**
     * this method sets the score of the player
     * @param number - the score of the
     */
    public void setScore(int number) {
        this.score = number;
    }

}
