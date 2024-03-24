
public interface Device {

  String getCategoryName();

  void setCategoryName(String categoryName);

  String getName();

  void setName(String name);

  Double getPrice();

  void setPrice(Double price);

  Integer getQuantity();

  void setQuantity(Integer quantity);

  boolean equals(Object obj);

  String toString();

  String toString(int[] fieldWidths);
}