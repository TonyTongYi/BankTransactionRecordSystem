import java.util.Scanner;

/**
 * 第七组：银行交易记录系统
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
    private static final String CUSTOMER_ID_PATTERN = "^[A-Za-z]\\d{3}$";
    private static final String ACCOUNT_NUMBER_PATTERN = "^\\d(?:[ -]?\\d){7,17}$";
    private static final String AMOUNT_PATTERN = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$";
    private static final String TRANSACTION_TYPE_PATTERN = "^[1-4]$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankTransaction[] transactions = new BankTransaction[CUSTOMER_COUNT];

        printTitle();

        // 循环输入 5 位客户的交易资料。
        for (int i = 0; i < transactions.length; i++) {
            System.out.println();
            System.out.println("Enter transaction information for customer " + (i + 1));
            System.out.println("----------------------------------------");

            String customerId = readValidatedText(
                    scanner,
                    "Customer ID: ",
                    CUSTOMER_ID_PATTERN,
                    "Invalid Customer ID. Use 1 letter followed by 3 digits, for example: C001."
            );
                String accountNumber = readAccountNumber(scanner);
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
    public static String readValidatedText(Scanner scanner, String prompt,
                                           String pattern, String errorMessage) {
        String value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();

            if (value.length() == 0) {
                System.out.println("Input cannot be empty. Please enter again.");
            } else if (!value.matches(pattern)) {
                System.out.println(errorMessage);
            } else {
                return value;
            }
        }
    }

    /**
     * 读取更接近真实场景的账户号码。
     * 允许输入 8-18 位数字，中间可包含空格或连字符，保存时会移除分隔符。
     */
    public static String readAccountNumber(Scanner scanner) {
        String accountNumber = readValidatedText(
                scanner,
                "Account Number: ",
                ACCOUNT_NUMBER_PATTERN,
                "Invalid Account Number. Use 8-18 digits, and you may separate digits with spaces or hyphens."
        );

        return accountNumber.replaceAll("[ -]", "");
    }

    /**
     * 读取大于 0 的交易金额，并处理非数字输入。
     */
    public static double readPositiveAmount(Scanner scanner) {
        while (true) {
            System.out.print("Transaction Amount: ");
            String input = scanner.nextLine().trim();

            if (!input.matches(AMOUNT_PATTERN)) {
                System.out.println("Invalid amount. Please enter a positive number with up to 2 decimal places.");
                continue;
            }

            double amount = Double.parseDouble(input);

            if (amount > 0) {
                return amount;
            } else {
                System.out.println("Amount must be greater than 0.");
            }
        }
    }

    /**
     * 通过菜单和 switch 读取交易类型。
     */
    public static String readTransactionType(Scanner scanner) {
        while (true) {
            System.out.println("Transaction Type:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Transfer");
            System.out.println("4. Payment");
            System.out.print("Choose 1-4: ");
            String input = scanner.nextLine().trim();

            if (!input.matches(TRANSACTION_TYPE_PATTERN)) {
                System.out.println("Invalid choice. Please enter an integer from 1 to 4.");
                continue;
            }

            switch (input) {
                case "1":
                    return "Deposit";
                case "2":
                    return "Withdrawal";
                case "3":
                    return "Transfer";
                default:
                    return "Payment";
            }
        }
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
