package Art;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PaintingCatalog {
    private int id;
    private String title;
    private String artist;
    private String year;

    private static Scanner scanner = new Scanner(System.in);

    public PaintingCatalog(int id, String title, String artist, String year) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return "Painting No" + id + ".\n" +
                "(" + title + ")\n" +
                "(" + artist + ")\n" +
                "(" + year + ")\n";
    }

    // XML fájlból beolvasás
    public static List<PaintingCatalog> getPaintingFromXml(File f) {
        List<PaintingCatalog> paintings = new ArrayList<>();

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(f);
            dom.getDocumentElement().normalize();

            NodeList paintingNodes = dom.getElementsByTagName("painting");

            for (int i = 0; i < paintingNodes.getLength(); i++) {
                Node n = paintingNodes.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;
                    int id = Integer.parseInt(e.getElementsByTagName("id").item(0).getTextContent().trim());
                    String title = e.getElementsByTagName("title").item(0).getTextContent().trim();
                    String artist = e.getElementsByTagName("artist").item(0).getTextContent().trim();
                    String year = e.getElementsByTagName("year").item(0).getTextContent().trim();

                    PaintingCatalog painting = new PaintingCatalog(id, title, artist, year);
                    paintings.add(painting);
                }
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Error: " + e);
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }

        return paintings;
    }

    // Festmények listázása
    static void listPaintings(List<PaintingCatalog> catalog) {
        for (PaintingCatalog painting : catalog) {
            System.out.println(painting);
        }
    }

    // Új festmény hozzáadása
    static void addPainting(List<PaintingCatalog> catalog) {
        System.out.println("Enter the details of the painting:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Year: ");
        String year = scanner.nextLine();

        PaintingCatalog newPainting = new PaintingCatalog(id, title, artist, year);
        catalog.add(newPainting);
        System.out.println("Painting added successfully.");
    }

    // Festmény törlése
    static void deletePainting(List<PaintingCatalog> catalog) {
        System.out.print("Enter the ID of the painting to delete: ");
        int id = scanner.nextInt();
        boolean paintingFound = false;

        for (PaintingCatalog painting : catalog) {
            if (painting.getId() == id) {
                catalog.remove(painting);
                paintingFound = true;
                System.out.println("Painting deleted successfully.");
                break;
            }
        }

        if (!paintingFound) {
            System.out.println("Painting not found with the given ID.");
        }
    }

    // Festmény módosítása
    static void updatePainting(List<PaintingCatalog> catalog) {
        System.out.print("Enter the ID of the painting to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean paintingFound = false;

        for (PaintingCatalog painting : catalog) {
            if (painting.getId() == id) {
                System.out.println("Enter the new details of the painting:");

                System.out.print("Title: ");
                String title = scanner.nextLine();
                painting.setTitle(title);

                System.out.print("Artist: ");
                String artist = scanner.nextLine();
                painting.setArtist(artist);

                System.out.print("Year: ");
                String year = scanner.nextLine();
                painting.setYear(year);

                paintingFound = true;
                System.out.println("Painting updated successfully.");
                break;
            }
        }

        if (!paintingFound) {
            System.out.println("Painting not found with the given ID.");
        }
    }
}
