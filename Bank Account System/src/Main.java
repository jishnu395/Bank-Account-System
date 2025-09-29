import java.util.Scanner;
import java.util.ArrayList;


class BankAccount {
    private String accHolder;
    private double balance;
    protected ArrayList<String> transactionHistory;

    public BankAccount(String accHolder, double initialDeposit) {
        this.accHolder = accHolder;
        this.balance = initialDeposit;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account Created | Initial Balance: ₹" + initialDeposit);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("₹" + amount + " withdrawn successfully");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void showTransactionHistory() {
        System.out.println("----------------------------------------------------");
        System.out.println("Transaction History: ");
        for (String record : transactionHistory) {
            System.out.println("-> " + record);
        }
        System.out.println("----------------------------------------------------");
    }

    public void showAccountDetails() {
        System.out.println("-----------------------------");
        System.out.println("Account Holder: " + accHolder);
        System.out.println("Current Balance: ₹" + balance);
        System.out.println("-----------------------------");
    }
}
class SavingsAccount extends BankAccount {
    private double interestRate;
    public SavingsAccount(String accHolder, double initialDeposit, double interestRate) {
        super(accHolder, initialDeposit);
        this.interestRate = interestRate;
    }
    @Override
    public void deposit(double amount) {
        double interest = amount * interestRate / 100;
        super.deposit(amount + interest);
        transactionHistory.add("Interest added:  ₹" + interest);
    }
}
public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter Account Holder' s Name: ");
        String name = s.nextLine();
        System.out.print("Enter the initial Deposit amount: ₹");
        double initialDeposit = s.nextDouble();
        System.out.print("Enter the interest rate (%) for savings account: ");
        double interestRate = s.nextDouble();

        SavingsAccount account = new SavingsAccount(name, initialDeposit, interestRate);

        int choice;

        do{
            System.out.println("\n------- Bank Menu -------");
            System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Transaction History\n5. Account Details\n6. Exit\n---------------------------");
            System.out.print("Choose an option from the menu: ");
            choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("----------------------------------");
                    System.out.print("Enter deposit amount: ₹");
                    double depositAmount = s.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("----------------------------------");
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.print("Enter the amount to be withdrawn: ₹");
                    double withdrawAmount = s.nextDouble();
                    account.withdraw(withdrawAmount);
                    System.out.println("-----------------------------------");
                    break;
                case 3:
                    System.out.println("-------------------------------------------");
                    System.out.println("Current balance: ₹" + account.getBalance());
                    System.out.println("-------------------------------------------");
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    account.showAccountDetails();
                    break;
                case 6:
                    System.out.println("\nThank You for Banking With Us!");
                    break;
                default:
                    System.out.println("Invalid Choice!");

            }
        } while(choice != 6);
        s.close();
    }

}