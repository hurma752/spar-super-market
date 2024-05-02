import java.util.ArrayList;
import java.util.List;

public class ManagementSystem {
    private List<Salesperson> salespersons;
    private Inventory inventory;

    public ManagementSystem() {
        this.salespersons = new ArrayList<>();
        // For simplicity, add a sample salesperson
        addSalesperson(new Salesperson("Aashir"));
        this.inventory = new Inventory();
    }

    public final void addSalesperson(Salesperson salesperson) {
        salespersons.add(salesperson);
    }

    public Bill generateBill(Order order) {
        Salesperson salesperson = selectSalesperson();
        return salesperson.generateBill(order);
    }

    public String generateReport() {
        // Implement logic to generate a comprehensive report
        StringBuilder reportData = new StringBuilder();

        reportData.append("Sales Summary:\n");
        for (Salesperson salesperson : salespersons) {
            reportData.append("Salesperson ID: ").append(salesperson.getId()).append("\n");
            reportData.append("Total Bills: ").append(salesperson.getTotalBillsGenerated()).append("\n");
            reportData.append("Total Amount: $").append(salesperson.getTotalAmountCollected()).append("\n");
        }


        return reportData.toString();
    }

   private Salesperson selectSalesperson() {
    if (salespersons.isEmpty()) {
        return null; // No salespersons available
    }

    // Find the salesperson with the lowest workload (fewest total bills)
    Salesperson selectedSalesperson = salespersons.get(0);
    int minTotalBills = selectedSalesperson.getTotalBillsGenerated();

    for (Salesperson salesperson : salespersons) {
        if (salesperson.getTotalBillsGenerated() < minTotalBills) {
            selectedSalesperson = salesperson;
            minTotalBills = salesperson.getTotalBillsGenerated();
        }
    }

    return selectedSalesperson;
}

}
