import java.sql.SQLException;

public class AdminService {
    private final AdminRepository repo;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }

    public void createAccount(String holderName, double balance, String status, String login, int pin) {
        try {
            repo.createAccount(holderName, balance, status, login, pin);
            System.out.println("Account Created Successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to create account: " + e.getMessage());
        }
    }

    public void deleteAccount(int accountNumber) {
        try {
            repo.deleteAccount(accountNumber);
            System.out.println("Account Deleted Successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to delete account: " + e.getMessage());
        }
    }

    public void updateAccount(int accountNumber, String newHolderName, String newStatus) {
        try {
            repo.updateAccount(accountNumber, newHolderName, newStatus);
            System.out.println("Account Updated Successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update account: " + e.getMessage());
        }
    }

    public void searchAccount(int accountNumber) {
        try {
            Account acc = repo.searchAccount(accountNumber);
            if (acc != null) {
                System.out.println("Account Found:");
                System.out.println("Number: " + acc.getAccountNumber());
                System.out.println("Holder: " + acc.getHolderName());
                System.out.println("Balance: " + acc.getBalance());
                System.out.println("Status: " + acc.getStatus());
            } else {
                System.out.println("Account not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to search account: " + e.getMessage());
        }
    }
}
