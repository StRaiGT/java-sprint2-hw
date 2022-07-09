import java.util.ArrayList;

public class MonthlyReport {
    ArrayList<MonthlyChange> monthlyChanges;
    int monthNumber;

    public MonthlyReport(String fileContents, int monthNumber) {
        this.monthNumber = monthNumber;
        monthlyChanges = new ArrayList<>();

        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            String item_name = lineContents[0];
            boolean is_expense = Boolean.parseBoolean(lineContents[1]);
            int quantity = Integer.parseInt(lineContents[2]);
            int sum_of_one = Integer.parseInt(lineContents[3]);
            MonthlyChange monthlyChange = new MonthlyChange(item_name, is_expense, quantity, sum_of_one);
            monthlyChanges.add(monthlyChange);
        }
    }

    public int getSumIncome() {
        int SumIncome = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (!monthlyChange.is_expense) {
                SumIncome += monthlyChange.quantity * monthlyChange.sum_of_one;
            }
        }
        return SumIncome;
    }

    public int getSumExpense (){
        int SumExpense = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (monthlyChange.is_expense) {
                SumExpense += monthlyChange.quantity * monthlyChange.sum_of_one;
            }
        }
        return SumExpense;
    }

    public int getIndexMaxIncome() {
        int indexMaxIncome = 0;
        for (MonthlyChange monthlyChange : monthlyChanges) {
            if (!monthlyChange.is_expense) {
                int curIncome = monthlyChange.quantity * monthlyChange.sum_of_one;
                int maxIncome = monthlyChanges.get(indexMaxIncome).quantity * monthlyChanges.get(indexMaxIncome).sum_of_one;
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
            if (monthlyChange.is_expense) {
                int curExpense = monthlyChange.quantity * monthlyChange.sum_of_one;
                int maxExpense = monthlyChanges.get(indexMaxExpense).quantity * monthlyChanges.get(indexMaxExpense).sum_of_one;
                if (curExpense > maxExpense) {
                    indexMaxExpense = monthlyChanges.indexOf(monthlyChange);
                }
            }
        }
        return indexMaxExpense;
    }
}
