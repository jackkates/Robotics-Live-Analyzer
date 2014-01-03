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
 * The controller for adding a match.
 * <p>
 *     This controller handles getting the name for a new match.
 * </p>
 *
 * @author Octogonapus
 */

public class NewMatchDialogController implements Initializable {
    @FXML
    private TextField newMatchField;
    @FXML
    private Button addMatchButton;
    private String matchName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void addMatchButtonPressed(ActionEvent actionEvent) {
        matchName = newMatchField.getText();
        //Get stage and close stage
        Stage stage = (Stage) addMatchButton.getScene().getWindow();
        stage.close();
    }

    public String getMatchName() {
        return matchName;
    }
}
