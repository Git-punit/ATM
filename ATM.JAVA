import java.util.HashMap;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int inputPin) {
        return pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
}

public class ATM {
    private static HashMap<Integer, Account> accounts = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with one test account
        accounts.put(1234, new Account(1234, 1111, 1000.0));

        System.out.println("Welcome to the ATM");
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();

        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        Account acc = accounts.get(accNo);

        if (acc != null && acc.validatePin(pin)) {
            System.out.println("Login successful!\n");

            int choice;
            do {
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: $" + acc.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double dep = scanner.nextDouble();
                        acc.deposit(dep);
                        System.out.println("Deposited $" + dep);
                        break;
                    case 3:
                        System.out.print("Enter withdraw amount: ");
                        double wd = scanner.nextDouble();
                        if (acc.withdraw(wd)) {
                            System.out.println("Withdrew $" + wd);
                        } else {
                            System.out.println("Insufficient balance!");
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM!");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
                System.out.println();
            } while (choice != 4);

        } else {
            System.out.println("Invalid account number or PIN.");
        }
    }
}
