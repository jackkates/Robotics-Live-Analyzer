package Robotics2442C;

import nu.xom.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * Handles all XML tasks.
 *
 * @author Octogonapus
 */

public class XOMHandler {

    /**
     * Writes or overwrites a file to the HDD.
     *
     * @param xmlFileParsed The data to write
     * @param fileName  The name of a file to write to, or to overwrite
     */
    public static void save(Map<String, Map<String, Match>> xmlFileParsed, String fileName) throws IOException {
        //root element
        Element root = new Element("tossUp");

        for (Map.Entry<String, Map<String, Match>> entry : xmlFileParsed.entrySet()) {
            Map<String, Match> fileTemp = new HashMap<>(0);
            fileTemp.putAll(entry.getValue());

            //team element
            Element team = new Element("team");
            team.addAttribute(new Attribute("id", entry.getKey()));
            root.appendChild(team);

            for (Map.Entry<String, Match> entryDeep : fileTemp.entrySet()) {
                //match element
                Element match = new Element("match");
                match.addAttribute(new Attribute("id", entryDeep.getKey()));
                team.appendChild(match);

                //redAlliance1 element
                Element redAlliance1 = new Element("redAlliance1");
                redAlliance1.appendChild(entryDeep.getValue().getRedAlliance1());
                match.appendChild(redAlliance1);

                //redAlliance2 element
                Element redAlliance2 = new Element("redAlliance2");
                redAlliance2.appendChild(entryDeep.getValue().getRedAlliance2());
                match.appendChild(redAlliance2);

                //redAlliance3 element
                Element redAlliance3 = new Element("redAlliance3");
                redAlliance3.appendChild(entryDeep.getValue().getRedAlliance3());
                match.appendChild(redAlliance3);

                //blueAlliance1 element
                Element blueAlliance1 = new Element("blueAlliance1");
                blueAlliance1.appendChild(entryDeep.getValue().getBlueAlliance1());
                match.appendChild(blueAlliance1);

                //blueAlliance2 element
                Element blueAlliance2 = new Element("blueAlliance2");
                blueAlliance2.appendChild(entryDeep.getValue().getBlueAlliance2());
                match.appendChild(blueAlliance2);

                //blueAlliance3 element
                Element blueAlliance3 = new Element("blueAlliance3");
                blueAlliance3.appendChild(entryDeep.getValue().getBlueAlliance3());
                match.appendChild(blueAlliance3);

                //redScore element
                Element redScore = new Element("redScore");
                redScore.appendChild(entryDeep.getValue().getRedScore());
                match.appendChild(redScore);

                //blueScore element
                Element blueScore = new Element("blueScore");
                blueScore.appendChild(entryDeep.getValue().getBlueScore());
                match.appendChild(blueScore);
            }
        }

        //finalize and print document
        Document doc = new Document(root);
        try {
            if (!Controller.firstSave && fileName != null) {
                Serializer serializer = new Serializer(new PrintStream(System.getProperty("user.home") + System.getProperty("file.separator") + "RoboDogs Live Analyzer" + System.getProperty("file.separator") + fileName + ".xml"), "ISO-8859-1");
                serializer.setIndent(4);
                serializer.write(doc);
                System.out.println("Saved");
            }
        } catch (UnsupportedEncodingException e) {
            LogError.log(Level.INFO, e.toString() + ", XOMHandler.save {firstSave = " + Controller.firstSave + ", fileName = " + fileName + "}");
        }
    }

    /**
     * Loads a file from the HDD into memory.
     *
     * @param xmlFile   The path of the file to load
     * @return  A Map containing the loaded file, serving as the main data structure
     */
    public static Map<String, Map<String, Match>> load(File xmlFile) {
        Builder builder = new Builder();
        Map<String, Map<String, Match>> xmlFileParsed = new HashMap<>(0);
        try {
            Document doc = builder.build(xmlFile);
            Element root = doc.getRootElement();
            for (int i = 0; i < root.getChildElements("team").size(); i++) {
                Element team = root.getChildElements("team").get(i);
                String teamId = team.getAttributeValue("id");
                xmlFileParsed.put(teamId, new HashMap<String, Match>(0));
                for (int j = 0; j < team.getChildElements("match").size(); j++) {
                    Element match = root.getChildElements("team").get(i).getChildElements("match").get(j);
                    String matchId = match.getAttributeValue("id");
                    String redAlliance1 = match.getFirstChildElement("redAlliance1").getValue();
                    String redAlliance2 = match.getFirstChildElement("redAlliance2").getValue();
                    String redAlliance3 = match.getFirstChildElement("redAlliance3").getValue();
                    String blueAlliance1 = match.getFirstChildElement("blueAlliance1").getValue();
                    String blueAlliance2 = match.getFirstChildElement("blueAlliance2").getValue();
                    String blueAlliance3 = match.getFirstChildElement("blueAlliance3").getValue();
                    String redScore = match.getFirstChildElement("redScore").getValue();
                    String blueScore = match.getFirstChildElement("blueScore").getValue();
                    xmlFileParsed.get(teamId).put(matchId, new Match(matchId, redAlliance1, redAlliance2, redAlliance3, blueAlliance1, blueAlliance2, blueAlliance3, redScore, blueScore));
                }
            }
        } catch (IOException e) {
           LogError.log(Level.WARNING, e.toString() + ", XOMHandler.load {xmlFile = " + xmlFile.toString() + "}");
        } catch (ParsingException e) {
            LogError.log(Level.SEVERE, e.toString() + ", XOMHandler.load {xmlFile = " + xmlFile.toString() + "}");
        }
        return xmlFileParsed;
    }
}
