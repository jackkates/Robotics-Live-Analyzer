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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * @author Octogonapus
 * @contributor jackkates
 */

public class Controller implements Initializable {
    public static File mainDirectory;
    private Main mainApp;
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
        compColumn.setEditable(false);
        compColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("competitionName"));
        compColumn.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        matchColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("matchName"));
        matchColumn.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        redAlliance1Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance1"));
        redAlliance1Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        redAlliance2Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance2"));
        redAlliance2Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        redAlliance3Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("redAlliance3"));
        redAlliance3Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        blueAlliance1Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance1"));
        blueAlliance1Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        blueAlliance2Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance2"));
        blueAlliance2Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        blueAlliance3Column.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueAlliance3"));
        blueAlliance3Column.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        redScoreColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("redScore"));
        redScoreColumn.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
        blueScoreColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("blueScore"));
        blueScoreColumn.setCellFactory(TextFieldTableCell.<Competition>forTableColumn());
    }

    public void setupApp(ActionEvent actionEvent) throws Exception {
        //Path of main directory for the application's data to be held in
        String startingPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer";
        //Create said main directory
        NewDirectory mainDirectory = new NewDirectory(startingPath, "");
        mainDirectory.makeDirectory(false);
        //Create data directory
        mainDirectory = new NewDirectory(startingPath, System.getProperty("file.separator") + "Data");
        mainDirectory.makeDirectory(true);
        //Create file holding file path of data directory and write the file path to said file
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(startingPath + System.getProperty("file.separator") + "openMe.txt")));
            writer.write(startingPath + System.getProperty("file.separator") + "Data");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ignored) { }
        }
        Controller.mainDirectory = new File(startingPath + System.getProperty("file.separator") + "Data");
    }

    public void initNewTeam(ActionEvent actionEvent) throws Exception {
        String teamName = Dialogs.showNewTeamDialog();
        if (teamName != null && !teams.contains(teamName)) {
            teams.add(teamName);
            NewDirectory forTeam = new NewDirectory(mainDirectory.toString(), teamName);
            forTeam.makeDirectory(true);
        }
    }

    public void deleteTeam(ActionEvent actionEvent) {
        if (teamList.getSelectionModel().getSelectedItem() != null) {
            teams.remove(teamList.getSelectionModel().getSelectedItem());
        }
    }

    public void initNewCompetition(ActionEvent actionEvent) throws Exception {
        String competitionName = Dialogs.showNewCompetitionDialog();
        //If a competition name was returned, add it to the list
        if (competitionName != null) {
            competitions.add(competitionName);
            if (!DirectotryContains.searchDirectory(new File(mainDirectory + System.getProperty("file.separator") + teamList.getSelectionModel().getSelectedItem()), competitionName)) {
                NewDirectory forCompetition = new NewDirectory(mainDirectory + System.getProperty("file.separator") + teamList.getSelectionModel().getSelectedItem(), competitionName);
                forCompetition.makeDirectory(true);
            }
        }
    }

    public void deleteCompetition(ActionEvent actionEvent) throws Exception {
        boolean doDelete = Dialogs.showDeleteCompetitionDialog();
        //If doDelete returns true, delete the currently selected competition
        if (doDelete) {
            try {
                competitions.remove(currentCompSelection);
            } catch (ArrayIndexOutOfBoundsException e) {
                //TODO: Implement user friendly error report
                e.printStackTrace();
            }
        }
    }

    //TODO: Implement initAnalyzeTeam
    public void initAnalyzeTeam(ActionEvent actionEvent) {
    }

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
        if(loadSave.getFolders() != null) {
            String[] futureTeams = loadSave.getFolders();
            Collections.addAll(teams, futureTeams);
        }
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
}
