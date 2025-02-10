package PersonalFinanceManager;

public class ValidateInput {
    public static boolean validateType(String type) {
        return type.equalsIgnoreCase("Income") || type.equalsIgnoreCase("Expense");
    }

    public static boolean validateCategory(String category) {
        return category.equalsIgnoreCase("Food") || category.equalsIgnoreCase("Transportation") || category.equalsIgnoreCase("Shopping") || category.equalsIgnoreCase("Extra");
    }

    public static boolean validateAmount(String amount) {
        try {
            double value = Double.parseDouble(amount);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateFilterCategory(String category) {
        return category.equalsIgnoreCase("Food") || category.equalsIgnoreCase("Transportation") || category.equalsIgnoreCase("Shopping") || category.equalsIgnoreCase("Extra");
    }

}
