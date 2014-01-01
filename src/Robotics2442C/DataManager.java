package Robotics2442C;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Octogonapus
 */

public class DataManager {
    private static Map<String, Map<String, Match>> xmlFileParsed = new HashMap<String, Map<String, Match>>(0);
    public static String fileName = "";

    public static void setupMainFolder() throws IOException {
        //Path of main directory for the application's data to be held in
        String startingPath = System.getProperty("user.home") + Controller.fileSeparator + "RoboDogs Live Analyzer";
        //Create said main directory
        DirectoryTools.makeDirectory(startingPath, "", false);
        Controller.setMainDirectory();
    }

    public static void testBoundFile() {
        for (Map.Entry<String, Map<String, Match>> entry : xmlFileParsed.entrySet()) {
            System.out.println("\nTeam : " + entry.getKey());
            Map<String, Match> fileTemp = new HashMap<String, Match>(0);
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
                /*
                System.out.println("\nTest Calls\n----------------------------");
                System.out.println(xmlFileParsed.get("2442A").get("m1").getMatchName());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getRedAlliance1());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getRedAlliance2());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getRedAlliance3());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getBlueAlliance1());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getBlueAlliance2());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getBlueAlliance3());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getRedScore());
                System.out.println(xmlFileParsed.get("2442A").get("m1").getBlueScore());
                */
            }
        }
    }

    public static void openApp(File file) {
        xmlFileParsed.putAll(XOMHandler.load(file));
        DataManager.fileName = file.getName().replace(".xml", "");
    }

    public static void saveApp(String fileName) {
        Controller.firstSave = false;
        DataManager.fileName = fileName;
        XOMHandler.save(xmlFileParsed, fileName);
    }

    public static void saveApp() {
        XOMHandler.save(xmlFileParsed, DataManager.fileName);
    }

    public static void fillMatch(String teamName, String matchName) {
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance1(" ");
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance2(" ");
        xmlFileParsed.get(teamName).get(matchName).setRedAlliance3(" ");
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance1(" ");
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance2(" ");
        xmlFileParsed.get(teamName).get(matchName).setBlueAlliance3(" ");
        xmlFileParsed.get(teamName).get(matchName).setRedScore(" ");
        xmlFileParsed.get(teamName).get(matchName).setBlueScore(" ");
    }

    public static void newTeam(String teamName) {
        xmlFileParsed.put(teamName, new HashMap<String, Match>(0));
    }

    public static void newMatch(String teamName, String matchName) {
        xmlFileParsed.get(teamName).put(matchName, new Match());
        xmlFileParsed.get(teamName).get(matchName).setMatchName(matchName);
    }

    public static void renameMatch(String teamName, String matchName, String matchNameNew) {
        Match match = xmlFileParsed.get(teamName).get(matchName);
        xmlFileParsed.get(teamName).put(matchNameNew, match);
    }

    public static void deleteTeam(String teamName) {
        xmlFileParsed.remove(teamName);
    }

    public static void deleteMatch(String teamName, String matchName) {
        xmlFileParsed.get(teamName).remove(matchName);
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

    public static String[] getTeamNames() {
        String[] teamNamesArray = new String[xmlFileParsed.size()];
        teamNamesArray = xmlFileParsed.keySet().toArray(teamNamesArray);
        return teamNamesArray;
    }

    public static String[] getMatchNames(String teamName) {
        String[] matchNamesArray;
        Map<String, Match> matchNames = new HashMap<String, Match>(0);
        matchNames.putAll(xmlFileParsed.get(teamName));
        matchNamesArray = new String[matchNames.size()];
        Iterator<Map.Entry<String, Match>> iterator = matchNames.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, Match> entryDeep = iterator.next();
            matchNamesArray[i] = entryDeep.getKey();
            i++;
        }
        return matchNamesArray;
    }

    public static Match[] getMatches(String teamName) {
        Map<String, Match> matches = new HashMap<String, Match>(0);
        matches.putAll(xmlFileParsed.get(teamName));
        Match[] matchArray = new Match[matches.size()];
        matchArray = matches.values().toArray(matchArray);
        return matchArray;
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
}
