package Robotics2442C;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Octogonapus
 */

public class CBE_Handler {
    private ArrayList<File> competitions = new ArrayList<File>(0);
    private static final ObservableList<Competition> matches = FXCollections.observableArrayList();

    public ObservableList<Competition> loadMatches(String teamName) {
        Scanner sc;
        String teamPath = Controller.mainDirectory + Controller.fileSeparator + teamName;
        Collections.addAll(competitions, DirectoryTools.printDirectoryFiles(teamPath));
        int i = 0;
        for (File competition : competitions) {
            sc = new Scanner(competition.toString());
            sc.useDelimiter(",");
            String[] tokens = new String[10];
            int j = 0;
            while (sc.hasNext()) {
                tokens[j] = sc.next();
                j++;
            }
            matches.add(new Competition());
            matches.get(i).setCompetitionName(tokens[0]);
            matches.get(i).setMatchName(tokens[1]);
            matches.get(i).setRedAlliance1(tokens[2]);
            matches.get(i).setRedAlliance2(tokens[3]);
            matches.get(i).setRedAlliance3(tokens[4]);
            matches.get(i).setBlueAlliance1(tokens[5]);
            matches.get(i).setBlueAlliance2(tokens[6]);
            matches.get(i).setBlueAlliance3(tokens[7]);
            matches.get(i).setRedScore(tokens[8]);
            matches.get(i).setBlueScore(tokens[9]);
            i++;
        }
        return matches;
    }

    public void writeMatch() {
        //
    }
}
