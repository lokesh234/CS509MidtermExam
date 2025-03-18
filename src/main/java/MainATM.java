import java.util.Scanner;

public class MainATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database db = new Database();
        Admin admin = new Admin(db.getConnection());

        System.out.print("Enter login: ");
        String login = scanner.next();
        System.out.print("Enter Pin code: ");
        int pin = scanner.nextInt();
        scanner.nextLine();

        if (login.equals("admin") && pin == 0000) {
            boolean exit = false;
            while (!exit) {
                System.out.println("1----Create New Account\n2----Delete Existing Account\n3----Update Account Information\n4----Search for Account\n6----Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Holder Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double balance = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Enter Status (Active/Inactive): ");
                        String status = scanner.nextLine();
                        System.out.print("Enter Login: ");
                        String newLogin = scanner.nextLine();
                        System.out.print("Enter PIN: ");
                        int newPin = scanner.nextInt();
                        admin.createAccount(name, balance, status, newLogin, newPin);
                        break;
                    case 2:
                        System.out.print("Enter Account Number to Delete: ");
                        int accNum = scanner.nextInt();
                        admin.deleteAccount(accNum);
                        break;
                    case 3:
                        System.out.print("Enter Account Number to Update: ");
                        int updateAcc = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter New Holder Name: ");
                        String newHolderName = scanner.nextLine();
                        System.out.print("Enter New Status (Active/Inactive): ");
                        String newStatus = scanner.nextLine();
                        admin.updateAccount(updateAcc, newHolderName, newStatus);
                        break;
                    case 4:
                        System.out.print("Enter Account Number to Search: ");
                        int searchAcc = scanner.nextInt();
                        admin.searchAccount(searchAcc);
                        break;
                    case 6:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            Account user = db.getAccount(login, pin);
            if (user != null) {
                boolean exit = false;
                while (!exit) {
                    System.out.println("1----Withdraw Cash\n3----Deposit Cash\n4----Display Balance\n5----Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawAmount = scanner.nextDouble();
                            user.withdraw(withdrawAmount, db.getConnection());
                            break;
                        case 3:
                            System.out.print("Enter the cash amount to deposit: ");
                            double depositAmount = scanner.nextDouble();
                            user.deposit(depositAmount, db.getConnection());
                            break;
                        case 4:
                            user.displayBalance();
                            break;
                        case 5:
                            exit = true;
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            } else {
                System.out.println("Invalid Credentials");
            }
        }

        scanner.close();
    }
}