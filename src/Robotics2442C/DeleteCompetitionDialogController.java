package Robotics2442C;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Octogonapus
 */

public class DeleteCompetitionDialogController implements Initializable {

    private boolean didPress = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void deleteCompetitionButtonPressed(ActionEvent actionEvent) {
        didPress = true;
    }

    public boolean getPressState() {
        return didPress;
    }
}
