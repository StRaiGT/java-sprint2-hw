import java.util.ArrayList;
import java.util.List;

public class MonthlyReport {
    List<MonthlyChange> monthlyChanges;
    int monthNumber;

    public MonthlyReport(String fileContents, int monthNumber) {
        this.monthNumber = monthNumber;
        monthlyChanges = new ArrayList<>();

        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            int itemNamePosition = 0;
            int isExpensePosition = 1;
            int quantityPosition = 2;
            int sumOfOnePosition = 3;

            String[] words = lines[i].split(",");
            String itemName = words[itemNamePosition];
            boolean isExpense = Boolean.parseBoolean(words[isExpensePosition]);
            int quantity = Integer.parseInt(words[quantityPosition]);
            int sumOfOne = Integer.parseInt(words[sumOfOnePosition]);
            monthlyChanges.add(new MonthlyChange(itemName, isExpense, quantity, sumOfOne));
        }
    }

    public int getSumIncome() {
        int SumIncome = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (!monthlyChange.isExpense) {
                SumIncome += monthlyChange.quantity * monthlyChange.sumOfOne;
            }
        }
        return SumIncome;
    }

    public int getSumExpense (){
        int SumExpense = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (monthlyChange.isExpense) {
                SumExpense += monthlyChange.quantity * monthlyChange.sumOfOne;
            }
        }
        return SumExpense;
    }

    public int getIndexMaxIncome() {
        int indexMaxIncome = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (!monthlyChange.isExpense) {
                int curIncome = monthlyChange.quantity * monthlyChange.sumOfOne;
                int maxIncome = monthlyChanges.get(indexMaxIncome).quantity * monthlyChanges.get(indexMaxIncome).sumOfOne;
                if (curIncome > maxIncome) {
                    indexMaxIncome = monthlyChanges.indexOf(monthlyChange);
                }
            }
        }
        return indexMaxIncome;
    }

    public int getIndexMaxExpense(){
        int indexMaxExpense = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (monthlyChange.isExpense) {
                int curExpense = monthlyChange.quantity * monthlyChange.sumOfOne;
                int maxExpense = monthlyChanges.get(indexMaxExpense).quantity * monthlyChanges.get(indexMaxExpense).sumOfOne;
                if (curExpense > maxExpense) {
                    indexMaxExpense = monthlyChanges.indexOf(monthlyChange);
                }
            }
        }
        return indexMaxExpense;
    }
}
