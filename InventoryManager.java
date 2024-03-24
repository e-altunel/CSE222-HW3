import java.util.Scanner;

public class InventoryManager {

  public static void main(String[] args) {
    System.out.println("Welcome to the inventory management system.");
    boolean isFinished = false;
    Inventory inventory = new Inventory();
    Scanner scanner = new Scanner(System.in);
    while (!isFinished) {
      PrintMenu();
      int option = scanner.nextInt();
      if (scanner.hasNextLine())
        scanner.nextLine();
      switch (option) {
      case 0:
        System.out.println("Exiting the program.");
        isFinished = true;
        break;
      case 1:
        inventory.addDevice(scanner);
        break;
      case 3:
        inventory.updateDevice(scanner);
        break;
      case 4:
        inventory.listDevices();
        waitForEnter(scanner);
        break;
      case 5:
        inventory.findCheapestDevice();
        waitForEnter(scanner);
        break;
      case 6:
        inventory.sortByPrice();
        break;
      case 7:
        inventory.calculateTotalValue();
        waitForEnter(scanner);
        break;
      case 8:
        inventory.restockDevice(scanner);
        break;
      case 9:
        inventory.exportInventory();
        waitForEnter(scanner);
        break;
      default:
        System.out.println("Invalid option. Please try again.");
        break;
      }
    }
    scanner.close();
  }

  private static void PrintMenu() {
    System.out.println("Please select an option:");
    System.out.println("1. Add a device to the inventory.");
    System.out.println("2. Remove a device from the inventory.");
    System.out.println("3. Update a device in the inventory.");
    System.out.println("4. List all devices in the inventory.");
    System.out.println("5. Find the cheapest device in the inventory.");
    System.out.println("6. Sort devices by price.");
    System.out.println("7. Calculate the total value of the inventory.");
    System.out.println("8. Restock a device in the inventory.");
    System.out.println("9. Export inventory to a file.");
    System.out.println("0. Exit the program.");
  }

  public static void printError(String message) {
    System.out.println("\033[1;31m[ERROR]\033[0m " + message);
  }

  public static void printSuccess(String message) {
    System.out.println("\033[1;32m[SUCCESS]\033[0m " + message);
  }

  public static void waitForEnter(Scanner scanner) {
    System.out.print("Enter any key to continue...");
    scanner.nextLine();
    System.out.println("\033[F\033[K");
  }
}