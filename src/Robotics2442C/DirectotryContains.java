package Robotics2442C;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Octogonapus
 */

public class DirectotryContains {

    public static boolean searchDirectory(File directory, String fileName) {
        ArrayList<File> files = new ArrayList<File>(0);
        Collections.addAll(files, directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }));
        return files.contains(fileName);
    }
}
