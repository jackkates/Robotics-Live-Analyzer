package Robotics2442C;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;

/**
 * @author Octogonapus
 */

public class LoadSave {
    private static File folder;
    private static Scanner sc;
    private String[] folders;

    public LoadSave(File folderPath) {
        try {
            sc = new Scanner(folderPath);
            folder = new File(sc.nextLine());
            Controller.mainDirectory = folder;
            folders = folder.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir, name).isDirectory();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    public String[] getFolders() {

        return folders;
    }
}
