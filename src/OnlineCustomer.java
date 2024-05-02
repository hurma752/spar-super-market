public class OnlineCustomer extends Customer {
    private String address;
    private double loyaltyPoints;

    public OnlineCustomer(String name, ContactDetails contactDetails, String address, double loyaltyPoints) {
        super(name, contactDetails);
        this.address = address;
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String getCustomerType() {
        return "Online Customer";
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public double getDiscountPercentage() {
        double baseDiscount = (loyaltyPoints >= 100) ? 10.0 : 5.0;
        return baseDiscount + 5.0; 
    }

    @Override
public String toString() {
    return "Customer Name: " + getName() +
            ", Phone Number: " + getContactDetails().getPhoneNumber() +
            ", Email: " + getContactDetails().getEmail();
    // Add other fields as needed
}
    
    public void displayRewardPoints() {
        System.out.println("Reward Points: " + loyaltyPoints);
    }
}