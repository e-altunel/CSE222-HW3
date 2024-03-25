/**
 * Computer.java
 * This class represents a computer device.
 */
public class Computer extends AbstractDevice {
  /**
   * Constructor for the Computer class.
   * @param name the name of the computer
   * @param price the price of the computer
   * @param quantity the quantity of the computer
   */
  public Computer(final String name, final Double price,
                  final Integer quantity) {
    super("COMPUTER", Inventory.Category.COMPUTER, name, price, quantity);
  }
}
