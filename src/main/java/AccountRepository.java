import java.sql.*;

public class AccountRepository {
    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public void updateBalance(int accountNumber, double newBalance) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE accountNumber = ?");
        stmt.setDouble(1, newBalance);
        stmt.setInt(2, accountNumber);
        stmt.executeUpdate();
    }

    public Account getAccountByLogin(String login, int pin) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE login = ? AND pin = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setInt(2, pin);
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
}