/**
 * 客户抽象类。
 * 使用继承（Inheritance）与多态（Polymorphism）表示不同的客户等级。
 * 每个客户等级的初始余额、透支额度和名称由子类各自实现，
 * 未来新增客户类型时，只需新增一个子类，不需要修改本类或已有业务逻辑。
 */
public abstract class Customer {
    private final String customerId;
    private double accountBalance;

    protected Customer(String customerId) {
        this.customerId = customerId;
        this.accountBalance = getInitialBalance();
    }

    /**
     * 该客户等级的初始账户余额，由子类提供。
     */
    public abstract double getInitialBalance();

    /**
     * 该客户等级允许的透支额度，0 表示不允许透支，由子类提供。
     */
    public abstract double getOverdraftLimit();

    /**
     * 客户等级名称，用于显示，由子类提供。
     */
    public abstract String getCustomerType();

    public String getCustomerId() {
        return customerId;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * 当前可用余额（账户余额 + 透支额度）。
     */
    public double getAvailableBalance() {
        return accountBalance + getOverdraftLimit();
    }

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public void withdraw(double amount) {
        accountBalance -= amount;
    }
}
