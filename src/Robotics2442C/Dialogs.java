package Robotics2442C;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Creator: jackkates
 * Date: 12/27/13
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

        Scene scene =  new Scene(page);
        stage.setScene(scene);

        NewCompetitionDialogController controller = loader.getController();

        stage.showAndWait();

        return controller.getCompetitionName();
    }
}
