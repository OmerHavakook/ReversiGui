package application;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiPlayer{
    private char type;
    private int score;
    private Image iv;
    public GuiPlayer(char type) {
        this.type = type;
        this.score = 2;
        if (type == 'O'){
            iv = new Image(getClass().getClassLoader().getResourceAsStream("minion.jpg"));
            
        } else {
            InputStream input = getClass().getClassLoader().getResourceAsStream("minion.jpg");
            iv = new Image(input);
        }
        
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
