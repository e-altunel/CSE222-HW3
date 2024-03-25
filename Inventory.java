import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Inventory.java
 * This class represents an inventory of devices.
 */
public class Inventory {
  private LinkedList<ArrayList<Device>> devices;

  /**
   * Enumeration of device categories.
   */
  public enum Category {
    /** Computer category. */
    COMPUTER,
    /** Phone category. */
    PHONE,
    /** Tablet category. */
    TABLET,
    /** Smartwatch category. */
    SMARTWATCH,
    /** Accessory category. */
    ACCESSORY
  }

  /**
   * Constructs a new Inventory object.
   */
  public Inventory() {
    devices = new LinkedList<>();
    devices.add(new ArrayList<>()); // COMPUTER
    devices.add(new ArrayList<>()); // PHONE
    devices.add(new ArrayList<>()); // TABLET
    devices.add(new ArrayList<>()); // SMARTWATCH
    devices.add(new ArrayList<>()); // ACCESSORY
  }

  /**
   * Adds a device to the inventory using the user input.
   *
   * @param scanner the scanner to get input
   */
  public void addDevice(Scanner scanner) {
    String type;
    String name;
    Double price;
    Integer quantity;
    try {
      System.out.print("Please enter the type of device: ");
      type = scanner.nextLine().toUpperCase();
      System.out.print("Please enter the name of the device: ");
      name = scanner.nextLine();
      System.out.print("Please enter the price of the device: ");
      price = scanner.nextDouble();
      if (scanner.hasNextLine())
        scanner.nextLine();
      System.out.print("Please enter the quantity of the device: ");
      quantity = scanner.nextInt();
      if (scanner.hasNextLine())
        scanner.nextLine();
    } catch (Exception e) {
      InventoryManager.printError("Invalid input");
      InventoryManager.waitForEnter(scanner);
      return;
    }

    AbstractDevice device = null;
    switch (type) {
    case "COMPUTER":
      device = new Computer(name, price, quantity);
      break;
    case "PHONE":
      device = new Phone(name, price, quantity);
      break;
    case "TABLET":
      device = new Tablet(name, price, quantity);
      break;
    case "SMARTWATCH":
      device = new Smartwatch(name, price, quantity);
      break;
    case "ACCESSORY":
      device = new Accessory(name, price, quantity);
      break;
    default:
      InventoryManager.printError("Invalid device type");
      System.out.println("Please enter one of the following types:");
      System.out.println("COMPUTER, PHONE, TABLET, SMARTWATCH, ACCESSORY");
      InventoryManager.waitForEnter(scanner);
      return;
    }
    addDevice(device);
    InventoryManager.printSuccess("Device added successfully");
    InventoryManager.waitForEnter(scanner);
  }

  /**
   * Adds a device to the inventory using the specified device.
   *
   * @param device the device to add
   */
  private void addDevice(Device device) {
    if (device instanceof Computer) {
      devices.get(Category.COMPUTER.ordinal()).add((Computer)device);
    } else if (device instanceof Phone) {
      devices.get(Category.PHONE.ordinal()).add((Phone)device);
    } else if (device instanceof Tablet) {
      devices.get(Category.TABLET.ordinal()).add((Tablet)device);
    } else if (device instanceof Smartwatch) {
      devices.get(Category.SMARTWATCH.ordinal()).add((Smartwatch)device);
    } else if (device instanceof Accessory) {
      devices.get(Category.ACCESSORY.ordinal()).add((Accessory)device);
    }
  }

  /**
   * Removes a device from the inventory using the user input.
   *
   * @param scanner the scanner to get input
   */
  public void removeDevice(Scanner scanner) {
    System.out.print("Please enter the name of the device to remove: ");
    String name = scanner.nextLine();
    Device device = findDevice(name);
    if (device == null) {
      InventoryManager.printError("Device not found");
      InventoryManager.waitForEnter(scanner);
      return;
    }
    devices.get(device.getCategory().ordinal()).remove(device);
    InventoryManager.printSuccess("Device removed successfully");
    InventoryManager.waitForEnter(scanner);
  }

  /**
   * Updates a device in the inventory using the user input. The user can update
   * the price and quantity of the device.
   *
   * @param scanner the scanner to get input
   */
  public void updateDevice(Scanner scanner) {
    System.out.print("Please enter the name of the device to update: ");
    String name = scanner.nextLine();
    Device device = findDevice(name);
    if (device == null) {
      InventoryManager.printError("Device not found");
      InventoryManager.waitForEnter(scanner);
      return;
    }
    System.out.print(
        "Please enter the new price of the device or empty to skip: ");
    String input = scanner.nextLine();
    if (!input.isEmpty()) {
      try {
        device.setPrice(Double.parseDouble(input));
      } catch (Exception e) {
        InventoryManager.printError("Invalid input assumed empty");
      }
    }
    System.out.print(
        "Please enter the new quantity of the device or empty to skip: ");
    input = scanner.nextLine();
    if (!input.isEmpty()) {
      try {
        device.setQuantity(Integer.parseInt(input));
      } catch (Exception e) {
        InventoryManager.printError("Invalid input assumed empty");
      }
    }
    InventoryManager.printSuccess("Device updated successfully to:" + device);
    InventoryManager.waitForEnter(scanner);
  }

  /**
   * Lists all devices in the inventory. The devices are listed by category.
   * The user can see the category, name, price, and quantity of each device.
   * If there are no devices in the inventory, an error message is displayed.
   */
  public void listDevices() {
    int deviceCount = 0;
    boolean isEmpty = true;
    for (int i = 0; i < devices.size(); i++) {
      for (Device device : devices.get(i)) {
        System.out.println((++deviceCount) + ". " + device);
        isEmpty = false;
      }
    }
    if (isEmpty) {
      InventoryManager.printError("No devices in inventory");
    }
  }

  /**
   * Finds the cheapest device in the inventory. The user can see the category,
   * name, price, and quantity of the cheapest device. If there are no devices
   * in the inventory, an error message is displayed.
   */
  public void findCheapestDevice() {
    Device cheapest = null;
    for (int i = 0; i < devices.size(); i++) {
      for (Device device : devices.get(i)) {
        if (cheapest == null || device.getPrice() < cheapest.getPrice()) {
          cheapest = device;
        }
      }
    }
    if (cheapest == null) {
      InventoryManager.printError("No devices in inventory");
    } else {
      System.out.println("The cheapest device is: \n" + cheapest);
    }
  }

  /**
   * Sorts all devices in the inventory by price. The devices are sorted in
   * ascending order by price. The user can see the category, name, price, and
   * quantity of each device.
   */
  public void sortByPrice() {
    ArrayList<Device> sortedDevices = new ArrayList<>();
    for (int i = 0; i < devices.size(); i++) {
      sortedDevices.addAll(devices.get(i));
    }
    sortedDevices.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
    for (Device device : sortedDevices) {
      System.out.println(device);
    }
  }

  /**
   * Calculates the total value of all devices in the inventory. The user can
   * see the total value of all devices in the inventory.
   *
   * @return the total value of all devices in the inventory
   */
  public double calculateTotalValue() {
    double totalValue = 0;
    for (int i = 0; i < devices.size(); i++) {
      for (Device device : devices.get(i)) {
        totalValue += device.getPrice() * device.getQuantity();
      }
    }
    return totalValue;
  }

  /**
   * Restocks a device in the inventory using the user input. The user can add
   * or remove stock from the device. The user can see the new quantity of the
   * device. If the device is not found, an error message is displayed.
   *
   * @param scanner the scanner to get input
   */
  public void restockDevice(Scanner scanner) {
    System.out.print("Please enter the name of the device to restock: ");
    String name = scanner.nextLine();
    Device device = findDevice(name);
    if (device == null) {
      InventoryManager.printError("Device not found");
      InventoryManager.waitForEnter(scanner);
      return;
    }
    System.out.println("Do you want to add or remove stock?");
    String input = scanner.nextLine().toLowerCase();
    int multiplier = 1;
    if (input.equalsIgnoreCase("remove")) {
      multiplier = -1;
    } else if (!input.equalsIgnoreCase("add")) {
      InventoryManager.printError("Invalid input assumed add");
    }
    System.out.print("Please enter the quantity to restock: ");
    input = scanner.nextLine();
    try {
      int quantity = Integer.parseInt(input);
      if (quantity < 0) {
        InventoryManager.printError("Invalid quantity");
        InventoryManager.waitForEnter(scanner);
        return;
      }
      device.setQuantity(device.getQuantity() + quantity * multiplier);
      InventoryManager.printSuccess(
          "Device restocked successfully to new quantity: " +
          device.getQuantity());
    } catch (Exception e) {
      InventoryManager.printError("Invalid input");
    }
  }

  /**
   * Exports the inventory to a file. The user can see the category, name,
   * price, and quantity of each device. The user can also see the total number
   * of devices and the total value of the inventory.
   *
   * @throws Exception if an error occurs
   */
  public void exportInventory() throws Exception {
    FileWriter writer = new FileWriter("export.txt");
    writer.write("Electronic Store Inventory\n");
    writer.write("Generated on: " + LocalDate.now() + "\n\n");

    int fieldWidths[] = {8, 4, 5, 8};
    int deviceCount = 0;
    boolean isEmpty = true;
    for (int i = 0; i < devices.size(); i++) {
      for (Device device : devices.get(i)) {
        deviceCount++;
        isEmpty = false;
        fieldWidths[0] =
            Math.max(fieldWidths[0], device.getCategoryName().length());
        fieldWidths[1] = Math.max(fieldWidths[1], device.getName().length());
        fieldWidths[2] =
            Math.max(fieldWidths[2],
                     String.format("%.2f $", device.getPrice()).length());
        fieldWidths[3] =
            Math.max(fieldWidths[3], device.getQuantity().toString().length());
      }
    }
    if (!isEmpty) {
      writer.write("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                              fieldWidths[2] + fieldWidths[3]) +
                   "\n");
      writer.write(String.format(
          "| No. | %-" + fieldWidths[0] + "s | %-" + fieldWidths[1] + "s | %-" +
              fieldWidths[2] + "s | %-" + fieldWidths[3] + "s |\n",
          "Category", "Name", "Price", "Quantity"));
      writer.write("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                              fieldWidths[2] + fieldWidths[3]) +
                   "\n");
      int deviceIndex = 0;
      for (int i = 0; i < devices.size(); i++) {
        for (Device device : devices.get(i)) {
          writer.write(String.format(
              "| %3d | %-" + fieldWidths[0] + "s | %-" + fieldWidths[1] +
                  "s | %-" + fieldWidths[2] + "s | %-" + fieldWidths[3] +
                  "s |\n",
              ++deviceIndex, device.getCategoryName(), device.getName(),
              String.format("%.2f $", device.getPrice()),
              device.getQuantity()));
        }
      }
      writer.write("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                              fieldWidths[2] + fieldWidths[3]) +
                   "\n");
    } else {
      writer.write("No devices in inventory\n");
    }

    writer.write("\n");
    writer.write("Summary:\n");
    writer.write("Total number of devices: " + deviceCount + "\n");
    writer.write("Total value of inventory: " +
                 String.format("%.2f $", calculateTotalValue()) + "\n");
    writer.close();
  }

  /**
   * Finds a device in the inventory using the specified name.
   *
   * @param name the name of the device to find
   * @return the device with the specified name or null if not found
   */
  private Device findDevice(String name) {
    for (int i = 0; i < devices.size(); i++) {
      for (Device device : devices.get(i)) {
        if (device.getName().equals(name)) {
          return device;
        }
      }
    }
    return null;
  }
}
