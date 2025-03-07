import java.util.Scanner;

public class MainATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Account acc1 = new Account(23, "Rajesh", 3000, "Active", "rajesh234", 023445);

        System.out.print("Enter login: ");
        String login = scanner.next();
        System.out.print("Enter Pin code: ");
        int pin = scanner.nextInt();

        if (acc1.authenticate(login, pin)) {
            System.out.println("Login Successful");
            acc1.displayBalance();
        } else {
            System.out.println("Invalid Credentials");
        }

        scanner.close();
    }
}