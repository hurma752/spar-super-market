import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Item> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

  public boolean updateItemQuantity(Item item, int quantity) {
    for (Item cartItem : items) {
        if (cartItem.getName().equals(item.getName())) {
            cartItem.setQuantity(quantity);
            System.out.println("Item quantity updated in the shopping cart.");
            return true;
        }
    }
    System.out.println("Item not found in the shopping cart.");
    return false;
}



    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.calculateTotalPrice();
        }
        return totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }
}
