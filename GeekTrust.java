package com.ledgerco;


import com.ledgerco.commad.LoanCommandProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

public class GeekTrust {

    public static void main(String[] args)
    {
        String filePath = args[0];
        processFile(filePath);
    }

    private static void processFile(String fileName) {
        LoanCommandProcessor loanCommandProcessor = new LoanCommandProcessor();

        try  {  //creates
            File file = new File(fileName);

            BufferedReader br = new BufferedReader(new FileReader(file));


            String line;

            while ((line = br.readLine()) != null) {
                String[] entries = line.split(" ");

                switch (entries[0]) {
                    case "LOAN":
                        // LOAN BANK_NAME BORROWER_NAME PRINCIPAL NO_OF_YEARS RATE_OF_INTEREST
                        loanCommandProcessor.createLoan(entries[1], entries[2], new BigDecimal(entries[3]),
                                Integer.parseInt(entries[4]), new BigDecimal(entries[5]));

                        break;
                    case "BALANCE":
                        // BALANCE BANK_NAME BORROWER_NAME EMI_NO
                        loanCommandProcessor.showBalance(entries[1], entries[2], Integer.parseInt(entries[3]));
                        break;
                    case "PAYMENT":
                        //PAYMENT BANK_NAME BORROWER_NAME LUMP_SUM_AMOUNT EMI_NO
                        loanCommandProcessor.payment(entries[1], entries[2], new BigDecimal(entries[3]), Integer.parseInt(entries[4]));
                        break;

                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}