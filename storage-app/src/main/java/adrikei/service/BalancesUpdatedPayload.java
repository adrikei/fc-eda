package adrikei.service;

import java.math.BigDecimal;

public class BalancesUpdatedPayload {
    private String accountIdFrom;
    private String accountIdTo;
    private BigDecimal balanceAccountIdFrom;
    private BigDecimal balanceAccountIdTo;

    @Override
    public String toString() {
        return "BalanceUpdatedEvent{" +
                "accountIdFrom='" + accountIdFrom + '\'' +
                ", accountIdTo='" + accountIdTo + '\'' +
                ", balanceAccountIdFrom=" + balanceAccountIdFrom +
                ", balanceAccountIdTo=" + balanceAccountIdTo +
                '}';
    }

    //    public BalanceUpdatedEvent(String accountIdFrom, String accountIdTo, BigDecimal balanceAccountIdFrom, BigDecimal balanceAccountIdTo) {
//        this.accountIdFrom = accountIdFrom;
//        this.accountIdTo = accountIdTo;
//        this.balanceAccountIdFrom = balanceAccountIdFrom;
//        this.balanceAccountIdTo = balanceAccountIdTo;
//    }

    public String getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(String accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public String getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(String accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public BigDecimal getBalanceAccountIdFrom() {
        return balanceAccountIdFrom;
    }

    public void setBalanceAccountIdFrom(BigDecimal balanceAccountIdFrom) {
        this.balanceAccountIdFrom = balanceAccountIdFrom;
    }

    public BigDecimal getBalanceAccountIdTo() {
        return balanceAccountIdTo;
    }

    public void setBalanceAccountIdTo(BigDecimal balanceAccountIdTo) {
        this.balanceAccountIdTo = balanceAccountIdTo;
    }
}
