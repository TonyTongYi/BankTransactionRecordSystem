import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 银行交易记录系统
 *
 * 功能：
 * 1. 使用数组储存 5 位客户交易资料。
 * 2. 输入 Customer ID、Customer Type、Account Number、Transaction Amount 和 Transaction Type。
 * 3. 显示全部客户交易记录。
 *
 * 本程序只使用基础 Java：变量、Scanner、if/else、switch、循环、数组、类、对象、方法、继承与多态。
 * 客户等级（Standard、Premium、Corporate）通过 Customer 的子类实现，
 * 新增客户类型时只需新增子类并在 createCustomer 中注册，不需要修改其余业务逻辑。
 */
public class BankTransactionRecordSystem {
    private static final int CUSTOMER_COUNT = 5;
    private static final String CUSTOMER_ID_PATTERN = "^[A-Za-z]\\d{3}$";
    private static final String ACCOUNT_NUMBER_PATTERN = "^\\d(?:[ -]?\\d){7,17}$";
    private static final String AMOUNT_PATTERN = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$";
    private static final String TRANSACTION_TYPE_PATTERN = "^[1-4]$";
    private static final String CUSTOMER_TYPE_PATTERN = "^[1-3]$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankTransaction[] transactions = new BankTransaction[CUSTOMER_COUNT];

        try {
            printTitle();

            // 循环输入 5 位客户的交易资料。
            for (int i = 0; i < transactions.length; i++) {
                System.out.println();
                System.out.println("Enter transaction information for customer " + (i + 1));
                System.out.println("----------------------------------------");

                String customerId = readUniqueCustomerId(scanner, transactions, i);
                Customer customer = readCustomerType(scanner, customerId);
                String accountNumber = readAccountNumber(scanner);
                TransactionType transactionType = readTransactionType(scanner, i > 0);
                double availableBalance = customer.getAvailableBalance();
                double transactionAmount = readTransactionAmount(scanner, transactionType, availableBalance);
                String transferTargetAccount = null;

                if (transactionType == TransactionType.TRANSFER) {
                    transferTargetAccount = readTransferTargetAccount(scanner, transactions, i, accountNumber);
                }

                // 创建对象，并把对象存入数组。
                transactions[i] = new BankTransaction(
                        customer,
                        accountNumber,
                        transactionAmount,
                        transactionType,
                        transferTargetAccount
                );

                if (transferTargetAccount != null) {
                    BankTransaction recipient = findTransactionByAccountNumber(
                            transferTargetAccount,
                            transactions,
                            i
                    );
                    recipient.addToBalance(transactionAmount);
                }

                System.out.println("Transaction record saved successfully.");

                if (transferTargetAccount != null) {
                    BankTransaction recipient = findTransactionByAccountNumber(
                            transferTargetAccount,
                            transactions,
                            i
                    );
                    System.out.printf("Sender account balance   : %.2f%n", transactions[i].getAccountBalance());
                    System.out.printf("Recipient account balance: %.2f%n", recipient.getAccountBalance());
                } else {
                    System.out.printf("Current account balance: %.2f%n", transactions[i].getAccountBalance());
                }
            }

            // 显示数组中的全部交易记录。
            displayAllTransactions(transactions);
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Input ended unexpectedly. Please provide complete information for all "
                    + CUSTOMER_COUNT + " customers.");
        } catch (IllegalStateException e) {
            System.out.println();
            System.out.println("Input source is closed unexpectedly. Program terminated.");
        } catch (Exception e) {
            System.out.println();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
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

    public static String readUniqueCustomerId(Scanner scanner,
                                              BankTransaction[] transactions,
                                              int currentIndex) {
        while (true) {
            String customerId = readValidatedText(
                    scanner,
                    "Customer ID: ",
                    CUSTOMER_ID_PATTERN,
                    "Invalid Customer ID. Use 1 letter followed by 3 digits, for example: C001."
            );

            if (isDuplicateCustomerId(customerId, transactions, currentIndex)) {
                System.out.println("Duplicate Customer ID. Please enter a different ID.");
            } else {
                return customerId;
            }
        }
    }

    public static boolean isDuplicateCustomerId(String customerId,
                                                BankTransaction[] transactions,
                                                int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
            if (transactions[i] != null && transactions[i].getCustomerId().equalsIgnoreCase(customerId)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 通过菜单读取客户等级，并利用工厂方法创建对应的 Customer 子类对象。
     * 新增客户类型时，只需新增一个 Customer 子类并在 createCustomer 中注册一个分支，
     * 其余读取、校验、交易和显示逻辑都不需要修改。
     */
    public static Customer readCustomerType(Scanner scanner, String customerId) {
        while (true) {
            System.out.println("Customer Type:");
            System.out.println("1. Standard");
            System.out.println("2. Premium");
            System.out.println("3. Corporate");
            System.out.print("Choose 1-3: ");
            String input = scanner.nextLine().trim();

            if (!input.matches(CUSTOMER_TYPE_PATTERN)) {
                System.out.println("Invalid choice. Please enter an integer from 1 to 3.");
                continue;
            }

            return createCustomer(input, customerId);
        }
    }

    public static Customer createCustomer(String customerTypeChoice, String customerId) {
        switch (customerTypeChoice) {
            case "1":
                return new StandardCustomer(customerId);
            case "2":
                return new PremiumCustomer(customerId);
            default:
                return new CorporateCustomer(customerId);
        }
    }

    /**
     * 读取更接近真实场景的账户号码。
     * 允许输入 8-18 位数字，中间可包含空格或连字符，保存时会移除分隔符。
     */
    public static String readAccountNumber(Scanner scanner) {
        return readAccountNumber(scanner, "Account Number: ");
    }

    public static String readAccountNumber(Scanner scanner, String prompt) {
        String accountNumber = readValidatedText(
                scanner,
                prompt,
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

    public static double readTransactionAmount(Scanner scanner,
                                               TransactionType transactionType,
                                               double availableBalance) {
        while (true) {
            double amount = readPositiveAmount(scanner);

            if (requiresSufficientBalance(transactionType) && amount > availableBalance) {
                System.out.printf(
                        "Insufficient balance. Available balance is %.2f. Please enter a smaller amount.%n",
                        availableBalance
                );
            } else {
                return amount;
            }
        }
    }

    public static boolean requiresSufficientBalance(TransactionType transactionType) {
        return transactionType == TransactionType.WITHDRAWAL
                || transactionType == TransactionType.TRANSFER
                || transactionType == TransactionType.PAYMENT;
    }

    /**
     * 通过菜单和 switch 读取交易类型。
     */
    public static TransactionType readTransactionType(Scanner scanner, boolean transferAvailable) {
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
                    return TransactionType.DEPOSIT;
                case "2":
                    return TransactionType.WITHDRAWAL;
                case "3":
                    if (!transferAvailable) {
                        System.out.println("Transfer requires another existing account. Please choose a different transaction type.");
                        break;
                    }
                    return TransactionType.TRANSFER;
                default:
                    return TransactionType.PAYMENT;
            }
        }
    }

    public static String readTransferTargetAccount(Scanner scanner,
                                                   BankTransaction[] transactions,
                                                   int currentIndex,
                                                   String senderAccountNumber) {
        while (true) {
            String targetAccount = readAccountNumber(scanner, "Transfer To Account: ");

            if (targetAccount.equals(senderAccountNumber)) {
                System.out.println("Transfer account must be different from the sender account.");
            } else if (!accountExists(targetAccount, transactions, currentIndex)) {
                System.out.println("Target account not found. Please enter an existing account number.");
            } else {
                return targetAccount;
            }
        }
    }

    public static boolean accountExists(String accountNumber,
                                        BankTransaction[] transactions,
                                        int currentIndex) {
        return findTransactionByAccountNumber(accountNumber, transactions, currentIndex) != null;
    }

    public static BankTransaction findTransactionByAccountNumber(String accountNumber,
                                                                 BankTransaction[] transactions,
                                                                 int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
            if (transactions[i] != null && transactions[i].getAccountNumber().equals(accountNumber)) {
                return transactions[i];
            }
        }

        return null;
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
