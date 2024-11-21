package in.ac.adit.pwj.miniproject.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankManagement {
    private static final String ACCOUNT_FILE = "accounts.txt"; // File to store account details
    private static Map<Integer, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        loadAccountsFromFile(); // Load existing accounts from file
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Create New Account");
            System.out.println("2. Access Existing Account");
            System.out.println("3. Exit");
            int mainAction = sc.nextInt();

            switch (mainAction) {
                case 1: // Create New Account
                    createNewAccount(sc);
                    break;

                case 2: // Access Existing Account
                    accessExistingAccount(sc);
                    break;

                case 3: // Exit
                    exit = true;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }

        sc.close(); // Close the scanner
    }

    private static void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int accno = Integer.parseInt(parts[0]);
                String name = parts[1];
                String atype = parts[2];
                float balance = Float.parseFloat(parts[3]);

                Account account;
                if (atype.equals("Savings")) {
                    account = new SavingAccount(name, accno);
                } else {
                    account = new CurrentAccount(name, accno);
                }
                account.balance = balance; // Set balance from file
                accounts.put(accno, account); // Store in the map
            }
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    private static void createNewAccount(Scanner sc) {
        System.out.println("Choose Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();
        int accno = accounts.size() + 1; // Simple method to generate unique account number

        Account account;
        if (choice == 1) {
            account = new SavingAccount(name, accno);
        } else {
            account = new CurrentAccount(name, accno);
        }

        accounts.put(accno, account);
        saveAccountToFile(account);
        System.out.println("Account created successfully!");
    }

    private static void saveAccountToFile(Account account) {
        try (FileWriter writer = new FileWriter(ACCOUNT_FILE, true)) {
            writer.write(account.accno + "," + account.name + "," + account.atype + "," + account.balance + "\n");
        } catch (IOException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    private static void accessExistingAccount(Scanner sc) {
        System.out.print("Enter Account Number: ");
        int accno = sc.nextInt();
        Account account = accounts.get(accno);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        manageAccount(sc, account);
    }

    private static void manageAccount(Scanner sc, Account account) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer Funds");
            System.out.println("4. View Account Details");
            System.out.println("5. Logout");
            int action = sc.nextInt();

            switch (action) {
                case 1: // Deposit
                    System.out.print("Enter amount to deposit: ");
                    float depositAmount = sc.nextFloat();
                    account.deposit(depositAmount);
                    System.out.println("Deposited: " + depositAmount);
                    break;

                case 2: // Withdraw
                    System.out.print("Enter amount to withdraw: ");
                    float withdrawAmount = sc.nextFloat();
                    try {
                        account.withdraw(withdrawAmount);
                        System.out.println("Withdrew: " + withdrawAmount);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Transfer Funds
                    System.out.print("Enter target Account Number: ");
                    int targetAccno = sc.nextInt();
                    Account targetAccount = accounts.get(targetAccno);
                    if (targetAccount == null) {
                        System.out.println("Target account not found.");
                        break;
                    }
                    System.out.print("Enter amount to transfer: ");
                    float transferAmount = sc.nextFloat();
                    try {
                        account.transfer(targetAccount, transferAmount);
                        System.out.println("Transferred: " + transferAmount + " to Account No: " + targetAccno);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4: // View Account Details
                    account.displayDetails();
                    break;

                case 5: // Logout
                    exit = true;
                    System.out.println("Logging out...");
                    break;

                default:
                    System.out.println("Invalid action. Please try again.");
            }
        }
    }
}
