package Robotics2442C;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author jackkates
 */
public class NewTeamDialogController implements Initializable {

    @FXML
    private TextField newTeamField;

    @FXML
    private Button addTeamButton;

    private String teamName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addTeamButtonPressed(ActionEvent actionEvent) {
        teamName = newTeamField.getText();
        //Get stage and close stage
        Stage stage = (Stage) addTeamButton.getScene().getWindow();
        stage.close();
    }

    public String getTeamName() {
        return teamName;
    }
}
