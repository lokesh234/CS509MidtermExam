import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountService implements IAccountService {
    private final Account account;
    private final AccountRepository repository;

    public AccountService(Account account, AccountRepository repository) {
        this.account = account;
        this.repository = repository;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        account.setBalance(account.getBalance() + amount);
        try {
            repository.updateBalance(account.getAccountNumber(), account.getBalance());
            print("Deposited", amount);
        } catch (SQLException e) {
            System.out.println("Failed to deposit: " + e.getMessage());
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0 || amount > account.getBalance()) {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
            return;
        }
        account.setBalance(account.getBalance() - amount);
        try {
            repository.updateBalance(account.getAccountNumber(), account.getBalance());
            print("Withdrawn", amount);
        } catch (SQLException e) {
            System.out.println("Failed to withdraw: " + e.getMessage());
        }
    }

    @Override
    public void displayBalance() {
        print("Balance", account.getBalance());
    }

    private void print(String label, double amount) {
        String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
        System.out.println("\nAccount #" + account.getAccountNumber());
        System.out.println("Date: " + date);
        System.out.println(label + ": " + amount);
        System.out.println("Balance: " + account.getBalance() + "\n");
    }
}
