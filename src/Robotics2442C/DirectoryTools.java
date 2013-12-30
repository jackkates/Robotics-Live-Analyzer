package Robotics2442C;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Octogonapus
 */

public class DirectoryTools {

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

    public static File[] printDirectory(File directory) {
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    public static File[] printDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        ArrayList<File> files = new ArrayList<File>(0);
        Collections.addAll(files, directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }));
        if (files.contains(".DS_Store")) {
            files.remove(".DS_Store");
        }
        File[] filesCaster = new File[1];
        return files.toArray(filesCaster);
    }

    public static File[] printDirectoryFiles(File directory) {
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    public static File[] printDirectoryFiles(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    public static void makeDirectory(String rawPath, String name, boolean addName) {
        File directory;
        String path;
        if (addName) {
            path = rawPath + Controller.fileSeparator + name;
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
