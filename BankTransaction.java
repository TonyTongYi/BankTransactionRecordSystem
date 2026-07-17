/**
 * 银行交易记录类。
 * 每个对象保存一位客户的一条交易资料。
 */
public class BankTransaction {
    private static final double DEFAULT_ACCOUNT_BALANCE = 99999.00;

    private String customerId;
    private String accountNumber;
    private double transactionAmount;
    private String transactionType;
    private String transferTargetAccount;
    private double accountBalance;

    /**
     * 构造方法：创建一条完整的银行交易记录。
     */
    public BankTransaction(String customerId, String accountNumber,
                           double transactionAmount, String transactionType,
                           String transferTargetAccount) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transferTargetAccount = transferTargetAccount;
        this.accountBalance = DEFAULT_ACCOUNT_BALANCE;
        applyTransaction();
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public static double getDefaultAccountBalance() {
        return DEFAULT_ACCOUNT_BALANCE;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void addToBalance(double amount) {
        accountBalance += amount;
    }

    private void applyTransaction() {
        switch (transactionType) {
            case "Deposit":
                accountBalance += transactionAmount;
                break;
            case "Withdrawal":
            case "Transfer":
            case "Payment":
                accountBalance -= transactionAmount;
                break;
            default:
                break;
        }
    }

    /**
     * 显示当前交易记录。
     */
    public void displayTransaction(int recordNumber) {
        System.out.println("----------------------------------------");
        System.out.println("Transaction Record No. : " + recordNumber);
        System.out.println("Customer ID           : " + customerId);
        System.out.println("Account Number        : " + accountNumber);
        System.out.printf("Transaction Amount    : %.2f%n", transactionAmount);
        System.out.println("Transaction Type      : " + transactionType);
        if (transferTargetAccount != null) {
            System.out.println("Transfer To Account   : " + transferTargetAccount);
        }
        System.out.printf("Account Balance       : %.2f%n", accountBalance);
    }
}
