package Robotics2442C;

import java.io.*;

/**
 * @author Octogonapus
 */

public class NewMatchDataFile {
    private String competitionName;
    private String matchName;
    private String redAlliance1;
    private String redAlliance2;
    private String redAlliance3;
    private String blueAlliance1;
    private String blueAlliance2;
    private String blueAlliance3;
    private String redScore;
    private String blueScore;

    public NewMatchDataFile() { }

    public NewMatchDataFile(String competitionName) { this.competitionName = competitionName; }

    public void makeFile() {
        Writer writer = null;
        String startingPath = System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator");
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
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getRedAlliance1() {
        return redAlliance1;
    }

    public void setRedAlliance1(String redAlliance1) {
        this.redAlliance1 = redAlliance1;
    }

    public String getRedAlliance2() {
        return redAlliance2;
    }

    public void setRedAlliance2(String redAlliance2) {
        this.redAlliance2 = redAlliance2;
    }

    public String getRedAlliance3() {
        return redAlliance3;
    }

    public void setRedAlliance3(String redAlliance3) {
        this.redAlliance3 = redAlliance3;
    }

    public String getBlueAlliance1() {
        return blueAlliance1;
    }

    public void setBlueAlliance1(String blueAlliance1) {
        this.blueAlliance1 = blueAlliance1;
    }

    public String getBlueAlliance2() {
        return blueAlliance2;
    }

    public void setBlueAlliance2(String blueAlliance2) {
        this.blueAlliance2 = blueAlliance2;
    }

    public String getBlueAlliance3() {
        return blueAlliance3;
    }

    public void setBlueAlliance3(String blueAlliance3) {
        this.blueAlliance3 = blueAlliance3;
    }

    public String getRedScore() {
        return redScore;
    }

    public void setRedScore(String redScore) {
        this.redScore = redScore;
    }

    public String getBlueScore() {
        return blueScore;
    }

    public void setBlueScore(String blueScore) {
        this.blueScore = blueScore;
    }
}
