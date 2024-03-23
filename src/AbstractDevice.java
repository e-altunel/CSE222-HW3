public abstract class AbstractDevice {
  private String categoryName;
  private String name;
  private Double price;
  private Integer quantity;

  public AbstractDevice(final String categoryName, final String name,
                        final Double price, final Integer quantity) {
    this.categoryName = categoryName;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getCategoryName() { return categoryName; }

  public void setCategoryName(final String categoryName) {
    this.categoryName = categoryName;
  }

  public String getName() { return name; }

  public void setName(final String name) { this.name = name; }

  public Double getPrice() { return price; }

  public void setPrice(final Double price) { this.price = price; }

  public Integer getQuantity() { return quantity; }

  public void setQuantity(final Integer quantity) { this.quantity = quantity; }

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

  @Override
  public String toString() {
    return String.format("Category: %s, Name: %s, Price: %.2f$, Quantity:%d",
                         categoryName, name, price, quantity);
  }
}
