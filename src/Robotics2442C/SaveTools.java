package Robotics2442C;

import java.io.*;

/**
 * @author Octogonapus
 */

public class SaveTools {
    private String saveData;

    public static void save(String team, Competition competition) {
        String path = System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator") +
                "Data" + System.getProperty("file.separator") + team + System.getProperty("file.separator") + competition;
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + System.getProperty("file.separator") + "openMe.txt")));
            writer.write(path + System.getProperty("file.separator") + "Data");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception ignored) { }
        }
    }
}
