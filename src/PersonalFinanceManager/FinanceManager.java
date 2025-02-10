package PersonalFinanceManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceManager {
    private List<Transaction> transactions = new ArrayList<>();
    private static final String transactionsFile = "src/transactions.txt";

    private void loadTransactionsFromFile() {
        File file = new File(transactionsFile);
        if (!file.exists()) return;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                if (data.length == 4) {
                    transactions.add(new Transaction(data[0], data[1], Double.parseDouble(data[2]), LocalDate.parse(data[3])));
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to load transactions from file.");
        }
    }

    public FinanceManager() {
        loadTransactionsFromFile();
    }

    private double calculateBalance() {
        double income = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expense = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        return income - expense;
    }


    public void addTransaction(String type, String category, double amount) {
        if (type.equalsIgnoreCase("expense")) {
            double balance = calculateBalance();
            if (amount > balance) {
                System.out.println("You don't have enough balance to make this transaction.");
                return;
            }
        }
        Transaction transaction = new Transaction(type, category, amount, LocalDate.now());
        transactions.add(transaction);
        saveToTransactionsFile();
        System.out.println("Transaction added successfully");
    }

    public void displayReport() {
        // A stream is a sequence of elements that can be processed in parallel or sequentially.
        double income = transactions.stream() // transactions.stream() -> Stream<Transaction> so t is Transaction
                .filter(t -> t.getType().equalsIgnoreCase("Income"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double expense = transactions.stream()
                .filter(t -> t.getType().equalsIgnoreCase("Expense"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        double balance = income - expense;
        System.out.println("Accounts report");
        System.out.println("Total Income: " + income);
        System.out.println("Total Expense: " + expense);
        System.out.println("Current Balance: " + balance);
    }

    public void filterByCategory(String category) {
        System.out.println("Filtering According To " + category + ":");
        transactions.stream().filter(t -> t.getCategory().equalsIgnoreCase(category))
                .forEach(t -> System.out.println("Type: " + t.getType().toLowerCase() + " | "
                        + "Amount: " + t.getAmount() + " | " + "Date: " + t.getDate()));
    }

    private void saveToTransactionsFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFile, true))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.getType() + "," + transaction.getCategory() + "," + transaction.getAmount() + "," + transaction.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed To Save Data");
        }
    }
}