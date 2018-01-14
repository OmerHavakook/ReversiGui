package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// BuildSettings is a singleton
public class BuildSettings {
    private final String fileName = "settings.txt";
    private static BuildSettings instance;
    private int size;
    private char beginner;
    private String firstPlayerColor;
    private String secondPlayerColor;

    /**
     * BuildSettings constructor which sets the game with default settings.
     */
    public BuildSettings() {
        setDefauldSettings();
        // save the settings to the settings.txt file
        saveToFile();
    }

    /**
     * this method checks if this class has already created an instance. If it's
     * the first time the program needed an instance, than create one with the
     * default settings, else, return the current instance.
     * @return an instance of BuildSettings
     */
    public static BuildSettings getInstance() {
        // first creation with default settings
        if (instance == null) {
            instance = new BuildSettings();
        }
        // return this instance
        return instance;
    }

    /**
     * this method loads the settings from the settings file and updates this
     * class members
     */
    public void loadFromFile() {
        // try to create a file reader for "settings.txt"
        try (FileReader reader = new FileReader(fileName)) {
            // create a buffer and sets the members by reading line after line
            // with the buffer
            BufferedReader buffer = new BufferedReader(reader);
            this.size = Integer.valueOf(buffer.readLine());
            this.beginner = buffer.readLine().charAt(0);
            this.firstPlayerColor = buffer.readLine();
            this.secondPlayerColor = buffer.readLine();
            // close buffer and than close the fileReader
            buffer.close();
            reader.close();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }

    /**
     * this method sets the default settings of the game
     */
    public void setDefauldSettings() {
        this.size = 8;
        this.beginner = 'X';
        this.firstPlayerColor = "black";
        this.secondPlayerColor = "white";
        saveToFile();
    }

    /**
     * this method saves the information of the members to the settings file
     */
    public void saveToFile() {
        // try to create a writer to the "settings.txt"
        try (FileWriter writer = new FileWriter(fileName)) {
            // create a PrintWriter buffer
            PrintWriter buffer = new PrintWriter(writer);
            // write each member in a line
            buffer.println(size);
            buffer.println(beginner);
            buffer.println(firstPlayerColor);
            buffer.println(secondPlayerColor);
            // close the buffer and the FileWriter
            buffer.close();
            writer.close();
        } catch (IOException exception) {
            // TODO Auto-generated catch block
            exception.printStackTrace();
        }
    }

    /**
     * this method returns the game board size
     * @return int - the size
     */
    public int getSize() {
        return size;
    }

    /**
     * this method sets the game board size
     * @param size - the size of the board
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * this method returns the type of the game begginer
     * @return char - 'X' or 'O'
     */
    public char getBeginner() {
        return beginner;
    }

    /**
     * this method returns the type of the second game player
     * @return char - 'O' or 'X'
     */
    public char getLast() {
        // checks if 'X' is the begginer
        if (beginner == 'X') {
            return 'O';
        }
        // if 'O' is the begginer player
        return 'X';
    }

    /**
     * this method sets the type of the game begginer
     * @param beginner - the type of the begginer ('X' or 'O')
     */
    public void setBeginner(char beginner) {
        this.beginner = beginner;
    }

    /**
     * this method returns the color of the first player
     * @return string - the color of the first player
     */
    public String getFirstPlayerColor() {
        return firstPlayerColor;
    }

    /**
     * this method sets the color of the first player
     * @param color - string (the color of the first player)
     */
    public void setfirstPlayerColor(String color) {
        this.firstPlayerColor = color;
    }

    /**
     * this method returns the color of the second player
     * @return string - the color of the second player
     */
    public String getSecondPlayerColor() {
        return secondPlayerColor;
    }

    /**
     * this method sets the color of the second player
     * @param color - string (the color of the second player)
     */
    public void setSecondPlayerColor(String color) {
        secondPlayerColor = color;
    }

    /**
     * this method returns the file name
     * @return string - the file name
     */
    public String getFileName() {
        return fileName;
    }
}