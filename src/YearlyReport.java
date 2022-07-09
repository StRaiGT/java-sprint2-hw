import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyChange> yearlyChanges;

    public YearlyReport(String fileContents) {
        yearlyChanges = new ArrayList<>();
        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            int month = Integer.parseInt(lineContents[0]);
            int amount = Integer.parseInt(lineContents[1]);
            boolean is_expense = Boolean.parseBoolean(lineContents[2]);
            YearlyChange yearlyChange = new YearlyChange(month, amount, is_expense);
            yearlyChanges.add(yearlyChange);
        }
    }

    public boolean isMonthFind(int numberMonth){
        boolean result = false;
        for (YearlyChange yearlyChange : yearlyChanges){
            if (yearlyChange.month == numberMonth){
                result = true;
            }
        }
        return result;
    }

    public int getMonthIncome(int numberMonth){
        int monthExpense = 0;
        for (YearlyChange yearlyChange : yearlyChanges) {
            if ((yearlyChange.month == numberMonth) & (!yearlyChange.is_expense)) {
                monthExpense = yearlyChange.amount;
                break;
            }
        }
        return monthExpense;
    }

    public int getMonthExpense(int numberMonth){
        int monthExpense = 0;
        for (YearlyChange yearlyChange : yearlyChanges) {
            if ((yearlyChange.month == numberMonth) & (yearlyChange.is_expense)) {
                monthExpense = yearlyChange.amount;
                break;
            }
        }
        return monthExpense;
    }

    public int getMonthProfit(int numberMonth) {
        return getMonthIncome(numberMonth) - getMonthExpense(numberMonth);
    }

    public int averageMonthIncome() {
        int allMonthIncome = 0;
        for (int i = 1; i < yearlyChanges.size(); i++) {
            allMonthIncome += getMonthIncome(i);
        }
        return allMonthIncome / 12;
    }

    public int averageMonthExpense() {
        int allMonthExpense = 0;
        for (int i = 1; i < yearlyChanges.size(); i++) {
            allMonthExpense += getMonthExpense(i);
        }
        return allMonthExpense / 12;
    }


}
