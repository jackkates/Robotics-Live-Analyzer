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
 * @author jackkates
 */
public class NewCompetitionDialogController implements Initializable {

    @FXML
    private TextField newCompField;

    @FXML
    private Button addCompetitionButton;

    private String competitionName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void newCompetitionButtonPressed(ActionEvent actionEvent) {
        competitionName = newCompField.getText();
        //Get stage and close stage
        Stage stage = (Stage) addCompetitionButton.getScene().getWindow();
        stage.close();
    }

    public String getCompetitionName() {
        return competitionName;
    }
}
