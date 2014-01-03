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
 * The controller for saving a file for the first time.
 * <p>
 *     This controller handles getting the name for a new file.
 * </p>
 *
 * @author Octogonapus
 */

public class SetupFileDialogController implements Initializable {
    @FXML
    private TextField fileNameField;
    @FXML
    private Button nameFileButton;
    private String fileName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void nameFileButtonPressed(ActionEvent actionEvent) {
        fileName = fileNameField.getText();
        //Get stage and close stage
        Stage stage = (Stage) nameFileButton.getScene().getWindow();
        stage.close();
    }

    public String getFileName() {
        return fileName;
    }
}
