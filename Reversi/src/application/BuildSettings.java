package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BuildSettings {
    private final String fileName = "settings.txt";
    private static BuildSettings instance;
    private int size;
    private char beginner;
    private String fName;
    private String SName;

    public BuildSettings() {
        this.size = 8;
        this.beginner = 'X';
        this.fName = "black";
        this.SName = "white";
        saveToFile();
    }

    public static BuildSettings getInstance() {
        if (instance == null) {

            instance = new BuildSettings();
        }
        // return the instance
        return instance;
    }

    public void loadFromFile() {
        try (FileReader reader = new FileReader(fileName)) {
            BufferedReader buffer = new BufferedReader(reader);
            this.size = Integer.valueOf(buffer.readLine());
            this.beginner = buffer.readLine().charAt(0);
            this.fName = buffer.readLine();
            this.SName = buffer.readLine();
            buffer.close();
            reader.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(fileName)) {
            PrintWriter buffer = new PrintWriter(writer);
            buffer.println(size);
            buffer.println(beginner);
            buffer.println(fName);
            buffer.println(SName);
            buffer.close();
            writer.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getBeginner() {
        return beginner;
    }

    public char getLast() {
        if (beginner == 'X') {
            return 'O';
        }
        return 'X';
    }

    public void setBeginner(char beginner) {
        this.beginner = beginner;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String sName) {
        SName = sName;
    }

    public String getFileName() {
        return fileName;
    }

}
