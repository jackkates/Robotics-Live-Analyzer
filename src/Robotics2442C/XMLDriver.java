package Robotics2442C;

import java.io.File;

/**
 * @author Octogonapus
 */

public class XMLDriver {
    public static void main(String[] args) {
        XMLHandler.write("robotics.xml");
        XMLHandler.readOut(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator") + "robotics.xml"));
        //DataManager.bindMainData();
    }
}
