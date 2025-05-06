public interface IAdminService {
    void createAccount(String holderName, double balance, String status, String login, int pin);
    void deleteAccount(int accountNumber);
    void updateAccount(int accountNumber, String newHolderName, String newStatus);
    void searchAccount(int accountNumber);
}