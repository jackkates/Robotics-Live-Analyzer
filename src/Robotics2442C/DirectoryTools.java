package Robotics2442C;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Convenience methods for dealing with directories.
 *
 * @author Octogonapus
 */

public class DirectoryTools {

    /**
     * Searches a directory for a file.
     *
     * @param directory The directory to search
     * @param fileName  The file to search for
     * @return  Whether or not the file is in the directory
     */
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

    /**
     * Gets all directories in a directory
     *
     * @param directory The directory to search (Type: File)
     * @return  An array of files of directories found
     */
    public static File[] printDirectory(File directory) {
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    /**
     * Gets all directories in a directory
     *
     * @param directoryPath The directory to search (Type: String)
     * @return  An array of files of directories found
     */
    public static File[] printDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        ArrayList<File> files = new ArrayList<File>(0);
        Collections.addAll(files, directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }));
        File[] filesCaster = new File[1];
        return files.toArray(filesCaster);
    }

    /**
     * Gets all files in a directory
     *
     * @param directory The directory to search (Type: File)
     * @return  An array of files of files found
     */
    public static File[] printDirectoryFiles(File directory) {
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    /**
     * Gets all files in a directory
     *
     * @param directoryPath The directory to search (Type: String))
     * @return  An array of files of files found
     */
    public static File[] printDirectoryFiles(String directoryPath) {
        File directory = new File(directoryPath);
        return directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile();
            }
        });
    }

    /**
     * Makes a directory.
     *
     * @param rawPath   The file path of the directory the directory will be make in
     * @param name  The name of the directory to be made
     * @param addName   Whether or not to add the given name
     */
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
