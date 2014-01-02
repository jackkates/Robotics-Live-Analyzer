package Robotics2442C;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Octogonapus
 */

public class AlliancePair {
    private final SimpleStringProperty teamName = new SimpleStringProperty();
    private final SimpleStringProperty teamWinPercentage = new SimpleStringProperty();

    public AlliancePair() { };

    public AlliancePair(String teamName, String teamWinPercentage) {
        this.teamName.set(teamName);
        this.teamWinPercentage.set(teamWinPercentage);
    }

    public String getTeamName() {
        return teamName.get();
    }

    public SimpleStringProperty teamNameProperty() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName.set(teamName);
    }

    public String getTeamWinPercentage() {
        return teamWinPercentage.get();
    }

    public SimpleStringProperty teamWinPercentageProperty() {
        return teamWinPercentage;
    }

    public void setTeamWinPercentage(String teamWinPercentage) {
        this.teamWinPercentage.set(teamWinPercentage);
    }
}