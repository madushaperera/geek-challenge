package com.ledgerco.model;

import java.math.BigDecimal;

public class LumSumPayment {

    private final int emiNo;
    private final BigDecimal amount;

    public LumSumPayment(int emiNo, BigDecimal amount) {
        this.emiNo = emiNo;
        this.amount = amount;
    }

    public int getEmiNo() {
        return emiNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
