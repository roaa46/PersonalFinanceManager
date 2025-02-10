package PersonalFinanceManager;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private String type; // INCOME, EXPENSE
    private String category; // FOOD, TRANSPORTATION, SHOPPING, EXTRA
    private double amount;
    private LocalDate date;

    Transaction(String type, String category, double amount, LocalDate date) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
