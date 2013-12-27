package Robotics2442C;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Octogonapus
 */

public class Competition {
    private final SimpleStringProperty competitionName;
    private final SimpleStringProperty matchName;
    private final SimpleStringProperty redAlliance1;
    private final SimpleStringProperty redAlliance2;
    private final SimpleStringProperty redAlliance3;
    private final SimpleStringProperty blueAlliance1;
    private final SimpleStringProperty blueAlliance2;
    private final SimpleStringProperty blueAlliance3;
    private final SimpleStringProperty redScore;
    private final SimpleStringProperty blueScore;

    public Competition(String competitionName, String matchName, String redAlliance1, String redAlliance2, String redAlliance3, String blueAlliance1, String blueAlliance2, String blueAlliance3, String redScore, String blueScore) {
        this.competitionName = new SimpleStringProperty(competitionName);
        this.matchName = new SimpleStringProperty(matchName);
        this.redAlliance1 = new SimpleStringProperty(redAlliance1);
        this.redAlliance2 = new SimpleStringProperty(redAlliance2);
        this.redAlliance3 = new SimpleStringProperty(redAlliance3);
        this.blueAlliance1 = new SimpleStringProperty(blueAlliance1);
        this.blueAlliance2 = new SimpleStringProperty(blueAlliance2);
        this.blueAlliance3 = new SimpleStringProperty(blueAlliance3);
        this.redScore = new SimpleStringProperty(redScore);
        this.blueScore = new SimpleStringProperty(blueScore);
    }

    public String getCompetitionName() {
        return competitionName.get();
    }

    public SimpleStringProperty competitionNameProperty() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName.set(competitionName);
    }

    public String getMatchName() {
        return matchName.get();
    }

    public SimpleStringProperty matchNameProperty() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName.set(matchName);
    }

    public String getRedAlliance1() {
        return redAlliance1.get();
    }

    public SimpleStringProperty redAlliance1Property() {
        return redAlliance1;
    }

    public void setRedAlliance1(String redAlliance1) {
        this.redAlliance1.set(redAlliance1);
    }

    public String getRedAlliance2() {
        return redAlliance2.get();
    }

    public SimpleStringProperty redAlliance2Property() {
        return redAlliance2;
    }

    public void setRedAlliance2(String redAlliance2) {
        this.redAlliance2.set(redAlliance2);
    }

    public String getRedAlliance3() {
        return redAlliance3.get();
    }

    public SimpleStringProperty redAlliance3Property() {
        return redAlliance3;
    }

    public void setRedAlliance3(String redAlliance3) {
        this.redAlliance3.set(redAlliance3);
    }

    public String getBlueAlliance1() {
        return blueAlliance1.get();
    }

    public SimpleStringProperty blueAlliance1Property() {
        return blueAlliance1;
    }

    public void setBlueAlliance1(String blueAlliance1) {
        this.blueAlliance1.set(blueAlliance1);
    }

    public String getBlueAlliance2() {
        return blueAlliance2.get();
    }

    public SimpleStringProperty blueAlliance2Property() {
        return blueAlliance2;
    }

    public void setBlueAlliance2(String blueAlliance2) {
        this.blueAlliance2.set(blueAlliance2);
    }

    public String getBlueAlliance3() {
        return blueAlliance3.get();
    }

    public SimpleStringProperty blueAlliance3Property() {
        return blueAlliance3;
    }

    public void setBlueAlliance3(String blueAlliance3) {
        this.blueAlliance3.set(blueAlliance3);
    }

    public String getRedScore() {
        return redScore.get();
    }

    public SimpleStringProperty redScoreProperty() {
        return redScore;
    }

    public void setRedScore(String redScore) {
        this.redScore.set(redScore);
    }

    public String getBlueScore() {
        return blueScore.get();
    }

    public SimpleStringProperty blueScoreProperty() {
        return blueScore;
    }

    public void setBlueScore(String blueScore) {
        this.blueScore.set(blueScore);
    }
}