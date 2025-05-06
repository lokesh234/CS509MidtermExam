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
}
