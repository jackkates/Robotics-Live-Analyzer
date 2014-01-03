package Robotics2442C;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The starting file. Loads and shows the main gui.
 *
 * @author Octogonapus
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RLA_GUI.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("RoboDogs Live Analyzer");
        primaryStage.setScene(new Scene(root, 1700, 961));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
