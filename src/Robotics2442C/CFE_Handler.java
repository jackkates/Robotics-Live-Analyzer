package Robotics2442C;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Octogonapus
 */

public class CFE_Handler {
    private ObservableList<String> teams = FXCollections.observableArrayList();

    public ObservableList<String> parseFile(File team) {
        Scanner sc;
        try {
            sc = new Scanner(team);
            sc.useDelimiter(",");
            int i = 0;
            while (sc.hasNext()) {
                teams.add(sc.next());
                i++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Controller.setMainDirectory();
        return teams;
    }
}
