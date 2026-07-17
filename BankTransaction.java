/**
 * 银行交易记录类。
 * 每个对象保存一位客户的一条交易资料。
 * 客户等级相关的余额与透支规则通过 Customer 及其子类的多态实现，
 * 本类不需要因为新增客户类型而修改。
 */
public class BankTransaction {
    private Customer customer;
    private String accountNumber;
    private double transactionAmount;
    private TransactionType transactionType;
    private String transferTargetAccount;

    /**
     * 构造方法：创建一条完整的银行交易记录。
     */
    public BankTransaction(Customer customer, String accountNumber,
                           double transactionAmount, TransactionType transactionType,
                           String transferTargetAccount) {
        this.customer = customer;
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
        this.transferTargetAccount = transferTargetAccount;
        applyTransaction();
    }

    public String getCustomerId() {
        return customer.getCustomerId();
    }

    public double getAccountBalance() {
        return customer.getAccountBalance();
    }

    public String getCustomerType() {
        return customer.getCustomerType();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void addToBalance(double amount) {
        customer.deposit(amount);
    }

    private void applyTransaction() {
        switch (transactionType) {
            case DEPOSIT:
                customer.deposit(transactionAmount);
                break;
            case WITHDRAWAL:
            case TRANSFER:
            case PAYMENT:
                customer.withdraw(transactionAmount);
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
        System.out.println("Customer ID           : " + customer.getCustomerId());
        System.out.println("Customer Type         : " + customer.getCustomerType());
        System.out.println("Account Number        : " + accountNumber);
        System.out.printf("Transaction Amount    : %.2f%n", transactionAmount);
        System.out.println("Transaction Type      : " + transactionType);
        if (transferTargetAccount != null) {
            System.out.println("Transfer To Account   : " + transferTargetAccount);
        }
        System.out.printf("Account Balance       : %.2f%n", customer.getAccountBalance());
    }
}

