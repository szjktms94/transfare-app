package com.monese.interviewtest.transferapp.service;

import com.monese.interviewtest.transferapp.entity.Account;
import com.monese.interviewtest.transferapp.entity.Statment;
import com.monese.interviewtest.transferapp.entity.TransactionHistory;
import com.monese.interviewtest.transferapp.repository.AccountRepository;
import com.monese.interviewtest.transferapp.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionHistoryRepository transactionRepository;

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    //top upot readme
    public String saveTransaction(TransactionHistory transactionHistory) {
        double sourceAccountBalance = this.getBalance(transactionHistory.getSourceAccountId());
        if((sourceAccountBalance - transactionHistory.getAmount()) >= 0) {
            transactionHistory.setTransactionTs(LocalDateTime.now());
            transactionRepository.save(transactionHistory);
            return "Success";
        }
        return "User does not have enough money";

    }

    public double getBalance(int accountId) {
        double currentBalance = 0;

        Iterable<TransactionHistory> transactionList = transactionRepository.findAll();

        for (TransactionHistory t : transactionList) {
            if (t.getSourceAccountId() == accountId || t.getTargetAccountId() == accountId) {
                if(t.getSourceAccountId() == accountId) {
                    currentBalance = currentBalance - t.getAmount();
                } else {
                    currentBalance = currentBalance + t.getAmount();
                }
            }
        }
       return currentBalance;
    }

    public Statment getStatement(int accountId) {
        Iterable<TransactionHistory> allTransactions = transactionRepository.findAll();
        List accountTransactionList = new ArrayList<TransactionHistory>();

        Statment statment = new Statment();
        statment.setCurrentBalance(this.getBalance(accountId));

        //use filted or any other solution
        for(TransactionHistory t : allTransactions) {
            if(t.getSourceAccountId() == accountId || t.getTargetAccountId() == accountId) {
                accountTransactionList.add(t);
            }
        }
        statment.setListOfTransactions(accountTransactionList);

        return statment;
    }
}
