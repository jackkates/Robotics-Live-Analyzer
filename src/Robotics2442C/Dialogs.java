package Robotics2442C;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author jackkates
 * @author Octogonapus
 */

public class Dialogs {

    public static String showNewTeamDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_NewTeamDialog.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Team");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene =  new Scene(page);
        stage.setScene(scene);

        NewTeamDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getTeamName();
    }

    public static String showNewMatchDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_NewMatchDialog.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Match");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

        NewMatchDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getMatchName();
    }

    public static String showSetupFileDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_SetupFileDialog.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Name File");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

        SetupFileDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getFileName();
    }

    public static void showAllianceSelectionDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_AllianceSelectionDialog.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Best Alliance Partners");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

        AllianceSelectionController controller = loader.getController();

        stage.showAndWait();
    }
}
