public class Order {
    private Customer customer;
    private boolean isOnline;
    private boolean isProcessed;
    private ShoppingCart shoppingCart;

    public Order(Customer customer, boolean isOnline) {
        this.customer = customer;
        this.isOnline = isOnline;
        this.isProcessed = false;
        this.shoppingCart = new ShoppingCart();
    }

    public void addItem(Item item) {
        shoppingCart.addItem(item);
        System.out.println("Item added to the order.");
    }

    public void removeItem(Item item) {
        shoppingCart.removeItem(item);
        System.out.println("Item removed from the order.");
    }

    public void updateItemQuantity(Item item, int quantity) {
        shoppingCart.updateItemQuantity(item, quantity);
        System.out.println("Item quantity updated in the order.");
    }

    public double calculateTotalPrice() {
        return shoppingCart.calculateTotalPrice();
    }

    public void markAsProcessed() {
        isProcessed = true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}