

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Inventory {
  private LinkedList<ArrayList<AbstractDevice>> devices;

  private enum Category { COMPUTER, PHONE, TABLET, SMARTWATCH, ACCESSORY }

  public Inventory() {
    devices = new LinkedList<>();
    devices.add(new ArrayList<>()); // COMPUTER
    devices.add(new ArrayList<>()); // PHONE
    devices.add(new ArrayList<>()); // TABLET
    devices.add(new ArrayList<>()); // SMARTWATCH
    devices.add(new ArrayList<>()); // ACCESSORY
  }

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

  // ! public void removeDevice(Scanner scanner) {

  public void updateDevice(Scanner scanner) {
    System.out.print("Please enter the name of the device to update: ");
    String name = scanner.nextLine();
    AbstractDevice device = findDevice(name);
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

  public void listDevices() {
    boolean isEmpty = true;
    for (int i = 0; i < devices.size(); i++) {
      for (AbstractDevice device : devices.get(i)) {
        System.out.println(i + 1 + ". " + device);
        isEmpty = false;
      }
    }
    if (isEmpty) {
      InventoryManager.printError("No devices in inventory");
    }
  }

  public void findCheapestDevice() {
    AbstractDevice cheapest = null;
    for (int i = 0; i < devices.size(); i++) {
      for (AbstractDevice device : devices.get(i)) {
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

  public void sortByPrice() {
    ArrayList<AbstractDevice> sortedDevices = new ArrayList<>();
    for (int i = 0; i < devices.size(); i++) {
      sortedDevices.addAll(devices.get(i));
    }
    sortedDevices.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
    for (AbstractDevice device : sortedDevices) {
      System.out.println(device);
    }
  }

  public void calculateTotalValue() {
    double totalValue = 0;
    for (int i = 0; i < devices.size(); i++) {
      for (AbstractDevice device : devices.get(i)) {
        totalValue += device.getPrice() * device.getQuantity();
      }
    }
    System.out.printf("The total value of the inventory is: %.2f $\n",
                      totalValue);
  }

  public void restockDevice(Scanner scanner) {
    System.out.print("Please enter the name of the device to restock: ");
    String name = scanner.nextLine();
    AbstractDevice device = findDevice(name);
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

  public void exportInventory() {
    System.out.println("Electronic Store Inventory");
    System.out.println("Generated on: " + LocalDate.now());
    System.out.println();

    int fieldWidths[] = {8, 4, 5, 8};
    int deviceCount = 0;
    boolean isEmpty = true;
    for (int i = 0; i < devices.size(); i++) {
      for (AbstractDevice device : devices.get(i)) {
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
      System.out.println("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                                    fieldWidths[2] + fieldWidths[3]));
      System.out.printf("| No. | %-" + fieldWidths[0] + "s | %-" +
                            fieldWidths[1] + "s | %-" + fieldWidths[2] +
                            "s | %-" + fieldWidths[3] + "s |\n",
                        "Category", "Name", "Price", "Quantity");
      System.out.println("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                                    fieldWidths[2] + fieldWidths[3]));
      for (int i = 0; i < devices.size(); i++) {
        for (AbstractDevice device : devices.get(i)) {
          System.out.printf("| %3d %s\n", i + 1, device.toString(fieldWidths));
        }
      }
      System.out.println("-".repeat(19 + fieldWidths[0] + fieldWidths[1] +
                                    fieldWidths[2] + fieldWidths[3]));
    } else {
      InventoryManager.printError("No devices in inventory");
      System.out.println();
    }

    System.out.println("Summary:");
    System.out.println("Total number of devices: " + deviceCount);
    calculateTotalValue();
    System.out.println();
  }

  private AbstractDevice findDevice(String name) {
    for (int i = 0; i < devices.size(); i++) {
      for (AbstractDevice device : devices.get(i)) {
        if (device.getName().equals(name)) {
          return device;
        }
      }
    }
    return null;
  }
}
