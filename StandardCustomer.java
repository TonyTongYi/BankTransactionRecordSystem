/**
 * 标准客户：无透支额度，初始余额为基础金额。
 */
public class StandardCustomer extends Customer {
    private static final double INITIAL_BALANCE = 99999.00;
    private static final double OVERDRAFT_LIMIT = 0.00;

    public StandardCustomer(String customerId) {
        super(customerId);
    }

    @Override
    public double getInitialBalance() {
        return INITIAL_BALANCE;
    }

    @Override
    public double getOverdraftLimit() {
        return OVERDRAFT_LIMIT;
    }

    @Override
    public String getCustomerType() {
        return "Standard";
    }
}
