import org.junit.jupiter.api.*;
import java.sql.*;
import static org.mockito.Mockito.*;

public class AccountRepositoryTest {

    private Connection conn;
    private AccountRepository repo;

    @BeforeEach
    void setUp() {
        conn = mock(Connection.class);
        repo = new AccountRepository(conn);
    }

    @Test
    void testUpdateBalance() throws Exception {
        PreparedStatement stmt = mock(PreparedStatement.class);
        when(conn.prepareStatement(anyString())).thenReturn(stmt);
        repo.updateBalance(1, 500.0);
        verify(stmt).executeUpdate();
    }
}