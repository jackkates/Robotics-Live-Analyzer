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

    public static String showNewCompetitionDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_NewCompDialog.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Competition");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

        NewCompetitionDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getCompetitionName();
    }

    public static boolean showDeleteCompetitionDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("RLA_CompDeleteWarning.fxml"));
        Parent page = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Really Delete Competition?");
        stage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        stage.setScene(scene);

        DeleteCompetitionDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getPressState();
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
}
