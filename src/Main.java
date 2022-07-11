import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReportsManager reportsManager = new ReportsManager();

        while (true) {
            printMenu();
            System.out.println("Введите команду:");
            String inputStr = scanner.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(inputStr);
                if (command == 1) {
                    int year = getInt();
                    reportsManager.addMonthlyReports(year);
                } else if (command == 2) {
                    int year = getInt();
                    reportsManager.addYearlyReports(year);
                } else if (command == 3) {
                    int year = getInt();
                    reportsManager.equalsReports(year);
                } else if (command == 4) {
                    int year = getInt();
                    reportsManager.getMonthlyReports(year);
                } else if (command == 5) {
                    int year = getInt();
                    reportsManager.getYearlyReports(year);
                } else {
                    throw new Exception("Не найдено подходящей числовой команды.");
                }
            }
            catch (Exception e){
                if (inputStr.equalsIgnoreCase("exit")){
                    System.out.println("Выход из приложения.");
                    break;
                } else {
                    System.out.println("Такой команды не существует.\n");
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Список доступных команд:");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("exit - Выйти из приложения");
    }

    public static int getInt(){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер года:");
            if (!scanner.hasNextInt()) {
                System.out.println("Требуется ввести число!");
                continue;
            }
            return scanner.nextInt();
        }
    }
}