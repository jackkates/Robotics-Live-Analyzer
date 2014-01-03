package Robotics2442C;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Delegates other classes with requested tasks, acting like the center point of the application.
 *
 * @author Octogonapus
 */

public class DataManager {
    private static Map<String, Map<String, Match>> xmlFileParsed = new HashMap<>(0);
    public static String fileName = "";
    private static Map<String, String> winPercentage = new HashMap<>(0);

    /**
     * Creates the main directory where all files for the application reside, and then sets the main directory to the
     * created one.
     */
    public static void setupMainFolder() throws IOException {
        //Path of main directory for the application's data to be held in
        String startingPath = System.getProperty("user.home") + Controller.fileSeparator + "RoboDogs Live Analyzer";
        //Create said main directory
        DirectoryTools.makeDirectory(startingPath, "", false);
        Controller.setMainDirectory();
    }

    /**
     * Testing method for the loaded file data structure. Prints all data in the loaded file.
     */
    public static void testBoundFile() {
        for (Map.Entry<String, Map<String, Match>> entry : xmlFileParsed.entrySet()) {
            System.out.println("\nTeam : " + entry.getKey());
            Map<String, Match> fileTemp = new HashMap<>(0);
            fileTemp.putAll(entry.getValue());
            for (Map.Entry<String, Match> entryDeep : fileTemp.entrySet()) {
                System.out.println("Match : " + entryDeep.getKey());
                System.out.println("\nMatch Object Data\n----------------------------");
                System.out.println("Match Name : " + entryDeep.getValue().getMatchName());
                System.out.println("Red Alliance 1 Name : " + entryDeep.getValue().getRedAlliance1());
                System.out.println("Red Alliance 2 Name : " + entryDeep.getValue().getRedAlliance2());
                System.out.println("Red Alliance 3 Name : " + entryDeep.getValue().getRedAlliance3());
                System.out.println("Blue Alliance 1 Name : " + entryDeep.getValue().getBlueAlliance1());
                System.out.println("Blue Alliance 2 Name : " + entryDeep.getValue().getBlueAlliance2());
                System.out.println("Blue Alliance 3 Name : " + entryDeep.getValue().getBlueAlliance3());
                System.out.println("Red Score : " + entryDeep.getValue().getRedScore());
                System.out.println("Blue Score : " + entryDeep.getValue().getBlueScore());
            }
        }
    }

    /**
     * Loads a file into memory via XOMHandler.
     *
     * @param file  The file to load
     * @see Robotics2442C.XOMHandler
     */
    public static void openApp(File file) {
        xmlFileParsed.putAll(XOMHandler.load(file));
        DataManager.fileName = file.getName().replace(".xml", "");
    }

    /**
     * Writes a file, containing all data from memory, to the HDD with a specific file name via XOMHandler, and sets <code>firstSave</code> to false. Also
     * sets <code>fileName</code> to the given file name.
     *
     * @param fileName  The name of the file to write
     * @see Robotics2442C.XOMHandler
     */
    public static void saveApp(String fileName) throws IOException {
        Controller.firstSave = false;
        DataManager.fileName = fileName;
        XOMHandler.save(xmlFileParsed, fileName);
    }

    /**
     * Overwrites the currently open file with all data from memory with XOMHandler.
     *
     * @see Robotics2442C.XOMHandler
     */
    public static void saveApp() throws IOException {
        XOMHandler.save(xmlFileParsed, DataManager.fileName);
    }

    /**
     * Creates a new team.
     *
     * @param teamName  The name of the team to create
     */
    public static void newTeam(String teamName) {
        xmlFileParsed.put(teamName, new HashMap<String, Match>(0));
    }

    /**
     * Gets the match with the specified team name and number.
     * @param teamName  The name of the owning team
     * @param matchName The name of the match to retrieve
     * @return          A match
     */
    public static Match getMatch(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName);
    }

    /**
     * Creates a new match.
     *
     * @param teamName  The name of the team which will receive the match
     * @param matchName The name of the match to create
     */
    public static void newMatch(String teamName, String matchName) {
        Match match = new Match();
        match.setMatchName(matchName);
        xmlFileParsed.get(teamName).put(matchName, match);
    }

    /**
     * Renames a match.
     *
     * @param teamName      The name of the team which holds the match
     * @param matchName     The name of the match to rename
     * @param newMatchName  The new name of the match
     */
    public static void renameMatch(String teamName, String matchName, String newMatchName) {
        Match match = getMatch(teamName, matchName);
        match.setMatchName(newMatchName);
        //Delete old match
        deleteMatch(teamName, matchName);
        newMatch(teamName, newMatchName);

    }

    /**
     * Deletes a team and its associated matches.
     *
     * @param teamName  The name of the team to delete
     */
    public static void deleteTeam(String teamName) {
        xmlFileParsed.remove(teamName);
    }

    /**
     * Deletes a match.
     *
     * @param teamName  The name of the team which holds the match
     * @param matchName The name of the match to delete
     */
    public static void deleteMatch(String teamName, String matchName) {
        xmlFileParsed.get(teamName).remove(matchName);
    }

    /**
     * Gets the names of all teams.
     *
     * @return  An array of all team names
     */
    public static String[] getTeamNames() {
        return xmlFileParsed.keySet().toArray(new String[xmlFileParsed.size()]);
    }

    /**
     * Gets the names of all matches for a team.
     *
     * @param teamName  The name of the team
     * @return  An array of all match names for a team
     */
    public static String[] getMatchNames(String teamName) {
        Match[] matches = getMatches(teamName);
        List<String> names = new ArrayList<>();
        for (Match match : matches) {
            names.add(match.getMatchName());
        }
        return names.toArray(new String[matches.length]);
    }

    /**
     * Gets all matches for a team.
     *
     * @param teamName  The name of the team
     * @return  An array of all matches for a team
     */
    public static Match[] getMatches(String teamName) {
        Map<String, Match> matches = new HashMap<>(0);
        matches.putAll(xmlFileParsed.get(teamName));
        return matches.values().toArray(new Match[matches.size()]);
    }

    public static void setRedAlliance1(String teamName, String matchName, String redAlliance1) {
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance1(redAlliance1);
    }

    public static void setRedAlliance2(String teamName, String matchName, String redAlliance2) {
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance2(redAlliance2);
    }

    public static void setRedAlliance3(String teamName, String matchName, String redAlliance3) {
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance3(redAlliance3);
    }

    public static void setBlueAlliance1(String teamName, String matchName, String blueAlliance1) {
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance1(blueAlliance1);
    }

    public static void setBlueAlliance2(String teamName, String matchName, String blueAlliance2) {
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance2(blueAlliance2);
    }

    public static void setBlueAlliance3(String teamName, String matchName, String blueAlliance3) {
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance3(blueAlliance3);
    }

    public static void setRedScore(String teamName, String matchName, String redScore) {
        xmlFileParsed.get(teamName).get(matchName).setRedScore(redScore);
    }

    public static void setBlueScore(String teamName, String matchName, String blueScore) {
        xmlFileParsed.get(teamName).get(matchName).setBlueScore(blueScore);
    }

    public static String getRedAlliance1(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getRedAlliance1();
    }

    public static String getRedAlliance2(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getRedAlliance2();
    }

    public static String getRedAlliance3(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getRedAlliance3();
    }

    public static String getBlueAlliance1(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getBlueAlliance1();
    }

    public static String getBlueAlliance2(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getBlueAlliance2();
    }

    public static String getBlueAlliance3(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getBlueAlliance3();
    }

    public static String getRedScore(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getRedScore();
    }

    public static String getBlueScore(String teamName, String matchName) {
        return xmlFileParsed.get(teamName).get(matchName).getBlueScore();
    }

    public static Map<String, Map<String, Match>> getXmlFileParsed() {
        return xmlFileParsed;
    }

    public static Map<String, String> getWinPercentage() {
        return winPercentage;
    }
}
