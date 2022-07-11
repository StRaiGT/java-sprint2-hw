import java.util.ArrayList;

public class YearlyReport {
    ArrayList<YearlyChange> yearlyChanges;
    int monthInYear = 12;

    public YearlyReport(String fileContents) {
        yearlyChanges = new ArrayList<>();
        String[] lines = fileContents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            int monthPosition = 0;
            int amountPosition = 1;
            int isExpensePosition = 2;

            String[] words = lines[i].split(",");
            int month = Integer.parseInt(words[monthPosition]);
            int amount = Integer.parseInt(words[amountPosition]);
            boolean isExpense = Boolean.parseBoolean(words[isExpensePosition]);
            yearlyChanges.add(new YearlyChange(month, amount, isExpense));
        }
    }

    public boolean isMonthFind(int numberMonth){
        for (YearlyChange yearlyChange : yearlyChanges){
            if (yearlyChange.month == numberMonth){
                return true;
            }
        }
        return false;
    }

    public int getMonthIncome(int numberMonth){
        int monthExpense = 0;
        for (YearlyChange yearlyChange : yearlyChanges) {
            if ((yearlyChange.month == numberMonth) && (!yearlyChange.isExpense)) {
                monthExpense = yearlyChange.amount;
                break;
            }
        }
        return monthExpense;
    }

    public int getMonthExpense(int numberMonth){
        int monthExpense = 0;
        for (YearlyChange yearlyChange : yearlyChanges) {
            if ((yearlyChange.month == numberMonth) && (yearlyChange.isExpense)) {
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
        return allMonthIncome / monthInYear;
    }

    public int averageMonthExpense() {
        int allMonthExpense = 0;
        for (int i = 1; i < yearlyChanges.size(); i++) {
            allMonthExpense += getMonthExpense(i);
        }
        return allMonthExpense / monthInYear;
    }
}
