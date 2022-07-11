public class MonthlyChange {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;

    public MonthlyChange(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
