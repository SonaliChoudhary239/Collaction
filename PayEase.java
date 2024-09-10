import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PayEase {
    private String walletId;
    private Connection connection;

    // Constructor to initialize wallet with a unique ID and establish a database connection
    public PayEase(String walletId) {
        this.walletId = walletId;
        try {
            // Connect to MySQL
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayEase", "root", "1234");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a transaction (credit or debit)
    public void addTransaction(double amount, String transactionType) {
        try {
            String query = "INSERT INTO transactions (wallet_id, amount, transaction_type) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, walletId);
            stmt.setDouble(2, amount);
            stmt.setString(3, transactionType);
            stmt.executeUpdate();
            System.out.println("Transaction added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get the transaction history
    public List<String> getTransactionHistory() {
        List<String> history = new ArrayList<>();
        try {
            String query = "SELECT transaction_id, amount, transaction_type, transaction_date FROM transactions WHERE wallet_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, walletId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                double amount = rs.getDouble("amount");
                String type = rs.getString("transaction_type");
                Timestamp date = rs.getTimestamp("transaction_date");
                history.add("ID: " + transactionId + ", Amount: " + amount + ", Type: " + type + ", Date: " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    // Close the connection when done
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method for user interaction
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input wallet ID
        System.out.print("Enter your wallet ID: ");
        String walletId = scanner.nextLine();
        
        PayEase wallet = new PayEase(walletId);

        boolean running = true;

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transaction History");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a transaction
                    System.out.print("Enter transaction amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter transaction type (credit or debit): ");
                    String transactionType = scanner.next();

                    if (!transactionType.equalsIgnoreCase("credit") && !transactionType.equalsIgnoreCase("debit")) {
                        System.out.println("Invalid transaction type. Please enter 'credit' or 'debit'.");
                    } else {
                        wallet.addTransaction(amount, transactionType);
                    }
                    break;

                case 2:
                    // View transaction history
                    List<String> history = wallet.getTransactionHistory();
                    System.out.println("\nTransaction History:");
                    for (String transaction : history) {
                        System.out.println(transaction);
                    }
                    break;

                case 3:
                    // Exit the application
                    running = false;
                    System.out.println("Exiting PayEase...");
                    wallet.closeConnection();
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
