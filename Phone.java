/**
 * Phone class that extends AbstractDevice class.
 * This class represents a phone device.
 */
public class Phone extends AbstractDevice {
  /**
   * Constructor for the Phone class.
   * @param name the name of the phone
   * @param price the price of the phone
   * @param quantity the quantity of the phone
   */
  public Phone(final String name, final Double price, final Integer quantity) {
    super("PHONE", Inventory.Category.PHONE, name, price, quantity);
  }
}
