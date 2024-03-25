/**
 * Device.java
 * This interface represents a device.
 */
public interface Device {

  /**
   * Returns the category name of the device.
   *
   * @return the category name of the device
   */
  String getCategoryName();

  /**
   * Sets the category name of the device.
   *
   * @param categoryName the category name to set
   */
  void setCategoryName(String categoryName);

  /**
   * Returns the category of the device.
   *
   * @return the category of the device
   */
  Inventory.Category getCategory();

  /**
   * Sets the category of the device.
   *
   * @param category the category to set
   */
  void setCategory(Inventory.Category category);

  /**
   * Returns the name of the device.
   *
   * @return the name of the device
   */
  String getName();

  /**
   * Sets the name of the device.
   *
   * @param name the name to set
   */
  void setName(String name);

  /**
   * Returns the price of the device.
   *
   * @return the price of the device
   */
  Double getPrice();

  /**
   * Sets the price of the device.
   *
   * @param price the price to set
   */
  void setPrice(Double price);

  /**
   * Returns the quantity of the device.
   *
   * @return the quantity of the device
   */
  Integer getQuantity();

  /**
   * Sets the quantity of the device.
   *
   * @param quantity the quantity to set
   */
  void setQuantity(Integer quantity);

  /**
   * Returns the total value of the device.
   *
   * @return the total value of the device
   */
  boolean equals(Object obj);

  /**
   * Returns the total value of the device.
   *
   * @return the total value of the device
   */
  String toString();

  /**
   * Returns the total value of the device.
   *
   * @param fieldWidths the field widths
   * @return the total value of the device
   */
  String toString(int[] fieldWidths);
}