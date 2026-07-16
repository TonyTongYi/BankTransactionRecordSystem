/**
 * 银行交易记录类。
 * 每个对象保存一位客户的一条交易资料。
 */
public class BankTransaction {
    private String customerId;
    private String accountNumber;
    private double transactionAmount;
    private String transactionType;

    /**
     * 构造方法：创建一条完整的银行交易记录。
     */
    public BankTransaction(String customerId, String accountNumber,
                           double transactionAmount, String transactionType) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
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
    }
}
