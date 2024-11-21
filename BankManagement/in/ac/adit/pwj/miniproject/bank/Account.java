package in.ac.adit.pwj.miniproject.bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {
    protected String name;
    protected int accno;
    protected String atype;
    protected float balance;

    protected final Lock lock = new ReentrantLock();
    private final List<String> transactions = new ArrayList<>();

    public Account(String name, int accno, String atype) {
        this.name = name;
        this.accno = accno;
        this.atype = atype;
        this.balance = 0;
    }

    public void deposit(float amount) {
        lock.lock();
        try {
            balance += amount;
            addTransaction("Deposited: " + amount);
            updateAccountInFile();
        } finally {
            lock.unlock();
        }
    }

    public void withdraw(float amount) throws Exception {
        lock.lock();
        try {
            if (amount > balance) {
                throw new Exception("Insufficient funds for withdrawal.");
            }
            balance -= amount;
            addTransaction("Withdrew: " + amount);
            updateAccountInFile();
        } finally {
            lock.unlock();
        }
    }

    public void transfer(Account targetAccount, float amount) throws Exception {
        this.withdraw(amount);
        targetAccount.deposit(amount);
        addTransaction("Transferred: " + amount + " to Account No: " + targetAccount.accno);
        targetAccount.addTransaction("Received: " + amount + " from Account No: " + this.accno);
    }

    private void addTransaction(String transaction) {
        transactions.add(transaction);
        saveTransactionToFile(transaction);
    }

    private void saveTransactionToFile(String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_history.txt", true))) {
            writer.write("Account No: " + accno + " - " + transaction);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    public void displayDetails() {
        System.out.println("\nCustomer Name: " + name);
        System.out.println("Account Number: " + accno);
        System.out.println("Account Type: " + atype);
        System.out.println("Balance: " + balance);
    }

    protected abstract void updateAccountInFile();
}
