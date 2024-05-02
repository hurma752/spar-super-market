import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private ContactDetails contactDetails;
    private List<Order> orders;

    public Customer(String name, ContactDetails contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.orders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order getLatestOrder() {
        if (orders.isEmpty()) {
            return null; 
        }
        return orders.get(orders.size() - 1);
    }

    public String getCustomerType() {
        return "Customer";
    }

@Override
public String toString() {
    return "Customer Name: " + getName() +
            ", Phone Number: " + getContactDetails().getPhoneNumber() +
            ", Email: " + getContactDetails().getEmail();
}

}