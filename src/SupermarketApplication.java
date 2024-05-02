import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupermarketApplication {
    private static final SparSupermarket supermarket = new SparSupermarket();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Salesperson> salespersons = new ArrayList<>();

    public static void main(String[] args) {
        Salesperson salesperson = new Salesperson("Aashir");
        salespersons.add(salesperson);

        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    processOnlineOrder(salesperson);
                    break;
                case 2:
                    processInStoreOrder(salesperson);
                    break;
                case 3:
                    generateManagementReport();
                    break;
                case 4:
                    System.out.println("Exiting Supermarket Application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nSupermarket Application Menu:");
        System.out.println("1. Process Online Order");
        System.out.println("2. Process In-Store Order");
        System.out.println("3. Generate Management Report");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void processOnlineOrder(Salesperson salesperson) {
        System.out.println("\nProcessing Online Order:");

        OnlineCustomer onlineCustomer = createOnlineCustomer();
        if (onlineCustomer != null) {
            customers.add(onlineCustomer);

            Order onlineOrder = createOrder(onlineCustomer, true);
            supermarket.processOnlineOrder(onlineOrder);

            if (validateStock(onlineOrder)) {
                generateBill(onlineOrder, salesperson);
            } else {
                System.out.println("Order cannot be processed due to insufficient stock.");
            }
        }
    }

    private static boolean validateStock(Order order) {
        for (Item item : order.getShoppingCart().getItems()) {
            Item availableItem = supermarket.getInventory().getItemByName(item.getName());
            if (availableItem != null && item.getQuantity() > availableItem.getQuantity()) {
                System.out.println("Error: Insufficient stock for item: " + item.getName());
                return false;
            }
        }
        return true;
    }

    private static OnlineCustomer createOnlineCustomer() {
        System.out.print("Enter online customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter online customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter online customer email: ");
        String email = scanner.nextLine();

        try {
            System.out.print("Enter online customer loyalty points: ");
            int loyaltyPoints = scanner.nextInt();
            scanner.nextLine();

            ContactDetails contactDetails = new ContactDetails();
            System.out.print("Enter customer phone number: ");
            contactDetails.setPhoneNumber(scanner.nextLine());
            contactDetails.setEmail(email);

            return new OnlineCustomer(name, contactDetails, address, loyaltyPoints);
        } catch (Exception e) {
            System.out.println("Invalid input for loyalty points. Please enter a valid number.");
            scanner.nextLine();
            return null;
        }
    }

    private static void processInStoreOrder(Salesperson salesperson) {
        System.out.println("\nProcessing In-Store Order:");

        Customer inStoreCustomer = createInStoreCustomer();
        if (inStoreCustomer != null) {
            customers.add(inStoreCustomer);

            Order inStoreOrder = createOrder(inStoreCustomer, false);
            supermarket.processInStoreOrder(inStoreOrder);

            generateBill(inStoreOrder, salesperson);
        }
    }

    private static Customer createInStoreCustomer() {
        System.out.print("Enter in-store customer name: ");
        String name = scanner.nextLine();

        ContactDetails contactDetails = createContactDetails();

        System.out.print("Enter in-store CNIC: ");
        String cnic = scanner.nextLine(); // Corrected variable name

        System.out.print("Enter Salesman_ID: ");
        String salesmanId = scanner.nextLine(); // Corrected variable name

        return new InStoreCustomer(name, contactDetails, cnic, salesmanId);
    }

    private static ContactDetails createContactDetails() {
        ContactDetails contactDetails = new ContactDetails();

        System.out.print("Enter customer phone number: ");
        String phoneNumber = scanner.nextLine();
        contactDetails.setPhoneNumber(phoneNumber);

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        contactDetails.setEmail(email);

        return contactDetails;
    }

    private static Order createOrder(Customer customer, boolean isOnline) {
        Order order = new Order(customer, isOnline);

        while (true) {
            displayOrderMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addItemToOrder(order);
                    break;
                case 2:
                    removeItemFromOrder(order);
                    break;
                case 3:
                    updateItemQuantityInOrder(order);
                    break;
                case 4:
                    displayOrderSummary(order);
                    break;
                case 5:
                    System.out.println("Finishing Order Creation.");
                    return order;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayOrderMenu() {
        System.out.println("\nOrder Menu:");
        System.out.println("1. Add Item to Order");
        System.out.println("2. Remove Item from Order");
        System.out.println("3. Update Item Quantity in Order");
        System.out.println("4. Display Order Summary");
        System.out.println("5. Finish Order Creation");
        System.out.print("Enter your choice: ");
    }

    private static void addItemToOrder(Order order) {
        boolean insufficientStock;
        do {
            System.out.println("\nAvailable Items in the Supermarket:");
            supermarket.getInventory().displayAvailableItems();

            System.out.print("Enter the number of the item you want to add: ");
            int itemNumber = scanner.nextInt();
            scanner.nextLine();

            List<Item> availableItems = new ArrayList<>(supermarket.getInventory().getItems().values());
            if (itemNumber >= 1 && itemNumber <= availableItems.size()) {
                Item selectedItem = availableItems.get(itemNumber - 1);

                System.out.print("Enter item quantity: ");
                int quantity = scanner.nextInt();

                if (selectedItem.getQuantity() >= quantity) {
                    Item itemToAdd = new Item(selectedItem.getName(), quantity, selectedItem.getUnitPrice());
                    order.addItem(itemToAdd);

                    System.out.println("Item added to the order.");
                    insufficientStock = false;
                } else {
                    System.out.println("Insufficient quantity in stock. Please enter a valid quantity.");
                    insufficientStock = true;
                }
            } else {
                System.out.println("Invalid item number. Please enter a valid item number.");
                insufficientStock = true;
            }
        } while (insufficientStock);
    }

    private static void removeItemFromOrder(Order order) {
        System.out.print("Enter item name to remove: ");
        String itemName = scanner.nextLine();

        Item itemToRemove = order.getShoppingCart().getItems().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElse(null);

        if (itemToRemove != null) {
            order.removeItem(itemToRemove);
            System.out.println("Item removed from the order.");
        } else {
            System.out.println("Item not found in the order.");
        }
    }

    private static void updateItemQuantityInOrder(Order order) {
        System.out.print("Enter item name to update quantity: ");
        String itemName = scanner.nextLine();

        Item itemToUpdate = order.getShoppingCart().getItems().stream()
                .filter(item -> item.getName().equals(itemName))
                .findFirst()
                .orElse(null);

        if (itemToUpdate != null) {
            System.out.print("Enter new quantity: ");
            int newQuantity = scanner.nextInt();
            order.updateItemQuantity(itemToUpdate, newQuantity);
            System.out.println("Item quantity updated in the order.");
        } else {
            System.out.println("Item not found in the order.");
        }
    }

private static void displayOrderSummary(Order order) {
    System.out.println("\nOrder Summary:");
    System.out.println("Customer Type: " + order.getCustomer().getCustomerType());
    System.out.println("Is Online Order: " + order.isOnline());

    // Display customer details
    System.out.println("Customer Details:");
    System.out.println(order.getCustomer().toString());

    ShoppingCart shoppingCart = order.getShoppingCart();
    if (shoppingCart != null && !shoppingCart.getItems().isEmpty()) {
        System.out.println("-------- Items in the Order --------");
        for (Item item : shoppingCart.getItems()) {
            System.out.println("Item: " + item.getName() +
                    ", Quantity: " + item.getQuantity() +
                    ", Unit Price: $" + item.getUnitPrice() +
                    ", Total Price: $" + item.calculateTotalPrice());
        }
        System.out.println("------------------------------------");
    } else {
        System.out.println("No items in the order.");
    }

    System.out.println("Total Price: $" + order.calculateTotalPrice());
}

    private static void generateBill(Order order, Salesperson salesperson) {
        Bill bill = new Bill(order, salesperson);
        double billAmount = bill.calculateFinalAmount();  // Assuming a method to calculate the total bill amount

        // Display the bill
        bill.display();

        // Update salesperson's summary
        salesperson.updateSalesSummary(billAmount);
    }

    private static void generateManagementReport() {
        System.out.println("\nGenerating Management Report:");

        // Display total bills and amount collected for each salesperson
        for (Salesperson sp : salespersons) {
            System.out.println("\nSalesperson: " + sp.getId());
            System.out.println("Total Bills Generated: " + sp.getTotalBillsGenerated());
            System.out.println("Total Amount Collected: $" + sp.getTotalAmountCollected());
        }

        // Other parts of the report
        System.out.println("\nCustomer Details:");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }

        supermarket.getInventory().checkLowInventory();

        String salesSummary = supermarket.generateReport();
        System.out.println("\nSales Summary:");
        System.out.println(salesSummary);
    }
}
