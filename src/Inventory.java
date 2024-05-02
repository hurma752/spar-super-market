import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private static final int LOW_INVENTORY_THRESHOLD = 50; // Adjust the threshold as needed
    private Map<String, Item> items;

    public Inventory() {
    this.items = new HashMap<>();
}

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void displayAvailableItems() {
        System.out.println("\nAvailable Items in the Supermarket:");
        int itemNumber = 1;
        for (Item item : items.values()) {
            System.out.println(itemNumber + ". " + item.getName());
            itemNumber++;
        }
    }

    public Item getItemByName(String itemName) {
        return items.get(itemName);
    }

    public void checkLowInventory() {
        System.out.println("\nChecking Low Inventory:");

        for (Item item : items.values()) {
            System.out.println("Item: " + item.getName() + ", Quantity: " + item.getQuantity());
            if (item.getQuantity() < LOW_INVENTORY_THRESHOLD) {
                System.out.println("Low inventory for item: " + item.getName());
            } else {
                System.out.println("Inventory for item " + item.getName() + " is sufficient.");
            }
        }
    }
}
