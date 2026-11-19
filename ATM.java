import java.util.Scanner;

public class ATM {
    public static void atm(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final int PIN = 2002;
        int attempts = 0;
        int maxAttempts = 5;
        boolean loggedIn = false;

        // PIN authentication
        while (attempts < maxAttempts) {
            System.out.print("Enter PIN: ");
            int enteredPin = scanner.nextInt();
            attempts++;

            if (enteredPin == PIN) {
                loggedIn = true;
                break;
            } else if (attempts < maxAttempts) {
                System.out.println("Wrong PIN. Attempts left: " + (maxAttempts - attempts));
            }
        }

        if (!loggedIn) {
            System.out.println("Too many failed attempts. Exiting.");
            scanner.close();
            return;
        }

        double balance = 0.0;
        String[] transactions = new String[100];
        int txCount = 0;
        int choice;

        // Main menu loop
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Current balance: $%.2f%n", balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        if (txCount < transactions.length) {
                            transactions[txCount++] = String.format("Deposited: $%.2f", deposit);
                        }
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdraw = scanner.nextDouble();
                    if (withdraw <= 0) {
                        System.out.println("Invalid amount.");
                    } else if (withdraw <= balance) {
                        balance -= withdraw;
                        if (txCount < transactions.length) {
                            transactions[txCount++] = String.format("Withdrew: $%.2f", withdraw);
                        }
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Transaction History ---");
                    if (txCount == 0) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (int i = 0; i < txCount; i++) {
                            System.out.println((i + 1) + ". " + transactions[i]);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
