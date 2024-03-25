/**
 * Smartwatch class that extends AbstractDevice class.
 * This class represents a smartwatch device.
 */
public class Smartwatch extends AbstractDevice {
  /**
   * Constructor for the Smartwatch class.
   * @param name the name of the smartwatch
   * @param price the price of the smartwatch
   * @param quantity the quantity of the smartwatch
   */
  public Smartwatch(final String name, final Double price,
                    final Integer quantity) {
    super("SMARTWATCH", Inventory.Category.SMARTWATCH, name, price, quantity);
  }
}
