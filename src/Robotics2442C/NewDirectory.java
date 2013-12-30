package Robotics2442C;

import java.io.File;

/**
 * @author Octogonapus
 */

public class NewDirectory {
    private File directory;
    private String rawPath;
    private String path;
    private String name;

    public NewDirectory(String rawPath, String name) {
        this.rawPath = rawPath;
        this.name = name;
    }

    public void makeDirectory(boolean addName) {
        if (addName) {
            path = rawPath.concat(System.getProperty("file.separator") + name);
        } else {
            path = rawPath;
        }
        directory = new File(path);
        try {
            if (directory.mkdir()) {
                System.out.println("Directory " + directory.toString() + " created.");
            } else {
                System.out.println("Directory " + directory.toString() + " not created.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
