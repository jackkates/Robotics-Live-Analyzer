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
 * @author Octogonapus
 */

public class AllianceSelectionController implements Initializable {
    @FXML
    private final TableView<AlliancePair> mainTable = new TableView<AlliancePair>();
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

    private Map<String, Double> getWinPercent(Map<String, Map<String, Match>> xmlFileParsed) {
        Map<String, Double> winPercent = new HashMap<String, Double>(0);
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

    public static Map<String, Double> sortByValue(Map<String, Double> map) {
        List list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
