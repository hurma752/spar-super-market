import java.util.*;

public class SparSupermarket {
    private Scanner scanner = new Scanner(System.in);

    private Inventory inventory;
    private List<Order> onlineOrders;
    private List<Order> inStoreOrders;
    private ManagementSystem management;

    public SparSupermarket() {
        this.inventory = new Inventory();
        this.onlineOrders = new ArrayList<>();
        this.inStoreOrders = new ArrayList<>();
        this.management = new ManagementSystem();
        initializeInventory();
        
    }
    public Inventory getInventory() {
        return inventory;
    }
    


     private void initializeInventory() {
        inventory.addItem(new Item("Milk", 100, 2.5));
        inventory.addItem(new Item("Cookies", 100, 1.0));
        inventory.addItem(new Item("Bread", 150, 1.2));
        inventory.addItem(new Item("Eggs", 120, 0.8));
        inventory.addItem(new Item("Butter", 80, 3.0));
        inventory.addItem(new Item("Cheese", 90, 2.0));
        inventory.addItem(new Item("Yogurt", 120, 1.5));
        inventory.addItem(new Item("Tomatoes", 200, 0.5));
        inventory.addItem(new Item("Potatoes", 180, 0.6));
        inventory.addItem(new Item("Apples", 150, 1.2));
        inventory.addItem(new Item("Bananas", 160, 0.7));
        inventory.addItem(new Item("Chicken", 80, 5.0));
        inventory.addItem(new Item("Pasta", 120, 1.8));
        inventory.addItem(new Item("Rice", 100, 2.0));
        inventory.addItem(new Item("Cereal", 110, 2.3));
        inventory.addItem(new Item("Coffee", 90, 4.5));
        inventory.addItem(new Item("Tea", 120, 3.0));
        inventory.addItem(new Item("Orange Juice", 100, 2.2));
        inventory.addItem(new Item("Salmon", 70, 8.0));
        inventory.addItem(new Item("Shampoo", 50, 3.5));
        
    }

    public void processOnlineOrder(Order order) {
        onlineOrders.add(order);
        System.out.println("Online Order processed successfully.");

    }

    public void processInStoreOrder(Order order) {
        inStoreOrders.add(order);
        System.out.println("In-Store Order processed successfully.");

    }
    
    public void displayAvailableItems() {
        System.out.println("\nAvailable Items in the Supermarket:");
        int itemNumber = 1;
        for (Item item : inventory.getItems().values()) {
            System.out.println(itemNumber + ". " + item.getName());
            itemNumber++;
        }
    }

public void addItemToOrder(Order order, int itemNumber, int quantity) {
    Map<String, Item> items = inventory.getItems();
    List<Item> availableItems = new ArrayList<>(items.values());

    while (itemNumber < 1 || itemNumber > availableItems.size()) {
        System.out.println("Invalid item number. Please enter a valid item number.");
        displayAvailableItems();
        System.out.print("Enter the number of the item you want to add: ");
        itemNumber = scanner.nextInt();
    }

    Item selectedItem = availableItems.get(itemNumber - 1);

    if (selectedItem.getRemainingQuantity() >= quantity) {
        Item itemToAdd = new Item(selectedItem.getName(), quantity, selectedItem.getUnitPrice());
        order.addItem(itemToAdd);
        System.out.println("Item added to the order.");

        double remainingQuantityPercentage = (double) selectedItem.getRemainingQuantity() / selectedItem.getTotalQuantity() * 100;
        if (remainingQuantityPercentage < 50) {
            System.out.println("ALERT: Quantity of " + selectedItem.getName() + " is below 50%! Consider restocking.");
        }
    } else {
        System.out.println("Insufficient quantity in stock. Please enter a valid quantity.");
    }
}



public String generateReport() {
        int totalOnlineOrders = onlineOrders.size();
        int totalInStoreOrders = inStoreOrders.size();

        StringBuilder report = new StringBuilder();
        report.append("Supermarket Report:\n");
        report.append("Total Online Orders: ").append(totalOnlineOrders).append("\n");
        report.append("Total In-Store Orders: ").append(totalInStoreOrders).append("\n");

        return report.toString();
    }


}