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

    public boolean authenticate(String login, int pin) {
        return this.login.equals(login) && this.pin == pin;
    }

    public void displayBalance() {
        System.out.println("Balance: " + balance);
    }
}