package com.monese.interviewtest.transferapp.service;

import com.monese.interviewtest.transferapp.entity.Account;
import com.monese.interviewtest.transferapp.entity.Statement;
import com.monese.interviewtest.transferapp.entity.Transfer;
import com.monese.interviewtest.transferapp.repository.AccountRepository;
import com.monese.interviewtest.transferapp.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransferService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferRepository transactionRepository;

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    public String transferMoney(Transfer transactionHistory) {
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

        Iterable<Transfer> transferList = transactionRepository.findAll();

        for (Transfer t : transferList) {
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

    @Transactional(readOnly = true)
    public Statement getStatement(int accountId) {
        Stream<Transfer> transferStream = transactionRepository.findTransactionHistoriesByAccountId(accountId);
        List<Transfer> transferList = transferStream.collect(Collectors.toList());

        Statement accountStatement = new Statement();
        accountStatement.setCurrentBalance(this.getBalance(accountId));
        accountStatement.setTransferList(transferList);

        return accountStatement;
    }
}
