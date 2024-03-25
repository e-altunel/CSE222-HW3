/**
 * The Accessory class is a subclass of the AbstractDevice class.
 * It represents an accessory that can be sold in the inventory.
 */
public class Accessory extends AbstractDevice {
  /**
   * Constructor for the Accessory class.
   * @param name the name of the accessory
   * @param price the price of the accessory
   * @param quantity the quantity of the accessory
   */
  public Accessory(final String name, final Double price,
                   final Integer quantity) {
    super("ACCESSORY", Inventory.Category.ACCESSORY, name, price, quantity);
  }
}
