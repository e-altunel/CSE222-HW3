import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Inventory {
  private LinkedList<ArrayList<AbstractDevice>> devices;

  public Inventory() { devices = new LinkedList<>(); }

  public void addDevice() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Please enter the category of the device:");
    String category = scanner.nextLine();
    System.out.println("Please enter the name of the device:");
    String name = scanner.nextLine();
    System.out.println("Please enter the price of the device:");
    Double price = scanner.nextDouble();
    System.out.println("Please enter the quantity of the device:");
    Integer quantity = scanner.nextInt();
    AbstractDevice device = new AbstractDevice(category, name, price, quantity);
    addDevice(device);
  }

  public void addDevice(final AbstractDevice device) {
    for (ArrayList<AbstractDevice> category : devices) {
      if (category.get(0).getCategoryName().equals(device.getCategoryName())) {
        System.out.println("Adding " + device.getName() + " to inventory.");
        category.add(device);
        return;
      }
    }
    System.out.println("Adding " + device.getCategoryName() +
                       " category to inventory.");
    System.out.println("Adding " + device.getName() + " to inventory.");
    ArrayList<AbstractDevice> newCategory = new ArrayList<>();
    newCategory.add(device);
    devices.add(newCategory);
  }

  public void removeDevice(final AbstractDevice device) {
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        if (d.equals(device)) {
          System.out.println("Removing " + d.getName() + " from inventory.");
          category.remove(d);
          return;
        }
      }
    }
    System.out.println("Device not found in inventory.");
  }

  public AbstractDevice getDevice(final String name) {
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        if (d.getName().equals(name)) {
          return d;
        }
      }
    }
    return null;
  }

  public void printInventory() {
    int index = 1;
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        System.out.println(index + ". " + d);
      }
    }
  }

  public AbstractDevice findCheapestDevice() {
    AbstractDevice cheapest = null;
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        if (cheapest == null || d.getPrice() < cheapest.getPrice()) {
          cheapest = d;
        }
      }
    }
    return cheapest;
  }

  public ArrayList<AbstractDevice> sortDevicesByPrice() {
    ArrayList<AbstractDevice> sortedDevices = new ArrayList<>();
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        sortedDevices.add(d);
      }
    }
    sortedDevices.sort((d1, d2) -> d1.getPrice().compareTo(d2.getPrice()));
    return sortedDevices;
  }

  public Double calculateInventoryValue() {
    Double value = 0.0;
    for (ArrayList<AbstractDevice> category : devices) {
      for (AbstractDevice d : category) {
        value += d.getPrice() * d.getQuantity();
      }
    }
    return value;
  }
}
