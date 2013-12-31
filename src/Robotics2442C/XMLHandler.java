package Robotics2442C;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Octogonapus
 */

public class XMLHandler {

    public static void readOut(File xmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            NodeList nList;
            Node nNode;
            //Not needed, recommended
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            nList = doc.getElementsByTagName("team");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                nNode = nList.item(temp);
                System.out.println("\nCurrent Element : " + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    System.out.println("Team id : " + eElement.getAttribute("id"));
                    System.out.println("Match Name : " + eElement.getElementsByTagName("match").item(0).getTextContent());
                    System.out.println("Red Alliance One Name : " + eElement.getElementsByTagName("redAlliance1").item(0).getTextContent());
                    System.out.println("Red Alliance Two Name : " + eElement.getElementsByTagName("redAlliance2").item(0).getTextContent());
                    System.out.println("Red Alliance Three Name : " + eElement.getElementsByTagName("redAlliance3").item(0).getTextContent());
                    System.out.println("Blue Alliance One Name : " + eElement.getElementsByTagName("blueAlliance1").item(0).getTextContent());
                    System.out.println("Blue Alliance Two Name : " + eElement.getElementsByTagName("blueAlliance2").item(0).getTextContent());
                    System.out.println("Blue Alliance Three Name : " + eElement.getElementsByTagName("blueAlliance3").item(0).getTextContent());
                    System.out.println("Red Score : " + eElement.getElementsByTagName("redScore").item(0).getTextContent());
                    System.out.println("Blue Score : " + eElement.getElementsByTagName("blueScore").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Map<String, Match>> readIn(File xmlFile) {
        Map<String, Map<String, Match>> xmlFileParsed = new HashMap<String, Map<String, Match>>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            NodeList nList;
            Node nNode;
            //Not needed, recommended
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName("team");
            for (int i = 0; i < nList.getLength(); i++) {
                nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    xmlFileParsed.put(eElement.getAttribute("id"), new HashMap<String, Match>());
                    xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("match").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setMatchName(eElement.getElementsByTagName("match").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("redAlliance1").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setRedAlliance1(eElement.getElementsByTagName("redAlliance1").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("redAlliance2").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setRedAlliance2(eElement.getElementsByTagName("redAlliance2").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("redAlliance3").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setRedAlliance3(eElement.getElementsByTagName("redAlliance3").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("blueAlliance1").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setBlueAlliance1(eElement.getElementsByTagName("blueAlliance1").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("blueAlliance2").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setBlueAlliance2(eElement.getElementsByTagName("blueAlliance2").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("blueAlliance3").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setBlueAlliance3(eElement.getElementsByTagName("blueAlliance3").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("redScore").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setRedScore(eElement.getElementsByTagName("redScore").item(0).getTextContent());
                    //xmlFileParsed.get(eElement.getAttribute("id")).put(eElement.getElementsByTagName("blueScore").item(0).getTextContent(), new Match());
                    xmlFileParsed.get(eElement.getAttribute("id")).get(eElement.getElementsByTagName("match").item(0).getTextContent()).setBlueScore(eElement.getElementsByTagName("blueScore").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlFileParsed;
    }

    public static void write(String fileName) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("tossUp");
            doc.appendChild(rootElement);

            //team element
            Element team = doc.createElement("team");
            rootElement.appendChild(team);

            //set attribute to team element
            team.setAttribute("id", "2442A");

            //match element
            Element match = doc.createElement("match");
            match.appendChild(doc.createTextNode("m1"));
            team.appendChild(match);

            //redAlliance1 element
            Element redAlliance1 = doc.createElement("redAlliance1");
            redAlliance1.appendChild(doc.createTextNode("2442A"));
            team.appendChild(redAlliance1);

            //redAlliance2 element
            Element redAlliance2 = doc.createElement("redAlliance2");
            redAlliance2.appendChild(doc.createTextNode("2442B"));
            team.appendChild(redAlliance2);

            //redAlliance3 element
            Element redAlliance3 = doc.createElement("redAlliance3");
            redAlliance3.appendChild(doc.createTextNode(""));
            team.appendChild(redAlliance3);

            //blueAlliance1 element
            Element blueAlliance1 = doc.createElement("blueAlliance1");
            blueAlliance1.appendChild(doc.createTextNode("2442C"));
            team.appendChild(blueAlliance1);

            //blueAlliance2 element
            Element blueAlliance2 = doc.createElement("blueAlliance2");
            blueAlliance2.appendChild(doc.createTextNode("2442D"));
            team.appendChild(blueAlliance2);

            //blueAlliance3 element
            Element blueAlliance3 = doc.createElement("blueAlliance3");
            blueAlliance3.appendChild(doc.createTextNode(""));
            team.appendChild(blueAlliance3);

            //redScore element
            Element redScore = doc.createElement("redScore");
            redScore.appendChild(doc.createTextNode("10"));
            team.appendChild(redScore);

            //blueScore element
            Element blueScore = doc.createElement("blueScore");
            blueScore.appendChild(doc.createTextNode("20"));
            team.appendChild(blueScore);

            //write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Robotics Live Analyzer" + System.getProperty("file.separator") + fileName));

                //output to console for testing (comment out the above one)
                //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
