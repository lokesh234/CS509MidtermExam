import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdminRepositoryTest {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() throws Exception {
        connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        adminRepository = new AdminRepository(connection);
    }

    @Test
    void testCreateAccount() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        adminRepository.createAccount("John", 1000, "Active", "john123", 1234);
        verify(statement).executeUpdate();
    }

    @Test
    void testDeleteAccount() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        adminRepository.deleteAccount(1);
        verify(statement).executeUpdate();
    }

    @Test
    void testUpdateAccount() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        adminRepository.updateAccount(1, "John Doe", "Inactive");
        verify(statement).executeUpdate();
    }

    @Test
    void testSearchAccountFound() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("accountNumber")).thenReturn(1);
        when(resultSet.getString("holderName")).thenReturn("John");
        when(resultSet.getDouble("balance")).thenReturn(1000.0);
        when(resultSet.getString("status")).thenReturn("Active");
        when(resultSet.getString("login")).thenReturn("john123");
        when(resultSet.getInt("pin")).thenReturn(1234);

        Account account = adminRepository.searchAccount(1);
        assertNotNull(account);
        assertEquals(1, account.getAccountNumber());
        assertEquals("John", account.getHolderName());
    }

    @Test
    void testSearchAccountNotFound() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        Account account = adminRepository.searchAccount(999);
        assertNull(account);
    }

    @Test
    void testGetAdminByLoginFound() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("login")).thenReturn("admin");
        when(resultSet.getInt("pin")).thenReturn(0000);

        Admin admin = adminRepository.getAdminByLogin("admin", 0000);
        assertNotNull(admin);
        assertEquals("admin", admin.getLogin());
    }

    @Test
    void testGetAdminByLoginNotFound() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        Admin admin = adminRepository.getAdminByLogin("wrong", 9999);
        assertNull(admin);
    }
}