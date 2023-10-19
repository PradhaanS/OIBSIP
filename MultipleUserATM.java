import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultipleUserATM {

    public static class User {
        private String username;
        private int pin;
        private double balance;
        private List<String> transactionHistory;

        public User(String username, int pin) {
            this.username = username;
            this.pin = pin;
            this.balance = 1000.00; // Initial balance
            this.transactionHistory = new ArrayList<>();
        }

        public String getUsername() {
            return username;
        }

        public int getPin() {
            return pin;
        }

        public double getBalance() {
            return balance;
        }

        public List<String> getTransactionHistory() {
            return transactionHistory;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                transactionHistory.add("Deposit: $" + amount);
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactionHistory.add("Withdraw: $" + amount);
                return true;
            }
            return false;
        }

        public void transfer(User recipient, double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                recipient.deposit(amount);
                transactionHistory.add("Transfer to " + recipient.getUsername() + ": $" + amount);
            }
        }
    }

    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Create some user accounts
        User user1 = new User("user1", 1234);
        User user2 = new User("user2", 5678);
        users.add(user1);
        users.add(user2);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter PIN: ");
            int pin = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            User currentUser = authenticateUser(username, pin);

            if (currentUser != null) {
                performTransactions(scanner, currentUser);
            } else {
                System.out.println("Authentication failed. Please try again.");
            }
        }
    }

    private static User authenticateUser(String username, int pin) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPin() == pin) {
                return user;
            }
        }
        return null;
    }

    private static void performTransactions(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nWelcome, " + user.getUsername() + "!");
            System.out.println("ATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Transaction History");
            System.out.println("3. Withdraw");
            System.out.println("4. Deposit");
            System.out.println("5. Transfer");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.println("Your balance: $" + user.getBalance());
                    break;
                case 2:
                    System.out.println("Transaction History:");
                    for (String transaction : user.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 3:
                    System.out.print("Enter the amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    boolean withdrawSuccess = user.withdraw(withdrawAmount);
                    if (withdrawSuccess) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Invalid withdrawal amount or insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.print("Enter the amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    user.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 5:
                    System.out.print("Enter the recipient's username: ");
                    String recipientUsername = scanner.nextLine();
                    System.out.print("Enter the amount to transfer: $");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    User recipient = findUserByUsername(recipientUsername);
                    if (recipient != null) {
                        user.transfer(recipient, transferAmount);
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Recipient not found.");
                    }
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
