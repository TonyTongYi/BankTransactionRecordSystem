/**
 * 企业客户：拥有最高初始余额，并享有最高透支额度。
 */
public class CorporateCustomer extends Customer {
    private static final double INITIAL_BALANCE = 999999.00;
    private static final double OVERDRAFT_LIMIT = 50000.00;

    public CorporateCustomer(String customerId) {
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
        return "Corporate";
    }
}
