package com.ledgerco.commad;

import java.math.BigDecimal;

import com.ledgerco.model.Loan;

public interface Command<E> {
	
	public void createLoan(String bankName, String borrowerName, BigDecimal principal, int noOfYears, BigDecimal rate);
	public Loan find(String bankName, String borrowerName);
	public void payment(String bankName, String borrowerName, BigDecimal payment, int emiNo) ;
	public void showBalance(String bankName, String borrowerName, int emiNo);
}
