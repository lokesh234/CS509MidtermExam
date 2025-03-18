import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/atm";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Account getAccount(String login, int pin) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM accounts WHERE login = ? AND pin = ?");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
