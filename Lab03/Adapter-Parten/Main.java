import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        XmlService xmlService = new XmlService();
        XmlToJsonAdapter adapter = new XmlToJsonAdapter(xmlService);

        System.out.println("Choose mode:");
        System.out.println("1 - JSON to XML");
        System.out.println("2 - XML to JSON");

        int choice = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        if (choice == 1) {
            System.out.print("Enter JSON: ");
            String json = scanner.nextLine();
            adapter.sendJson(json);
        } else if (choice == 2) {
            String jsonResult = adapter.getJsonFromXml();
            System.out.println("Converted JSON:");
            System.out.println(jsonResult);
        } else {
            System.out.println("Invalid choice");
        }

        scanner.close();
    }
}
