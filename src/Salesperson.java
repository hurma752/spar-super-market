public class Salesperson {
    private final String id;
    private int totalBills;
    private double totalAmount;
    private int totalRewardPoints;

    public Salesperson(String id) {
        this.id = id;
        this.totalBills = 0;
        this.totalAmount = 0;
        this.totalRewardPoints = 0;
    }

    public String getId() {
        return id;
    }

    public int getTotalBillsGenerated() {
        return totalBills;
    }

    public double getTotalAmountCollected() {
        return totalAmount;
    }

    public Bill generateBill(Order order) {
        Bill bill = new Bill(order, this);
        totalBills++;
        double billAmount = order.calculateTotalPrice(); // Get the total amount before discount
        totalAmount += billAmount;
        order.markAsProcessed();

        // Displaying reward points for in-store customers
        if (!order.isOnline() && order.getCustomer() instanceof InStoreCustomer) {
            InStoreCustomer inStoreCustomer = (InStoreCustomer) order.getCustomer();
            int rewardPoints = inStoreCustomer.calculateRewardPoints();
            System.out.println("Reward Points Earned: " + rewardPoints);
            totalRewardPoints += rewardPoints; // Update total reward points
        }

        // Displaying discount for online customers
        if (order.isOnline() && order.getCustomer() instanceof OnlineCustomer) {
            OnlineCustomer onlineCustomer = (OnlineCustomer) order.getCustomer();
            onlineCustomer.displayRewardPoints();
            System.out.println("Additional 5% Discount Applied for Online Customer");
        }

        System.out.println("Bill generated successfully.");
        return bill;
    }

    void updateSalesSummary(double billAmount) {
        totalBills++;
        totalAmount += billAmount;

        // You may add other logic or calculations related to the salesperson's summary here
    }

    public String displaySalesSummary() {
        return "Salesperson Summary: Total Bills - " + totalBills + ", Total Amount - " + totalAmount +
                ", Total Reward Points Earned - " + totalRewardPoints;
    }
}
