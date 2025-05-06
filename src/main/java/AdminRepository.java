import java.sql.*;

public class AdminRepository {
    private Connection connection;

    public AdminRepository(Connection connection) {
        this.connection = connection;
    }

    public void createAccount(String holderName, double balance, String status, String login, int pin) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO accounts (holderName, balance, status, login, pin) VALUES (?, ?, ?, ?, ?)"
        );
        stmt.setString(1, holderName);
        stmt.setDouble(2, balance);
        stmt.setString(3, status);
        stmt.setString(4, login);
        stmt.setInt(5, pin);
        stmt.executeUpdate();
    }

    public void deleteAccount(int accountNumber) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM accounts WHERE accountNumber = ?");
        stmt.setInt(1, accountNumber);
        stmt.executeUpdate();
    }

    public void updateAccount(int accountNumber, String newHolderName, String newStatus) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE accounts SET holderName = ?, status = ? WHERE accountNumber = ?"
        );
        stmt.setString(1, newHolderName);
        stmt.setString(2, newStatus);
        stmt.setInt(3, accountNumber);
        stmt.executeUpdate();
    }

    public Account searchAccount(int accountNumber) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM accounts WHERE accountNumber = ?");
        stmt.setInt(1, accountNumber);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Account(
                    rs.getInt("accountNumber"),
                    rs.getString("holderName"),
                    rs.getDouble("balance"),
                    rs.getString("status"),
                    rs.getString("login"),
                    rs.getInt("pin")
            );
        }
        return null;
    }

    public Admin getAdminByLogin(String login, int pin) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM admins WHERE login = ? AND pin = ?");
        stmt.setString(1, login);
        stmt.setInt(2, pin);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Admin(rs.getString("login"), rs.getInt("pin"));
        }
        return null;
    }
}
