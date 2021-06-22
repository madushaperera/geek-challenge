package com.ledgerco.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Loan {

    private final String bankName;
    private final String borrowerName;
    private final BigDecimal principal;
    private final int noOfYears;
    private final BigDecimal rate;
    private BigDecimal amountToRepay;
    private BigDecimal eMiValue;

    private List<LumSumPayment> lumSumPaymentList;

    public Loan(String bankName, String borrowerName, BigDecimal principal, int noOfYears, BigDecimal rate) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.principal = principal;
        this.noOfYears = noOfYears;
        this.rate = rate;
        lumSumPaymentList = new ArrayList<>();
        amountToRepay =
                principal.add(principal.multiply(new BigDecimal(noOfYears).multiply(rate.divide(new BigDecimal(100)))));
        eMiValue = amountToRepay.divide(new BigDecimal(noOfYears * 12), 0, RoundingMode.UP);
    }

    public String getBankName() {
        return bankName;
    }


    public String getBorrowerName() {
        return borrowerName;
    }


    public BigDecimal getPrincipal() {
        return principal;
    }


    public int getNoOfYears() {
        return noOfYears;
    }


    public BigDecimal getRate() {
        return rate;
    }

    public BigDecimal getAmountToRepay() {
        return amountToRepay;
    }

    public void setAmountToRepay(BigDecimal amountToRepay) {
        this.amountToRepay = amountToRepay;
    }

    public BigDecimal getEMiValue() {
        return eMiValue;
    }

    public void setEMiValue(BigDecimal noEMiValue) {
        this.eMiValue = noEMiValue;
    }

    public void addLumSumPayment(int emiNo, BigDecimal payment) {
        lumSumPaymentList.add(new LumSumPayment(emiNo, payment));
    }

    public List<LumSumPayment> getLumSumPayments() {
        Collections.sort(lumSumPaymentList, new Comparator<LumSumPayment>() {
            @Override
            public int compare(LumSumPayment o1, LumSumPayment o2) {
                return o1.getEmiNo() < o2.getEmiNo() ? -1 : o1.getEmiNo() == o2.getEmiNo() ? 0 : 1;
            }
        });

        return lumSumPaymentList;
    }
}
