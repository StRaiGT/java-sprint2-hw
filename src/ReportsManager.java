import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class ReportsManager {
    HashMap<Integer, ArrayList<MonthlyReport>> monthlyReportsByYear = new HashMap<>();
    HashMap<Integer, YearlyReport> yearlyReports = new HashMap<>();
    String[] monthNames = new String[] {"январь", "февраль", "март", "апрель", "май", "июнь",
            "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            return null;
        }
    }

    public void addMonthlyReports(int year) {
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            String monthlyReportPath;
            if (i < 10) {
                monthlyReportPath = "resources/m." + year + "0" + i + ".csv";
            } else {
                monthlyReportPath = "resources/m." + year + i + ".csv";
            }
            String fileContents = readFileContentsOrNull(monthlyReportPath);
            if (fileContents != null) {
                MonthlyReport monthlyReport = new MonthlyReport(fileContents, i);

                monthlyReports.add(monthlyReport);
            }
        }
        monthlyReportsByYear.put(year, monthlyReports);
        System.out.println("Считаны " + monthlyReportsByYear.get(year).size() + " месячных отчетов за " + year + " год.\n");
    }

    public void addYearlyReports(int year) {
        if (readFileContentsOrNull("resources/y." + year + ".csv") != null) {
            YearlyReport yearlyReport = new YearlyReport(readFileContentsOrNull("resources/y." + year + ".csv"));
            yearlyReports.put(year, yearlyReport);
            System.out.println("Считан годовой отчет за " + year + " год.\n");
        } else {
            System.out.println("Годовой отчет за " + year + " год не найден.\n");
        }

    }
    public void equalsReports(int year) {
        boolean result = true;
        if (!yearlyReports.containsKey(year) | !monthlyReportsByYear.containsKey(year)) {
            System.out.println("Не внесены месячные или годовые отчеты за " + year + " год.\n");
            return;
        }
        System.out.println("Начата сверка отчетов за " + year + " год");
        for (int i = 1; i <= 12; i++) {
            int index = -1;
            for (MonthlyReport monthlyReport : monthlyReportsByYear.get(year)) {
                if (monthlyReport.monthNumber == i){
                    index = monthlyReportsByYear.get(year).indexOf(monthlyReport);
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Отсутствуют месячный отчет за " + monthNames[i - 1]);
                continue;
            }

            if (!yearlyReports.get(year).isMonth(i)){
                System.out.println("Отсутствуют годовой отчет за " + monthNames[i - 1]);
                continue;
            }

            int incomeMonthlyReport = monthlyReportsByYear.get(year).get(index).getSumIncome();
            int incomeYearlyReport = yearlyReports.get(year).getMonthIncome(i);
            if (incomeMonthlyReport != incomeYearlyReport) {
                System.out.println("Обнаружено несоответствие доходов за " + monthNames[i - 1]);
                result = false;
            }

            int expenseMonthlyReport = monthlyReportsByYear.get(year).get(index).getSumExpense();
            int expenseYearlyReport = yearlyReports.get(year).getMonthExpense(i);
            if (expenseMonthlyReport != expenseYearlyReport) {
                System.out.println("Обнаружено несоответствие расходов за " + monthNames[i - 1]);
                result = false;
            }
        }
        if (result) {
            System.out.println("Сверка отчетов успешно завершена");
        }
        System.out.println();
    }

    public void getMonthlyReports(int year) {
        if (monthlyReportsByYear.containsKey(year)) {
            for (MonthlyReport monthlyReport : monthlyReportsByYear.get(year)) {
                System.out.println(monthNames[monthlyReport.monthNumber - 1] + " " + year + " года:");

                int indexMaxIncome = monthlyReport.getIndexMaxIncome();
                String nameMaxIncome = monthlyReport.monthlyChanges.get(indexMaxIncome).item_name;
                int maxIncome = monthlyReport.monthlyChanges.get(indexMaxIncome).quantity * monthlyReport.monthlyChanges.get(indexMaxIncome).sum_of_one;
                System.out.println("Самый прибыльный товар - " + nameMaxIncome + ". Размер прибыли - " + maxIncome);

                int indexMaxExpense = monthlyReport.getIndexMaxExpense();
                String nameMaxExpense = monthlyReport.monthlyChanges.get(indexMaxExpense).item_name;
                int maxExpense = monthlyReport.monthlyChanges.get(indexMaxExpense).quantity * monthlyReport.monthlyChanges.get(indexMaxExpense).sum_of_one;
                System.out.println("Самый большие траты - " + nameMaxExpense + ". Размер трат - " + maxExpense);

                System.out.println();
            }
        } else {
            System.out.println("Не внесены месячные отчеты за " + year + " год.\n");
            return;
        }
        if (monthlyReportsByYear.get(year).size() == 0){
            System.out.println("Список месячных отчетов пуст.\n");
        }
    }

    public void getYearlyReports(int year) {
        if (yearlyReports.containsKey(year)) {
            System.out.println(year + " год:");
            for (int i = 1; i <= 12; i++) {
                if (yearlyReports.get(year).isMonth(i)) {
                    System.out.println("Прибыль за " + monthNames[i - 1] + " составила " + yearlyReports.get(year).getMonthProfit(i));
                }
            }
            System.out.println("Средний расход за 12 месяцев составил " + yearlyReports.get(year).averageMonthExpense());
            System.out.println("Средний доход за 12 месяцев составил " + yearlyReports.get(year).averageMonthIncome());
            System.out.println();
        } else {
            System.out.println("Не внесены годовые отчеты за " + year + " год.\n");
        }
    }
}