package Root.gameData;

import Root.CustomContol.ScoreBoard;
import Root.Settings.Sound;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class XMLService {
    private DocumentBuilderFactory documentBuilderFactory ;
    private DocumentBuilder documentBuilder;
    private Document document;
    private Element root;
    private String filePath;
    private File DataFile;
    private Path pathToFile;

    public XMLService(){


        filePath="C:/Users/" +
                System.getProperty("user.name") +
                "/AppData/Local/TimeAttack/HighscoreData.xml";

        pathToFile = Paths.get(filePath);
        DataFile= new File(filePath);

        try{
            if (!DataFile.exists()){
                Files.createDirectories(pathToFile.getParent());
                Files.createFile(pathToFile);
                String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<GameData>\n" +
                        "</GameData>";
                writeFile(filePath,data);

            }

            documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(filePath);
            root = document.getDocumentElement();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createSoundInfoFile(){
        filePath="C:/Users/" +
                System.getProperty("user.name") +
                "/AppData/Local/TimeAttack/SoundData.xml";

        pathToFile = Paths.get(filePath);
        DataFile= new File(filePath);
        try {
            if (!DataFile.exists()){
                Files.createDirectories(pathToFile.getParent());
                Files.createFile(pathToFile);
                String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                        "<GameData>\n" +
                        "<sound><volume>5.0</volume><sfx>true</sfx><bgm>true</bgm></sound>"+
                        "</GameData>";
                writeFile(filePath,data);

            }
            document = documentBuilder.parse(filePath);
            root = document.getDocumentElement();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSoundInfo(double maxVolume, boolean sfxInfo, boolean bgmInfo){
        try {
            Element sound = document.createElement("sound");

            Element volume = document.createElement("volume");
            volume.appendChild(document.createTextNode(String.valueOf(maxVolume)));
            sound.appendChild(volume);

            Element sfx = document.createElement("sfx");
            sfx.appendChild(document.createTextNode(String.valueOf(sfxInfo)));
            sound.appendChild(sfx);

            Element bgm = document.createElement("bgm");
            bgm.appendChild(document.createTextNode(String.valueOf(bgmInfo)));
            sound.appendChild(bgm);

            root.replaceChild(sound, search("sound"));


            saveChange(document);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveChange(Document documents) throws TransformerException {
        DOMSource source = new DOMSource(documents);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(filePath);
        transformer.transform(source, result);
    }

    private Node search(String nodeName) {
        Node node = null;
        try {
            document.getDocumentElement().normalize();
            NodeList nodeList =  document.getElementsByTagName(nodeName);
            node = nodeList.item(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return node;
    }

    public Sound info(){
        Sound sound = null;
        try {
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("sound");
            Node fstNode = nodeList.item(0);

            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) fstNode;

                NodeList volumeNode = element.getElementsByTagName("volume");
                Element volumeLevel = (Element) volumeNode.item(0);
                NodeList volume = volumeLevel.getChildNodes();

                NodeList sfxNodeList = element.getElementsByTagName("sfx");
                Element sfxElement = (Element) sfxNodeList.item(0);
                NodeList sfx = sfxElement.getChildNodes();

                NodeList bgmNodeList = element.getElementsByTagName("bgm");
                Element bgmElement = (Element) bgmNodeList.item(0);
                NodeList bgm = bgmElement.getChildNodes();
                sound = new Sound(Double.parseDouble((volume.item(0)).getNodeValue()), Boolean.parseBoolean((sfx.item(0)).getNodeValue()), Boolean.parseBoolean((bgm.item(0)).getNodeValue()) );
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sound;
    }

    public void updateScoreBoard(String userName, String score, String lvlReached) throws TransformerException {
        ScoreBoard scores = new ScoreBoard(userName,score,lvlReached);
        Element scoreDetail = document.createElement("ScoreDetail");

        Element name = document.createElement("Name");
        name.appendChild(document.createTextNode(scores.getName()));
        scoreDetail.appendChild(name);

        Element score1 = document.createElement("Score");
        score1.appendChild(document.createTextNode(scores.getScore()));
        scoreDetail.appendChild(score1);

        Element levelReached = document.createElement("LevelReached");
        levelReached.appendChild(document.createTextNode(scores.getLvlReached()));
        scoreDetail.appendChild(levelReached);

        root.appendChild(scoreDetail);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(filePath);
        transformer.transform(source, result);
    }

    public ArrayList<ScoreBoard> getScoreList() {
        ArrayList<ScoreBoard> scoreList = new ArrayList<>();

        try {
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("ScoreDetail");

            for (int s = 0; s < nodeList.getLength(); s++) {
                Node fstNode = nodeList.item(s);

                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) fstNode;

                    NodeList nameNodeList = element.getElementsByTagName("Name");
                    Element nameElement = (Element) nameNodeList.item(0);
                    NodeList names = nameElement.getChildNodes();

                    NodeList scoreNodeList = element.getElementsByTagName("Score");
                    Element scoreElement = (Element) scoreNodeList.item(0);
                    NodeList score = scoreElement.getChildNodes();

                    NodeList levelReachedNodeList = element.getElementsByTagName("LevelReached");
                    Element levelReachedElement = (Element) levelReachedNodeList.item(0);
                    NodeList levelReached = levelReachedElement.getChildNodes();

                    scoreList.add(new ScoreBoard((names.item(0)).getNodeValue(), (score.item(0)).getNodeValue(), (levelReached.item(0)).getNodeValue() ));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreList;
    }

    public void writeFile(String filePath, String data){
        PrintWriter writer ;
        try {
            writer = new PrintWriter(filePath);
            writer.print(data);
            writer.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void clearFile(){
        filePath="C:/Users/" +
                System.getProperty("user.name") +
                "/AppData/Local/TimeAttack/HighscoreData.xml";
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<GameData>\n" +
                "</GameData>";
        writeFile(filePath,data);
    }
}
