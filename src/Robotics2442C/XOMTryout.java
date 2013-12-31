package Robotics2442C;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class XOMTryout {

    public static void save(Map<String, Map<String, Match>> xmlFileParsed, String fileName) {
        //root element
        Element root = new Element("tossUp");

        for (Map.Entry<String, Map<String, Match>> entry : xmlFileParsed.entrySet()) {
            Map<String, Match> fileTemp = new HashMap<String, Match>(0);
            fileTemp.putAll(entry.getValue());

            //team element
            Element team = new Element("team");
            team.addAttribute(new Attribute("id", entry.getKey()));
            root.appendChild(team);

            for (Map.Entry<String, Match> entryDeep : fileTemp.entrySet()) {
                //match element
                Element match = new Element("match");
                match.appendChild(entryDeep.getKey());
                team.appendChild(match);

                //redAlliance1 element
                Element redAlliance1 = new Element("redAlliance1");
                redAlliance1.appendChild(entryDeep.getValue().getRedAlliance1());
                team.appendChild(redAlliance1);

                //redAlliance2 element
                Element redAlliance2 = new Element("redAlliance2");
                redAlliance2.appendChild(entryDeep.getValue().getRedAlliance2());
                team.appendChild(redAlliance2);

                //redAlliance3 element
                Element redAlliance3 = new Element("redAlliance3");
                redAlliance3.appendChild(entryDeep.getValue().getRedAlliance3());
                team.appendChild(redAlliance3);

                //blueAlliance1 element
                Element blueAlliance1 = new Element("blueAlliance1");
                blueAlliance1.appendChild(entryDeep.getValue().getBlueAlliance1());
                team.appendChild(blueAlliance1);

                //blueAlliance2 element
                Element blueAlliance2 = new Element("blueAlliance2");
                blueAlliance2.appendChild(entryDeep.getValue().getBlueAlliance2());
                team.appendChild(blueAlliance2);

                //blueAlliance3 element
                Element blueAlliance3 = new Element("blueAlliance3");
                blueAlliance3.appendChild(entryDeep.getValue().getBlueAlliance3());
                team.appendChild(blueAlliance3);

                //redScore element
                Element redScore = new Element("redScore");
                redScore.appendChild(entryDeep.getValue().getRedScore());
                team.appendChild(redScore);

                //blueScore element
                Element blueScore = new Element("blueScore");
                blueScore.appendChild(entryDeep.getValue().getBlueScore());
                team.appendChild(blueScore);
            }
        }

        //finalize and print document
        Document doc = new Document(root);
        try {
            Serializer serializer = new Serializer(new PrintStream(System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator") + fileName), "ISO-8859-1");
            serializer.setIndent(4);
            serializer.write(doc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
