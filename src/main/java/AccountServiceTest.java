import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    private Account account;
    private AccountRepository repository;
    private AccountService service;

    @BeforeEach
    void setUp() {
        account = new Account(1, "John", 1000.0, "Active", "john123", 1234);
        repository = mock(AccountRepository.class);
        service = new AccountService(account, repository);
    }

    @Test
    void testDepositPositiveAmount() throws SQLException {
        service.deposit(500);
        assertEquals(1500.0, account.getBalance());
        verify(repository).updateBalance(1, 1500.0);
    }

    @Test
    void testDepositNegativeAmount() {
        service.deposit(-100);
        assertEquals(1000.0, account.getBalance());  // no change
    }

    @Test
    void testWithdrawValidAmount() throws SQLException {
        service.withdraw(300);
        assertEquals(700.0, account.getBalance());
        verify(repository).updateBalance(1, 700.0);
    }

    @Test
    void testWithdrawInvalidAmount() {
        service.withdraw(2000); // more than balance
        assertEquals(1000.0, account.getBalance());  // no change
    }

    @Test
    void testDisplayBalance() {
        service.displayBalance();
        // just test that it runs without exceptions
    }
}