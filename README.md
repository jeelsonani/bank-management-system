# Bank Management System

A simple Java-based **Bank Management System** to manage savings and current accounts. It provides functionalities such as account creation, deposit, withdrawal, fund transfer, and transaction tracking, along with file-based persistence for accounts and transaction history.

---

## Features

1. **Account Management**:
   - Create new Savings or Current accounts.
   - Access and manage existing accounts.

2. **Account Operations**:
   - Deposit money.
   - Withdraw money (with balance check).
   - Transfer funds between accounts.
   - View account details.

3. **Transaction History**:
   - Logs all transactions to a file (`transaction_history.txt`).

4. **File Persistence**:
   - Stores account details in `accounts.txt`.
   - Updates account data after every transaction.

---

## Project Structure

src/in/ac/adit/pwj/miniproject/bank ├── Account.java // Abstract class for account operations ├── BankManagement.java // Main class to interact with the system ├── CurrentAccount.java // Current account-specific implementation └── SavingAccount.java // Savings account-specific implementation

---

## Setup and Execution

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/BankManagementSystem.git
   cd BankManagementSystem
2. Compile the Java files:
  javac -d . src/in/ac/adit/pwj/miniproject/bank/*.java
3. Run the application:
   java in.ac.adit.pwj.miniproject.bank.BankManagement
   
## How to Use
1. Upon running, you can choose from the following options:
  -Create New Account: Create a new Savings or Current account.
  -Access Existing Account: Perform operations on an existing account.
  -Exit: Exit the program.
2. Inside an account, you can:
  -Deposit money.
  -Withdraw money (ensures no overdraft).
  -Transfer funds to another account.
  -View account details.
  -Logout to return to the main menu.

---

## File Storage
  -accounts.txt: Stores account details in the format:
    <AccountNumber>,<Name>,<AccountType>,<Balance>
  -transaction_history.txt: Stores transaction logs for all accounts.

---

## Future Enhancements
1. Add user authentication for secure account access.
2. Implement a graphical user interface (GUI).
3. Use a database (e.g., MySQL) for persistent storage instead of files.
4. Enhance error handling and logging.

---

## Contributing
Contributions are welcome! Please fork this repository and submit a pull request with your improvements.

---

## License
This project is licensed under the MIT License. See the LICENSE file for details.

---

## Author
Developed by Jeel Sonani.


