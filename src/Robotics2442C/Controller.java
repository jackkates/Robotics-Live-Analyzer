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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * @author Octogonapus
 * @author jackkates
 */

public class Controller implements Initializable {
    public static File mainDirectory;
    public static final String fileSeparator = System.getProperty("file.separator");
    private Main mainApp;
    /**
     * RLA_GUI.fxml
     */
    @FXML
    private final ListView<String> teamList = new ListView<String>();
    private static final ObservableList<String> teams = FXCollections.observableArrayList();
    private String currentTeamSelection;
    @FXML
    private TextField searchField;
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
                currentTeamSelection = s2;
                //TODO: Fix teamList ChangeListener
                /*CBE_Handler handler = new CBE_Handler();
                if (handler.loadMatches(s2) != null) {
                    tableData.clear();
                    tableData.setAll(handler.loadMatches(s2));
                }*/
            }
        });

        mainTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Match>() {
            @Override
            public void changed(ObservableValue<? extends Match> observableValue, Match match, Match match2) {
                currentMatchSelection = match2.getMatchName();
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
            }
        });
        redAlliance3Column.setCellValueFactory(new PropertyValueFactory<Match, String>("redAlliance3"));
        redAlliance3Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redAlliance3Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedAlliance3(t.getNewValue());
            }
        });
        blueAlliance1Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance1"));
        blueAlliance1Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance1Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance1(t.getNewValue());
            }
        });
        blueAlliance2Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance2"));
        blueAlliance2Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance2Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance2(t.getNewValue());
            }
        });
        blueAlliance3Column.setCellValueFactory(new PropertyValueFactory<Match, String>("blueAlliance3"));
        blueAlliance3Column.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueAlliance3Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueAlliance3(t.getNewValue());
            }
        });
        redScoreColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("redScore"));
        redScoreColumn.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        redScoreColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setRedScore(t.getNewValue());
            }
        });
        blueScoreColumn.setCellValueFactory(new PropertyValueFactory<Match, String>("blueScore"));
        blueScoreColumn.setCellFactory(TextFieldTableCell.<Match>forTableColumn());
        blueScoreColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Match, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Match, String> t) {
                t.getTableView().getItems().get(t.getTablePosition().getRow()).setBlueScore(t.getNewValue());
            }
        });
    }

    public void setupApp(ActionEvent actionEvent) throws Exception {
        DataManager.setupMainFolder();
    }

    public void newTeam(ActionEvent actionEvent) throws Exception {
        String teamName = Dialogs.showNewTeamDialog();
        if (teamName != null && !teams.contains(teamName)) {
            teams.add(teamName);
            DataManager.newTeam(teamName);
        }
    }

    public void deleteTeam(ActionEvent actionEvent) {
        if (teamList.getSelectionModel().getSelectedItem() != null) {
            teams.remove(currentTeamSelection);
            DataManager.deleteTeam(currentTeamSelection);
        }
    }

    public void newMatch(ActionEvent actionEvent) throws Exception {
        String matchName = Dialogs.showNewMatchDialog();
        if (currentTeamSelection != null) {
            tableData.add(0, new Match());
            tableData.get(0).setMatchName(matchName);
            DataManager.newMatch(currentTeamSelection, matchName);
            DataManager.fillMatch(currentTeamSelection, matchName);
        }
    }

    //TODO: Implement initAnalyzeTeam
    public void initAnalyzeTeam(ActionEvent actionEvent) {
        //
    }

    //TODO: Implement initAnalyzeComp
    public void initAnalyzeComp(ActionEvent actionEvent) {
        //
    }

    public void openApp(ActionEvent actionEvent) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open XML Resource File");
        fileChooser.setInitialDirectory(new File((System.getProperty("user.home"))));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        DataManager.openApp(file);
        Collections.addAll(teams, DataManager.getTeamNames());
    }

    public void saveApp(ActionEvent actionEvent) {
        DataManager.saveApp();
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

    public void setMainApp(Main main) {
        this.mainApp = main;
    }

    public static void setMainDirectory() {
        Controller.mainDirectory = new File(System.getProperty("user.home") + fileSeparator + "Robotics Live Analyzer" + fileSeparator + "Data");
    }
}
