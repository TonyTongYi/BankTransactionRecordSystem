import java.util.Scanner;

/**
 * 银行交易记录系统
 *
 * 功能：
 * 1. 使用数组储存 5 位客户交易资料。
 * 2. 输入 Customer ID、Account Number、Transaction Amount 和 Transaction Type。
 * 3. 显示全部客户交易记录。
 *
 * 本程序只使用基础 Java：变量、Scanner、if/else、switch、循环、数组、类、对象和方法。
 */
public class BankTransactionRecordSystem {
    private static final int CUSTOMER_COUNT = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankTransaction[] transactions = new BankTransaction[CUSTOMER_COUNT];

        printTitle();

        // 循环输入 5 位客户的交易资料。
        for (int i = 0; i < transactions.length; i++) {
            System.out.println();
            System.out.println("Enter transaction information for customer " + (i + 1));
            System.out.println("----------------------------------------");

            String customerId = readRequiredText(scanner, "Customer ID: ");
            String accountNumber = readRequiredText(scanner, "Account Number: ");
            double transactionAmount = readPositiveAmount(scanner);
            String transactionType = readTransactionType(scanner);

            // 创建对象，并把对象存入数组。
            transactions[i] = new BankTransaction(
                    customerId,
                    accountNumber,
                    transactionAmount,
                    transactionType
            );

            System.out.println("Transaction record saved successfully.");
        }

        // 显示数组中的全部交易记录。
        displayAllTransactions(transactions);
        scanner.close();
    }

    /**
     * 显示系统标题。
     */
    public static void printTitle() {
        System.out.println("========================================");
        System.out.println(" ABC Bank Transaction Record System");
        System.out.println(" Group 7");
        System.out.println("========================================");
    }

    /**
     * 读取不能为空的文字，例如客户编号和账户号码。
     */
    public static String readRequiredText(Scanner scanner, String prompt) {
        String value;

        do {
            System.out.print(prompt);
            value = scanner.nextLine();

            if (value.length() == 0) {
                System.out.println("Input cannot be empty. Please enter again.");
            }
        } while (value.length() == 0);

        return value;
    }

    /**
     * 读取大于 0 的交易金额，并处理非数字输入。
     */
    public static double readPositiveAmount(Scanner scanner) {
        double amount = 0;
        boolean validAmount = false;

        while (!validAmount) {
            System.out.print("Transaction Amount: ");

            if (scanner.hasNextDouble()) {
                amount = scanner.nextDouble();
                scanner.nextLine(); // 清除输入行末尾的换行符

                if (amount > 0) {
                    validAmount = true;
                } else {
                    System.out.println("Amount must be greater than 0.");
                }
            } else {
                System.out.println("Invalid amount. Please enter a number.");
                scanner.nextLine(); // 丢弃错误输入
            }
        }

        return amount;
    }

    /**
     * 通过菜单和 switch 读取交易类型。
     */
    public static String readTransactionType(Scanner scanner) {
        int choice = 0;
        String transactionType = "";
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Transaction Type:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Transfer");
            System.out.println("4. Payment");
            System.out.print("Choose 1-4: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // 清除输入行末尾的换行符

                switch (choice) {
                    case 1:
                        transactionType = "Deposit";
                        validChoice = true;
                        break;
                    case 2:
                        transactionType = "Withdrawal";
                        validChoice = true;
                        break;
                    case 3:
                        transactionType = "Transfer";
                        validChoice = true;
                        break;
                    case 4:
                        transactionType = "Payment";
                        validChoice = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please choose from 1 to 4.");
                }
            } else {
                System.out.println("Invalid choice. Please enter an integer from 1 to 4.");
                scanner.nextLine(); // 丢弃错误输入
            }
        }

        return transactionType;
    }

    /**
     * 遍历数组并显示全部交易记录。
     */
    public static void displayAllTransactions(BankTransaction[] transactions) {
        System.out.println();
        System.out.println("========================================");
        System.out.println(" All Bank Transaction Records");
        System.out.println("========================================");

        for (int i = 0; i < transactions.length; i++) {
            transactions[i].displayTransaction(i + 1);
        }

        System.out.println("----------------------------------------");
        System.out.println("Total records: " + transactions.length);
    }
}
