package in.ac.adit.pwj.miniproject.bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CurrentAccount extends Account {

    public CurrentAccount(String name, int accno) {
        super(name, accno, "Current");
    }

    @Override
    protected void updateAccountInFile() {
        // Implementation to update the current account in the file
        updateAccountFile();
    }

    private void updateAccountFile() {
        // Logic to read from and update the account file
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            StringBuilder fileContent = new StringBuilder();
            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (Integer.parseInt(parts[0]) == accno) {
                    found = true;
                    fileContent.append(accno).append(",").append(name).append(",").append(atype).append(",").append(balance).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            if (!found) {
                fileContent.append(accno).append(",").append(name).append(",").append(atype).append(",").append(balance).append("\n");
            }

            try (FileWriter writer = new FileWriter("accounts.txt")) {
                writer.write(fileContent.toString());
            }

        } catch (IOException e) {
            System.out.println("Error updating account file: " + e.getMessage());
        }
    }
}
