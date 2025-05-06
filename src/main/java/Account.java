public class Account {
    private int accountNumber;
    private String holderName;
    private double balance;
    private String status;
    private String login;
    private int pin;

    public Account(int accountNumber, String holderName, double balance, String status, String login, int pin) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.status = status;
        this.login = login;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public String getLogin() {
        return login;
    }

    public int getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
