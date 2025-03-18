import java.sql.*;

public class Admin {
    private Connection connection;

    public Admin(Connection connection) {
        this.connection = connection;
    }

    public void createAccount(String holderName, double balance, String status, String login, int pin) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO accounts (holderName, balance, status, login, pin) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, holderName);
            stmt.setDouble(2, balance);
            stmt.setString(3, status);
            stmt.setString(4, login);
            stmt.setInt(5, pin);
            stmt.executeUpdate();
            System.out.println("Account Created Successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAccount(int accountNumber) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM accounts WHERE accountNumber = ?");
            stmt.setInt(1, accountNumber);
            stmt.executeUpdate();
            System.out.println("Account Deleted Successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAccount(int accountNumber, String newHolderName, String newStatus) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE accounts SET holderName = ?, status = ? WHERE accountNumber = ?"
            );
            stmt.setString(1, newHolderName);
            stmt.setString(2, newStatus);
            stmt.setInt(3, accountNumber);
            stmt.executeUpdate();
            System.out.println("Account Updated Successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchAccount(int accountNumber) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Account Found: ");
                System.out.println("Account Number: " + rs.getInt("accountNumber"));
                System.out.println("Holder Name: " + rs.getString("holderName"));
                System.out.println("Balance: " + rs.getDouble("balance"));
                System.out.println("Status: " + rs.getString("status"));
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
