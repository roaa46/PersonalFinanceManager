package PersonalFinanceManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinanceManager manager = new FinanceManager();
        ValidateInput validateInput = new ValidateInput();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("*** Welcome to Person Finance Manager System ***");
            System.out.println("Enter 1 to add transaction");
            System.out.println("Enter 2 to display report of your transactions");
            System.out.println("Enter 3 to filter your transactions by category");
            System.out.println("Enter 4 to exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter transaction type (INCOME/EXPENSE):");
                    String type = scanner.nextLine();
                    while (!validateInput.validateType(type)) {
                        System.out.println("Invalid type. Please enter INCOME or EXPENSE");
                        type = scanner.nextLine();
                    }
                    System.out.println("Enter transaction category (FOOD/TRANSPORTATION/SHOPPING/EXTRA):");
                    String category = scanner.nextLine();
                    while (!validateInput.validateCategory(category)) {
                        System.out.println("Invalid category. Please enter FOOD, TRANSPORTATION, SHOPPING or EXTRA");
                        category = scanner.nextLine();
                    }
                    System.out.println("Enter transaction amount:");
                    String amount = scanner.nextLine();
                    while (!validateInput.validateAmount(amount)) {
                        System.out.println("Invalid amount. Please enter a valid amount greater than 0");
                        amount = scanner.nextLine();
                    }
                    manager.addTransaction(type, category, Double.parseDouble(amount));
                    break;
                case 2:
                    manager.displayReport();
                    break;
                case 3:
                    System.out.println("Enter category to filter:");
                    String filterCategory = scanner.nextLine();
                    while (!validateInput.validateFilterCategory(filterCategory)) {
                        System.out.println("Invalid category. Please enter FOOD, TRANSPORTATION, SHOPPING or EXTRA");
                        filterCategory = scanner.nextLine();
                    }
                    manager.filterByCategory(filterCategory);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
