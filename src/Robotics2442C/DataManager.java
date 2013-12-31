package Robotics2442C;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Octogonapus
 */

public class DataManager {
    private static Map<String, Map<String, Match>> xmlFileParsed = new HashMap<String, Map<String, Match>>(0);

    public static void setupMainFolder() throws IOException {
        //Path of main directory for the application's data to be held in
        String startingPath = System.getProperty("user.home") + Controller.fileSeparator + "Robotics Live Analyzer";
        //Create said main directory
        DirectoryTools.makeDirectory(startingPath, "", false);
        //Create data directory
        DirectoryTools.makeDirectory(startingPath, Controller.fileSeparator + "Data", true);
        //Create CFE file
        File startingFile = new File(startingPath + Controller.fileSeparator + "openMe.txt");
        if (startingFile.createNewFile()) {
            System.out.println("File " + startingFile.toString() + " created.");
        } else {
            System.out.println("File " + startingFile.toString() + " not created.");
        }
        Controller.setMainDirectory();
        bindMainFile();
    }

    public static void bindMainFile() {
        XMLHandler.write("robotics.xml");
        xmlFileParsed.putAll(XMLHandler.readIn(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator") + "robotics.xml")));
    }

    public static void testBoundFile() {
        for (Map.Entry<String, Map<String, Match>> entry : xmlFileParsed.entrySet()) {
            System.out.println("Team : " + entry.getKey());
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
            }
        }
    }
}
