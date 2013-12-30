package Robotics2442C;

import java.io.*;
import java.util.Scanner;

/**
 * @author Octogonapus
 */

public class LoadForTeam {
    private File teamFolder;
    private File[] teamComps;
    private static Scanner sc;
    private static File[] teamMatches;
    private Competition[] competitions;

    public LoadForTeam(String teamToLoad) {
        int i = 0;
        File teamFolder = new File(Controller.mainDirectory.toString().concat(System.getProperty("file.separator") + teamToLoad));
        teamComps = teamFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        if (teamComps != null && teamComps.length != 0) {
            competitions = new Competition[teamComps.length];
            for (File teamComp : teamComps) {
                teamMatches = teamComp.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isFile();
                    }
                });
                for (File teamMatch : teamMatches) {
                    try {
                        sc = new Scanner(teamMatch);
                        sc.useDelimiter(",");
                        competitions[i] = new Competition();
                        competitions[i].setCompetitionName(sc.next());
                        competitions[i].setMatchName(sc.next());
                        competitions[i].setRedAlliance1(sc.next());
                        competitions[i].setRedAlliance2(sc.next());
                        competitions[i].setRedAlliance3(sc.next());
                        competitions[i].setBlueAlliance1(sc.next());
                        competitions[i].setBlueAlliance2(sc.next());
                        competitions[i].setBlueAlliance3(sc.next());
                        competitions[i].setRedScore(sc.next());
                        competitions[i].setBlueScore(sc.next());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                i++;
            }
        }
    }

    public Competition[] getCompetitions() {
        return competitions;
    }
}
