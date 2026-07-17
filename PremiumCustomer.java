/**
 * 高级客户：拥有更高初始余额，并享有一定透支额度。
 */
public class PremiumCustomer extends Customer {
    private static final double INITIAL_BALANCE = 199999.00;
    private static final double OVERDRAFT_LIMIT = 5000.00;

    public PremiumCustomer(String customerId) {
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
        return "Premium";
    }
}
