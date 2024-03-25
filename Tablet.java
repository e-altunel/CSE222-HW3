/**
 * Tablet class that extends AbstractDevice class.
 * This class represents a tablet device.
 */
public class Tablet extends AbstractDevice {
  /**
   * Constructor for the Tablet class.
   * @param name the name of the tablet
   * @param price the price of the tablet
   * @param quantity the quantity of the tablet
   */
  public Tablet(final String name, final Double price, final Integer quantity) {
    super("TABLET", Inventory.Category.TABLET, name, price, quantity);
  }
}
