package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // create parent from menuFXML
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuFXML.fxml"));
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            // get the scene and show it
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the main method
     * @param args - no args from user
     */
    public static void main(String[] args) {
        launch(args);
    }
}
