package Robotics2442C;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for deleting a competition.
 * <p>
 *     This controller handles the competition delete confirmation button.
 * </p>
 *
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
