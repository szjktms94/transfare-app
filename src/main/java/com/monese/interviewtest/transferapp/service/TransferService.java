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
    TransferRepository transferRepository;

    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Transactional()
    public String transferMoney(Transfer transfer) {
        double sourceAccountBalance = this.getBalance(transfer.getSourceAccountId());
        if((sourceAccountBalance - transfer.getAmount()) >= 0) {
            transfer.setTransactionTs(LocalDateTime.now());
            transferRepository.save(transfer);
            return "Success";
        }
        return "User does not have enough money";
    }

    @Transactional(readOnly = true)
    public double getBalance(int accountId) {
        List<Transfer> transferList = getTransfers(accountId);

        double currentBalance = 0;

        for (Transfer t : transferList) {
                if(t.getSourceAccountId() == accountId) {
                    currentBalance = currentBalance - t.getAmount();
                } else {
                currentBalance = currentBalance + t.getAmount();
                }
        }

       return currentBalance;
    }

    @Transactional(readOnly = true)
    public Statement getStatement(int accountId) {
        List<Transfer> transferList = getTransfers(accountId);

        Statement accountStatement = new Statement();
        accountStatement.setCurrentBalance(this.getBalance(accountId));
        accountStatement.setTransferList(transferList);

        return accountStatement;
    }

    private List<Transfer> getTransfers(int accountId) {
        Stream<Transfer> transferStream = transferRepository.findTransfersByAccountId(accountId);
        return transferStream.collect(Collectors.toList());
    }
}
