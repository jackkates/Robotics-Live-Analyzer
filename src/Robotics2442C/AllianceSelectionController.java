package Robotics2442C;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.util.*;

/**
 * The controller for Alliance Selection.
 * <p>
 *     This controller does all the calculations and sorting with Alliance Selection.
 * </p>
 *
 * @author Octogonapus
 */

public class AllianceSelectionController implements Initializable {
    @FXML
    private final TableView<AlliancePair> mainTable = new TableView<>();
    private static final ObservableList<AlliancePair> tableData = FXCollections.observableArrayList();
    private static AlliancePair alliancePair = new AlliancePair();

    @FXML
    private TableColumn<AlliancePair, String> teamNameColumn;
    @FXML
    private TableColumn<AlliancePair, String> winPercentageColumn;

    public AllianceSelectionController() { }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainTable.setItems(tableData);
        mainTable.setEditable(false);

        teamNameColumn.setCellValueFactory(new PropertyValueFactory<AlliancePair, String>("teamName"));
        teamNameColumn.setCellFactory(TextFieldTableCell.<AlliancePair>forTableColumn());
        winPercentageColumn.setCellValueFactory(new PropertyValueFactory<AlliancePair, String>("teamWinPercentage"));
        winPercentageColumn.setCellFactory(TextFieldTableCell.<AlliancePair>forTableColumn());

        tableData.clear();

        Map<String, Double> sortedTeams = sortByValue(getWinPercent(DataManager.getXmlFileParsed()));
        for (String team : sortedTeams.keySet()) {
            alliancePair.setTeamName(team);
            String winPercentage = "" + sortedTeams.get(team) * 100;
            alliancePair.setTeamWinPercentage(winPercentage);
            tableData.add(alliancePair);
            System.out.println(team + ", " + sortedTeams.get(team) + " (initialize)");
        }
    }

    /**
     * Pairs each team with a percent of wins to total matches played.
     *
     * @param xmlFileParsed The saved file, parsed into memory by XOMHandler
     * @return  A Map containing each team and their respecting win percentage
     */
    private Map<String, Double> getWinPercent(Map<String, Map<String, Match>> xmlFileParsed) {
        Map<String, Double> winPercent = new HashMap<>(0);
        for (String team : xmlFileParsed.keySet()) {
            double score = 0;
            for (Match match : DataManager.getMatches(team)) {
                if (match.getRedAlliance1().equals(team) || match.getRedAlliance2().equals(team)) {
                    if (Integer.parseInt(match.getRedScore()) > Integer.parseInt(match.getBlueScore())) {
                        score++;
                    } else {
                        score--;
                        if (score < 0) {
                            score = 0;
                        }
                    }
                } else if (match.getBlueAlliance1().equals(team) || match.getBlueAlliance2().equals(team)) {
                    if (Integer.parseInt(match.getRedScore()) < Integer.parseInt(match.getBlueScore())) {
                        score++;
                    } else {
                        score--;
                        if (score < 0) {
                            score = 0;
                        }
                    }
                }
            }
            if (DataManager.getMatches(team).length != 0) {
                winPercent.put(team, (score / DataManager.getMatches(team).length));
                System.out.println(team + ", " + ((score / DataManager.getMatches(team).length)) + " (getWinPercent)");
            } else {
                System.out.println(team + " (getWinPercent, zero detected)");
            }
        }
        return winPercent;
    }

    /**
     * Sorts all teams via win percentage from high to low.
     *
     * @param map   A map of all teams and their respective win percentage
     * @return  A sorted map of all teams and their respective win percentages, from high to low
     */
    public static Map<String, Double> sortByValue(Map<String, Double> map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Object aList : list) {
            Map.Entry entry = (Map.Entry) aList;
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
