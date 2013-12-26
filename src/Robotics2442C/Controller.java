package Robotics2442C;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * TODO: Remove ChoiceBox and use something with the MenuBar to edit specific competitions, one at a time.
 * TODO: The TableView will adapt properly to show data to do only with the selected competition.
 * TODO: When not editing a competition, the TableView will show all competitions.
 */

/**
 * @author Octogonapus
 * @contributor jackkates
 */

public class Controller implements Initializable {
    /**
     * RLA_GUI.fxml
     */
    @FXML
    private final ListView<String> teamList = new ListView<String>();
    private static final ObservableList<String> teams = FXCollections.observableArrayList();
    @FXML
    private TextField searchField;
    @FXML
    private MenuBar menuBar;
    @FXML
    private final ChoiceBox<String> competitionChooser = new ChoiceBox<String>();
    private static final ObservableList<String> competitions = FXCollections.observableArrayList();

    /**
     * RLA_NewTeamDialog.fxml
     */
    @FXML
    private TextField newTeamField;

    /**
     * RLA_NewCompDialog.fxml
     */
    @FXML
    private TextField newCompField;

    public Controller() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * RLA_GUI.fxml
         */
        teamList.setItems(teams);
        competitionChooser.setItems(competitions);
    }

    //TODO: Use a system of folders and files to store team data

    public void initNewTeam(ActionEvent actionEvent) throws Exception {
        Parent root = load("RLA_NewTeamDialog.fxml");
        Dialog.show("New Team", root, 353, 259);
    }

    public void newTeam(ActionEvent actionEvent) {
        teams.add(newTeamField.getText());
    }

    public void deleteTeam(ActionEvent actionEvent) {
        if (teamList.getSelectionModel().getSelectedItem() != null) {
            teams.remove(teamList.getSelectionModel().getSelectedItem());
        }
    }

    public void initNewCompetition(ActionEvent actionEvent) throws Exception {
        Parent root = load("RLA_NewCompDialog.fxml");
        Dialog.show("New Competition", root, 353, 239);
    }

    public void newCompetition(ActionEvent actionEvent) {
        competitions.add(newCompField.getText());
    }

    public void initDeleteCompetition(ActionEvent actionEvent) throws Exception {
        Parent root = load("RLA_CompDeleteWarning.fxml");
        Dialog.show("Really Delete Competition?", root, 353, 239);
    }

    public void deleteConfirm(ActionEvent actionEvent) {
        competitions.remove();
    }

    public void openApp(ActionEvent actionEvent) {
        //TODO: Implement open function
    }

    public void saveApp(ActionEvent actionEvent) {
        //TODO: Implement save function
    }

    public void saveAsApp(ActionEvent actionEvent) {
        //TODO: Implement save as function
    }

    public void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Convenience method for loading .fxml files
     */
    private Parent load(String name) throws IOException {
        return FXMLLoader.load(getClass().getResource(name));
    }
}
