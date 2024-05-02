public class Item implements ItemInterface {
    private String name;
    private int initialQuantity;
    private int quantity;
    private double unitPrice;
     private int remainingQuantity;
    

    public Item(String name, int initialQuantity, double unitPrice) {
        this.name = name;
        this.initialQuantity = initialQuantity;
        this.quantity = initialQuantity;
        this.unitPrice = unitPrice;
    }

    @Override
    public double calculateTotalPrice() {
        return quantity * unitPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getUnitPrice() {
        return unitPrice;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

  public int getOrderedQuantity() {
    return getInitialQuantity() - getQuantity();
}

    public int getTotalQuantity() {
        return initialQuantity;
    }
public int getRemainingQuantity() {
    return getTotalQuantity() - getOrderedQuantity();
}
    
}
