package sample;

import java.io.*;
import java.util.*;

class MinimumBalanceException extends Exception {
    public MinimumBalanceException(String msg) {
        super(msg);
    }
}

class InvalidCIDException extends Exception {
    public InvalidCIDException(String msg) {
        super(msg);
    }
}

class NegativeAmountException extends Exception {
    public NegativeAmountException(String msg) {
        super(msg);
    }
}

class Customer implements Serializable {
    int cid;
    String cname;
    double amount;

    public Customer(int cid, String cname, double amount) {
        this.cid = cid;
        this.cname = cname;
        this.amount = amount;
    }

    public void display() {
        System.out.println("CID: " + cid + " Name: " + cname + " Balance: " + amount);
    }
}

public class Banking_system {

    static final String FILE_NAME = "customers.dat";

    static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }

    public static void createAccount() {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter CID (1-20): ");
            int cid = sc.nextInt();

            if (cid < 1 || cid > 20) {
                throw new InvalidCIDException("CID must be between 1 and 20");
            }

            System.out.print("Enter Name: ");
            String name = sc.next();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            if (amount < 0) {
                throw new NegativeAmountException("Amount must be positive");
            }

            if (amount < 1000) {
                throw new MinimumBalanceException("Minimum balance is 1000");
            }

            Customer c = new Customer(cid, name, amount);

            FileOutputStream fos = new FileOutputStream(FILE_NAME, true);
            ObjectOutputStream oos;

            if (new File(FILE_NAME).length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new AppendableObjectOutputStream(fos);
            }

            oos.writeObject(c);
            oos.close();

            System.out.println("✅ Account Created Successfully");

        } catch (InvalidCIDException | NegativeAmountException | MinimumBalanceException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Unexpected Error: " + e);
        }
    }

    public static void displayAccounts() {
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            while (true) {
                Customer c = (Customer) ois.readObject();
                c.display();
            }

        } catch (EOFException e) {
            System.out.println("✅ End of records");
        } catch (Exception e) {
            System.out.println("❌ Error reading file");
        }
    }

    public static void withdraw() {
        ArrayList<Customer> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter CID: ");
            int id = sc.nextInt();

            System.out.print("Enter Withdrawal Amount: ");
            double wth = sc.nextDouble();

            if (wth < 0) {
                throw new NegativeAmountException("Amount must be positive");
            }

            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);

            boolean found = false;

            while (true) {
                Customer c = (Customer) ois.readObject();

                if (c.cid == id) {
                    found = true;

                    if (wth > c.amount) {
                        throw new ArithmeticException("Insufficient balance");
                    }

                    c.amount -= wth;
                }

                list.add(c);
            }

        } catch (EOFException e) {
            try {
                FileOutputStream fos = new FileOutputStream(FILE_NAME);
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                for (Customer c : list) {
                    oos.writeObject(c);
                }

                oos.close();
                System.out.println("✅ Withdrawal Successful");

            } catch (Exception ex) {
                System.out.println("❌ File Error");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- BANK MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Display Accounts");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    displayAccounts();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("❌ Invalid choice");
            }
        }
    }
}