import java.util.Scanner;

public class InventoryManager {

  private static int AskForUserInput() {
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
    Scanner scanner = new Scanner(System.in);
    int option = -1;
    if (scanner.hasNextInt())
      option = scanner.nextInt();
    else {
      System.out.println("Invalid input. Please enter a number.");
    }
    scanner.close();
    return option;
  }
  public static void main(String[] args) {
    System.out.println("Welcome to the inventory management system.");
    boolean isFinished = false;
    Inventory inventory = new Inventory();
    while (!isFinished) {
      int option = AskForUserInput();
      switch (option) {
      case 0:
        System.out.println("Exiting the program.");
        isFinished = true;
        break;
      case 1:
        inventory.addDevice();
        break;
      default:
        break;
      }
    }
  }
}