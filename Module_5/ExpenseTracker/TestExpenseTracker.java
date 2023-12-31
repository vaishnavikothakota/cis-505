package ExpenseTracker;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is entry class to test the Expense tracker app.
 */
public class TestExpenseTracker { 
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("  Welcome to the Expense Tracker\n\n");
		
		boolean continueLoop = true;
		while (continueLoop) {
			int input = ValidatorIO.getInt(scanner, menu());
			if (input == 1) {
				System.out.print("\n  MONTHLY EXPENSES\n\n");
				ArrayList<Transaction> transactions = TransactionIO.findAll();
				for (Transaction t: transactions) {
					System.out.print(t);
				}
			} else if (input == 2 ) {
				String c = "y";
				ArrayList<Transaction> transactions = new ArrayList<>();
				
				while (c.equalsIgnoreCase("y")) {
					String description = ValidatorIO.getString(scanner, "\n  Enter the description:  ");
					double amount = ValidatorIO.getDouble(scanner, "  Enter the amount: ");
					
					Transaction transaction = new Transaction(description, amount);
					
					transactions.add(transaction);
					c = ValidatorIO.getString( scanner, "\n  Add another transaction? (y/n): ");
				}
				
				try {
					TransactionIO.bulkInsert(transactions);
				} catch (IOException e) {
					System.out.println("\n  Exception: " + e.getMessage());
				}
			} else if (input == 3) {
				ArrayList<Transaction> transactions = TransactionIO.findAll();
				double totalExpense = 0;
				for (Transaction t: transactions) {
					totalExpense += t.getAmount();
				}
				String result = "\n  Your total monthly expense is $" + String.format("%,6.2f", totalExpense) + "\n\n";
				System.out.print(result);
			}
			
			System.out.println("  Continue? (y/n): ");
			String next = scanner.nextLine().toLowerCase();
			if (next.equals("n")) {
				continueLoop = false;
				System.out.print("\n Program terminated by the user...");
			}
		}
	}
	
	/**
	 * Method to display Menu options.
	 */
	public static String menu() {
		String result = "  MENU OPTIONS\n" + "    1. View Transactions\n" + "    2. Add Transactions\n"
				+ "    3. View Expense\n" + "\n  Please choose an option: ";
		return result;
	}

}
