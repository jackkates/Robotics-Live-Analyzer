package Robotics2442C;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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
    public static File mainDirectory;
    /**
     * RLA_GUI.fxml
     */
    @FXML
    private final ListView<String> teamList = new ListView<String>();
    private static final ObservableList<String> teams = FXCollections.observableArrayList();
    @FXML
    private TextField searchField;
    @FXML
    private final ChoiceBox<String> competitionChooser = new ChoiceBox<String>();
    private static final ObservableList<String> competitions = FXCollections.observableArrayList();
    private int currentCompSelection;
    @FXML
    private final TableView<Competition> mainTable = new TableView<Competition>();
    private static final ObservableList<Competition> tableData = FXCollections.observableArrayList();
    /**
     * TableView
     */
    @FXML
    private TableColumn<Competition, String> compColumn;
    @FXML
    private TableColumn<Competition, String> matchColumn;
    @FXML
    private TableColumn<Competition, String> redAlliance1Column;
    @FXML
    private TableColumn<Competition, String> redAlliance2Column;
    @FXML
    private TableColumn<Competition, String> redAlliance3Column;
    @FXML
    private TableColumn<Competition, String> blueAlliance1Column;
    @FXML
    private TableColumn<Competition, String> blueAlliance2Column;
    @FXML
    private TableColumn<Competition, String> blueAlliance3Column;
    @FXML
    private TableColumn<Competition, String> redScoreColumn;
    @FXML
    private TableColumn<Competition, String> blueScoreColumn;

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
        teamList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                LoadForTeam loadForTeam = new LoadForTeam(s2);
                if (loadForTeam.getCompetitions() != null) {
                    Collections.addAll(tableData, loadForTeam.getCompetitions());
                }
            }
        });

        competitionChooser.setItems(competitions);
        competitionChooser.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                currentCompSelection = number2.intValue();
            }
        });

        mainTable.setEditable(true);
        mainTable.setItems(tableData);

        /**
         * TableView
         */
        compColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("competitionName"));
        matchColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("matchName"));
        redAlliance1Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance1"));
        redAlliance2Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance2"));
        redAlliance3Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance3"));
        blueAlliance1Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance1"));
        blueAlliance2Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance2"));
        blueAlliance3Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance3"));
        redScoreColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("redScore"));
        blueScoreColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueScore"));
}

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
        try {
            competitions.remove(currentCompSelection);
        } catch (ArrayIndexOutOfBoundsException e) {
            //TODO: Implement user friendly error report
            e.printStackTrace();
        }
    }

    //TODO: Implement initAnalyzeTeam
    public void initAnalyzeTeam(ActionEvent actionEvent) {}

    public void initAnalyzeComp(ActionEvent actionEvent) {
        //
    }

    public void openApp(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File((System.getProperty("user.home"))));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        File file = fileChooser.showOpenDialog(stage);
        LoadSave loadSave = new LoadSave(file);
        if (loadSave.getFolders() != null) {
            String[] futureTeams = loadSave.getFolders();
            Collections.addAll(teams, futureTeams);
        }
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
