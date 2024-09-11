import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class representing a transaction between two wallets
public class _5Transaction {
    private _1DatabaseConnection dbConnection;

    // Constructor initializes the database connection
    public _5Transaction() {
        this.dbConnection = new _1DatabaseConnection();
    }

    // Method to transfer funds between two wallets
    public boolean transferFunds(Wallet sender, Wallet receiver, double amount) {
        Connection conn = null;
        PreparedStatement updateSender = null;
        PreparedStatement updateReceiver = null;
        PreparedStatement logTransaction = null;

        try {
            // Get the database connection
            conn = dbConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Deduct amount from sender's wallet
            String deductSenderQuery = "UPDATE wallets SET balance = balance - ? WHERE wallet_id = ?";
            updateSender = conn.prepareStatement(deductSenderQuery);
            updateSender.setDouble(1, amount);
            updateSender.setInt(2, sender.getWalletId());
            updateSender.executeUpdate();

            // Add amount to receiver's wallet
            String creditReceiverQuery = "UPDATE wallets SET balance = balance + ? WHERE wallet_id = ?";
            updateReceiver = conn.prepareStatement(creditReceiverQuery);
            updateReceiver.setDouble(1, amount);
            updateReceiver.setInt(2, receiver.getWalletId());
            updateReceiver.executeUpdate();

            // Log transaction
            String logQuery = "INSERT INTO transactions (sender_id, receiver_id, amount) VALUES (?, ?, ?)";
            logTransaction = conn.prepareStatement(logQuery);
            logTransaction.setInt(1, sender.getWalletId());
            logTransaction.setInt(2, receiver.getWalletId());
            logTransaction.setDouble(3, amount);
            logTransaction.executeUpdate();

            // Commit the transaction
            conn.commit();
            return true;

        } catch (SQLException e) {
            // Rollback in case of any failure
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;

        } finally {
            // Close resources
            try {
                if (updateSender != null) updateSender.close();
                if (updateReceiver != null) updateReceiver.close();
                if (logTransaction != null) logTransaction.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
