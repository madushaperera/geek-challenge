package com.ledgerco.commad;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.ledgerco.model.*;

public class LoanCommandProcessor<E> implements Command<E> {

private static List<Loan> loanStore = new ArrayList<>();

	

@Override
public void createLoan(String bankName, String borrowerName, BigDecimal principal, int noOfYears, BigDecimal rate) {
    loanStore.add(new Loan(bankName, borrowerName, principal, noOfYears, rate));
}

@Override
public Loan find(String bankName, String borrowerName) {
    for (Loan l : loanStore) {
        if (l.getBankName().equals(bankName) && l.getBorrowerName().equals(borrowerName)) {
            return l;
        }
    }
    return null;
}

@Override
public void payment(String bankName, String borrowerName, BigDecimal payment, int emiNo) {
    Loan l = find(bankName, borrowerName);
    if(l != null) {
    	l.addLumSumPayment(emiNo, payment); 
    	}else {
    		System.out.println("No Records Found for given Customer: "+borrowerName +" in " + bankName +" bank." );
    	}
    
}

@Override
public void showBalance(String bankName, String borrowerName, int emiNo) {
        Loan l = find(bankName, borrowerName);
        if(l != null) {
        BigDecimal amountPaid =  l.getEMiValue().multiply(BigDecimal.valueOf(emiNo));
        BigDecimal totalLumSum = findTotalLumSumPayments(l, emiNo);
        BigDecimal totalAmountPaid = amountPaid.add(totalLumSum);
        BigDecimal outstanding = l.getAmountToRepay().subtract(totalAmountPaid);

        BigDecimal emiLeft = outstanding.divide(l.getEMiValue(), 0, RoundingMode.UP);
        
        System.out.println(bankName + " " + borrowerName + " " + totalAmountPaid + " " + emiLeft);
        }else {
    		System.out.println("No Records Found for given Customer: "+borrowerName +" in " + bankName +" bank." );
    	}
}

private BigDecimal findTotalLumSumPayments(Loan loan, int emiNo) {
   List<LumSumPayment> payments =  loan.getLumSumPayments();

    return payments.stream().filter(p -> {
        return p.getEmiNo() <= emiNo;
    }).map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
}


}






