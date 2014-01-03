package Robotics2442C;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * The main controller for the application.
 * <p>
 *     This controller handles all events with the main gui.
 * </p>
 *
 * @author Octogonapus
 * @author jackkates
 */

public class Controller implements Initializable {
    /**
     * The main directory where all files for the application reside.
     */
    public static File mainDirectory;
    /**
     * The operating system specific file separator.
     */
    public static final String fileSeparator = System.getProperty("file.separator");
    private Main mainApp;
    /**
     * Whether or not this is the user's first time saving.
     */
    public static boolean firstSave = true;

    /**
     * RLA_GUI.fxml
     */
    @FXML
    private final ListView<String> teamList = new ListView<String>();
    private static final ObservableList<String> teams = FXCollections.observableArrayList();
    private String currentTeamSelection;
    @FXML
    private final TableView<Match> mainTable = new TableView<Match>();
    private static final ObservableList<Match> tableData = FXCollections.observableArrayList();
    private String currentMatchSelection;

    /**
     * TableView
     */
    @FXML
    private TableColumn<Match, String> matchColumn;
    @FXML
    private TableColumn<Match, String> redAlliance1Column;
    @FXML
    private TableColumn<Match, String> redAlliance2Column;
    @FXML
    private TableColumn<Match, String> redAlliance3Column;
    @FXML
    private TableColumn<Match, String> blueAlliance1Column;
    @FXML
    private TableColumn<Match, String> blueAlliance2Column;
    @FXML
    private TableColumn<Match, String> blueAlliance3Column;
    @FXML
    private TableColumn<Match, String> redScoreColumn;
    @FXML
    private TableColumn<Match, String> blueScoreColumn;

    @FXML
    private MenuItem newTeamMenuItem;

    public Controller() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**
         * RLA_GUI.fxml
         */
        teamList.setItems(teams);
        teamList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
                currentTeamSelection = s2;
                tableData.clear();
                if (s2 != null) {
                    Collections.addAll(tableData, DataManager.getMatches(s2));
                }
            }
        });

        mainTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Match>() {
            @Override
            public void changed(ObservableValue<? extends Match> observableValue, Match match, Match match2) {
                if (match2 != null) {
                    currentMatchSelection = match2.getMatchName();
                }
            }
        });

        mainTable.setEditable(true);
        mainTable.setItems(tableData);

        /**
         * TableView Columns
         */
        matchColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("matchName"));
        matchColumn.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        matchColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setMatchName(t.getNewValue());
                DataManager.renameMatch(currentTeamSelection, t.getOldValue(), t.getNewValue());
            }
        });
        redAlliance1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("redAlliance1"));
        redAlliance1Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redAlliance1Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedAlliance1(t.getNewValue());
                DataManager.setRedAlliance1(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        redAlliance2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("redAlliance2"));
        redAlliance2Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redAlliance2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedAlliance2(t.getNewValue());
                DataManager.setRedAlliance2(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        redAlliance3Column.setCellValueFactory(new PropertyValueFactory<Match, String>("redAlliance3"));
        redAlliance3Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redAlliance3Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedAlliance3(t.getNewValue());
                DataManager.setRedAlliance3(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        blueAlliance1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance1"));
        blueAlliance1Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance1Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance1(t.getNewValue());
                DataManager.setBlueAlliance1(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        blueAlliance2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance2"));
        blueAlliance2Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance2(t.getNewValue());
                DataManager.setBlueAlliance2(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        blueAlliance3Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance3"));
        blueAlliance3Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance3Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance3(t.getNewValue());
                DataManager.setBlueAlliance3(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        redScoreColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("redScore"));
        redScoreColumn.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redScoreColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedScore(t.getNewValue());
                DataManager.setRedScore(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
        blueScoreColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("blueScore"));
        blueScoreColumn.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueScoreColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueScore(t.getNewValue());
                DataManager.setBlueScore(currentTeamSelection, currentMatchSelection, t.getNewValue());
            }
        });
    }

    /**
     * Requests a main folder setup from DataManager
     *
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void setupApp(ActionEvent actionEvent) throws Exception {
        DataManager.setupMainFolder();
    }

    /**
     * Takes a string from the user, via Dialogs, and adds that string (if it is not null, and if the current list does
     * not contain that same string) to the list of teams. It also calls for DataManager to add the string to the Map of
     * teams.
     *
     * @see Robotics2442C.Dialogs
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void newTeam(ActionEvent actionEvent) throws Exception {
        String teamName = Dialogs.showNewTeamDialog();
        if (teamName != null && !teams.contains(teamName)) {
            teams.add(teamName);
            DataManager.newTeam(teamName);
        }
    }

    /**
     * Removes the currently selected team (if that team is not null) from the list of teams, and calls for DataManager
     * to remove that team from the Map of teams.
     *
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void deleteTeam(ActionEvent actionEvent) {
        if (teamList.getSelectionModel().getSelectedItem() != null) {
            teams.remove(currentTeamSelection);
            DataManager.deleteTeam(currentTeamSelection);
        }
    }

    /**
     * Takes a string from the user, via Dialogs, and adds that string (if the current team selection is not null, and if
     * it is not null) to the list of matches for the currently selected team. It also calls for DataManager to add the
     * string to the proper team in the Map of teams, and to generate empty tags for that match. It then refreshes the
     * main table of matches.
     *
     * @see Robotics2442C.Dialogs
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void newMatch(ActionEvent actionEvent) throws Exception {
        String matchName = Dialogs.showNewMatchDialog();
        if (currentTeamSelection != null) {
            if (matchName != null) {
                tableData.add(0, new Match());
                tableData.get(0).setMatchName(matchName);
                DataManager.newMatch(currentTeamSelection, matchName);
                tableData.clear();
                Collections.addAll(tableData, DataManager.getMatches(currentTeamSelection));
            }
        }
    }

    /**
     * Removed the currently selected match (if that match is not null) from the main table, and calls for DataManager
     * to remove the match from the list of matches for the currently selected team.
     *
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void deleteMatch(ActionEvent actionEvent) {
        if (mainTable.getSelectionModel().selectedItemProperty().getValue() != null) {
            tableData.remove(mainTable.getSelectionModel().selectedItemProperty().getValue());
            DataManager.deleteMatch(currentTeamSelection, currentMatchSelection);
        }
    }

    /**
     * Generates a file chooser, and calls for DataManager to open the chosen file (if the file is not null). It then
     * sets <code>firstSave</code> to false.
     *
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void openApp(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML Resource File");
        fileChooser.setInitialDirectory(new File((System.getProperty("user.home"))));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            DataManager.openApp(file);
            Collections.addAll(teams, DataManager.getTeamNames());
            Controller.firstSave = false;
        }
    }

    /**
     * If <code>firstSave</code> if true, it takes a string from the user, via Dialogs, and calls for DataManager to save to a new file with the name of that
     * string. Otherwise, it calls for DataManager to save to a file with the string in <code>fileName</code>.
     *
     * @see Robotics2442C.DataManager
     */
    @FXML
    private void saveApp(ActionEvent actionEvent) throws IOException {
        if (Controller.firstSave) {
            String fileName = Dialogs.showSetupFileDialog();
            DataManager.saveApp(fileName);
        } else {
            DataManager.saveApp();
        }
        Controller.firstSave = false;
    }

    /**
     * Calls to System for the application to exit with exit code 0
     */
    @FXML
    private void closeApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Calls to Dialogs to show the Alliance Selection dialog.
     *
     * @see Robotics2442C.Dialogs
     */
    @FXML
    private void initAllianceSelection(ActionEvent actionEvent) throws IOException {
        Dialogs.showAllianceSelectionDialog();
    }

    /**
     * Calls to Dialogs to show the Match Pairing dialog.
     *
     * @see Robotics2442C.Dialogs
     */
    @FXML
    private void initMatchPairing(ActionEvent actionEvent) {
        //
    }

    /**
     * Convenience method for loading .fxml files.
     */
    private Parent load(String name) throws IOException {
        return FXMLLoader.load(getClass().getResource(name));
    }

    /**
     * Sets the file path of the main directory where all files for the application reside.
     */
    public static void setMainDirectory() {
        Controller.mainDirectory = new File(System.getProperty("user.home") + fileSeparator + "RoboDogs Live Analyzer" + fileSeparator + "Data");
    }
}
