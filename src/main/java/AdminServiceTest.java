import org.junit.jupiter.api.*;
import static org.mockito.Mockito.*;

public class AdminServiceTest {

    private AdminRepository repo;
    private AdminService service;

    @BeforeEach
    void setUp() {
        repo = mock(AdminRepository.class);
        service = new AdminService(repo);
    }

    @Test
    void testCreateAccountSuccess() throws Exception {
        service.createAccount("Jane", 2000, "Active", "jane123", 5678);
        verify(repo).createAccount("Jane", 2000, "Active", "jane123", 5678);
    }

    @Test
    void testDeleteAccountSuccess() throws Exception {
        service.deleteAccount(1);
        verify(repo).deleteAccount(1);
    }

    @Test
    void testUpdateAccountSuccess() throws Exception {
        service.updateAccount(1, "Jane Doe", "Inactive");
        verify(repo).updateAccount(1, "Jane Doe", "Inactive");
    }

    @Test
    void testSearchAccountFound() throws Exception {
        Account acc = new Account(1, "Jane", 2000, "Active", "jane123", 5678);
        when(repo.searchAccount(1)).thenReturn(acc);
        service.searchAccount(1);
        verify(repo).searchAccount(1);
    }

    @Test
    void testSearchAccountNotFound() throws Exception {
        when(repo.searchAccount(2)).thenReturn(null);
        service.searchAccount(2);
    }
}
