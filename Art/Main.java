package Art;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        File xmlFile = new File("paintings.xml");

        List<PaintingCatalog> catalog = PaintingCatalog.getPaintingFromXml(xmlFile);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. List paintings");
            System.out.println("2. Add a new painting");
            System.out.println("3. Delete a painting");
            System.out.println("4. Update a painting");
            System.out.println("5. Exit");
            System.out.println("Choose an option: ");

            int choice = 0; // Helyőrző érték
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Az új sor karakter beolvasásának fogyasztása
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Érvénytelen bemenet fogyasztása
            }


            switch (choice) {
                case 1:
                    PaintingCatalog.listPaintings(catalog); // Festmények listázása
                    break;
                case 2:
                    PaintingCatalog.addPainting(catalog); // Új festmény hozzáadása
                    break;
                case 3:
                    PaintingCatalog.deletePainting(catalog); // Festmény törlése
                    break;
                case 4:
                    PaintingCatalog.updatePainting(catalog); // Festmény módosítása
                    break;
                case 5:
                    exit = true; // Kilépés a programból
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}
