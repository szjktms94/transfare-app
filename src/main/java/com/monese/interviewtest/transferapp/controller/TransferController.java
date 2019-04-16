package com.monese.interviewtest.transferapp.controller;

import com.monese.interviewtest.transferapp.entity.Account;
import com.monese.interviewtest.transferapp.entity.Statement;
import com.monese.interviewtest.transferapp.entity.Transfer;
import com.monese.interviewtest.transferapp.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

    @Autowired
    TransferService transactionService;

    @PostMapping(value = "/createAccount")
    public String createAccount(@RequestBody Account account){
        transactionService.createAccount(account);
        return "saved";
    }

    @PostMapping(value = "/transaction")
    public String saveTransaction(@RequestBody Transfer transactionHistory){
        return transactionService.transferMoney(transactionHistory);
    }

    @RequestMapping(value = "/getStatement",  method=RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody
    Statement getStatement(Integer accountId){
        return transactionService.getStatement(accountId);
    }

    @GetMapping(value = "/getBalance")
    public double getBalance(Integer accountId){
        return transactionService.getBalance(accountId);
    }
}
