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

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Cash Deposited Successfully. New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Cash Withdrawn Successfully. New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }
}