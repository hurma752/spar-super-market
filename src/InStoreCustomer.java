public class InStoreCustomer extends Customer {
    private String cnic;
    private String salesmanId;  // New field to store the Salesman_ID
    private static int totalBillsGenerated;  // New static field to track total number of bills
    private static double totalAmountCollected;  // New static field to track total amount collected

    private static final int POINTS_THRESHOLD_1 = 10;
    private static final int POINTS_THRESHOLD_2 = 30;
    private static final double DISCOUNT_PERCENTAGE_1 = 0.02;
    private static final double DISCOUNT_PERCENTAGE_2 = 0.06;
    private static final double DISCOUNT_PERCENTAGE_3 = 0.08;

    public InStoreCustomer(String name, ContactDetails contactDetails, String cnic, String salesmanId) {
        super(name, contactDetails);
        this.cnic = cnic;
        this.salesmanId = salesmanId;
    }

    @Override
    public String getCustomerType() {
        return "In-Store Customer";
    }

    public String getCNIC() {
        return cnic;
    }

    public String getSalesmanId() {
        return salesmanId;
    }

    public static int getTotalBillsGenerated() {
        return totalBillsGenerated;
    }

    public static double getTotalAmountCollected() {
        return totalAmountCollected;
    }

    
    public double applyDiscount(double totalAmount) {
        int rewardPoints = calculateRewardPoints();

        double discount;

        if (rewardPoints >= POINTS_THRESHOLD_2) {
            discount = totalAmount * DISCOUNT_PERCENTAGE_3;
        } else if (rewardPoints > POINTS_THRESHOLD_1) {
            discount = totalAmount * DISCOUNT_PERCENTAGE_2;
        } else {
            discount = totalAmount * DISCOUNT_PERCENTAGE_1;
        }

        // Update totalBillsGenerated and totalAmountCollected
        totalBillsGenerated++;
        totalAmountCollected += totalAmount - discount;

        return discount;
    }
    @Override
public String toString() {
    return "\nCustomer Name: " + getName() +
            ",\n Phone Number: " + getContactDetails().getPhoneNumber() +
            ",\n Email: " + getContactDetails().getEmail();
    // Add other fields as needed
}

    // New method to calculate reward points
    public int calculateRewardPoints() {
        Order latestOrder = getLatestOrder();

        if (latestOrder != null && latestOrder.getShoppingCart() != null) {
            return latestOrder.getShoppingCart().getItems().stream()
                    .mapToInt(Item::getQuantity)
                    .sum();
        }

        return 0;
    }
}