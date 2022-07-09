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
                if ((command < 1) || (command > 5)) {
                    throw new Exception();
                }

                if (command == 1) {
                    int year = checkInt();
                    reportsManager.addMonthlyReports(year); // Считать все месячные отчёты
                } else if (command == 2) {
                    int year = checkInt();
                    reportsManager.addYearlyReports(year); // Считать годовой отчёт
                } else if (command == 3) {
                    int year = checkInt(); // Сверить отчёты
                    reportsManager.equalsReports(year);
                } else if (command == 4) {
                    int year = checkInt();
                    reportsManager.getMonthlyReports(year); // Вывести информацию о всех месячных отчётах
                } else if (command == 5) {
                    int year = checkInt();
                    reportsManager.getYearlyReports(year); // Вывести информацию о годовом отчёте
                }
            }
            catch (Exception e){
                if (inputStr.toLowerCase().equals("exit")){
                    System.out.println("Выход из приложения.");
                    break;
                } else {
                    System.out.println("Такой команды не существует.\n");
                    continue;
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

    public static int checkInt(){
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