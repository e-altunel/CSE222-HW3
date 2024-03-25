
/**
 * The AbstractDevice class represents an abstract device that implements the
 * Device interface. It provides common functionality and attributes for all
 * device types.
 */
public abstract class AbstractDevice implements Device {
  private String categoryName;
  private Inventory.Category category;

  private String name;
  private Double price;
  private Integer quantity;

  /**
   * Constructs a new AbstractDevice object with the specified category name,
   * category, name, price, and quantity.
   *
   * @param categoryName the category name of the device
   * @param category the category of the device
   * @param name the name of the device
   * @param price the price of the device
   * @param quantity the quantity of the device
   */
  public AbstractDevice(final String categoryName,
                        final Inventory.Category category, final String name,
                        final Double price, final Integer quantity) {
    this.categoryName = categoryName;
    this.category = category;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * Returns the category name of the device.
   *
   * @return the category name of the device
   */
  @Override
  public String getCategoryName() {
    return categoryName;
  }

  /**
   * Sets the category name of the device.
   *
   * @param categoryName the category name to set
   */
  @Override
  public void setCategoryName(final String categoryName) {
    this.categoryName = categoryName;
  }

  /**
   * Returns the category of the device.
   *
   * @return the category of the device
   */
  @Override
  public Inventory.Category getCategory() {
    return category;
  }

  /**
   * Sets the category of the device.
   *
   * @param category the category to set
   */
  @Override
  public void setCategory(final Inventory.Category category) {
    this.category = category;
  }

  /**
   * Returns the name of the device.
   *
   * @return the name of the device
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the device.
   *
   * @param name the name to set
   */
  @Override
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Returns the price of the device.
   *
   * @return the price of the device
   */
  @Override
  public Double getPrice() {
    return price;
  }

  /**
   * Sets the price of the device.
   *
   * @param price the price to set
   */
  @Override
  public void setPrice(final Double price) {
    this.price = price;
  }

  /**
   * Returns the quantity of the device.
   *
   * @return the quantity of the device
   */
  @Override
  public Integer getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of the device.
   *
   * @param quantity the quantity to set
   */
  @Override
  public void setQuantity(final Integer quantity) {
    this.quantity = quantity;
  }

  /*
   * They are used to compare AbstractDevice objects based on their attributes.
   * The equals() method compares the attributes of two AbstractDevice objects
   */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final AbstractDevice other = (AbstractDevice)obj;
    if (categoryName == null) {
      if (other.categoryName != null)
        return false;
    } else if (!categoryName.equals(other.categoryName))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (price == null) {
      if (other.price != null)
        return false;
    } else if (!price.equals(other.price))
      return false;
    if (quantity == null) {
      if (other.quantity != null)
        return false;
    } else if (!quantity.equals(other.quantity))
      return false;
    return true;
  }

  /*
   * The toString() method returns a string representation of the AbstractDevice
   */
  @Override
  public String toString() {
    return String.format("Category: %s, Name: %s, Price: %.2f$, Quantity:%d",
                         categoryName, name, price, quantity);
  }

  /*
   * The toString() method returns a formatted string representation of the
   * AbstractDevice
   */
  @Override
  public String toString(final int[] fieldWidths) {
    return String.format("| %-" + fieldWidths[0] + "s | %-" + fieldWidths[1] +
                             "s | %" + (fieldWidths[2] - 2) + ".2f $ | %" +
                             fieldWidths[3] + "d |",
                         categoryName, name, price, quantity);
  }
}
