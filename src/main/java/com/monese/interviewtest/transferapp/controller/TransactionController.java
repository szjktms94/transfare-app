package com.monese.interviewtest.transferapp.controller;

import com.monese.interviewtest.transferapp.entity.Account;
import com.monese.interviewtest.transferapp.entity.Statment;
import com.monese.interviewtest.transferapp.entity.TransactionHistory;
import com.monese.interviewtest.transferapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(value = "/")
    public String check(){
        return "check";
    }

    @PostMapping(value = "/create")
    public String createAccount(@RequestBody Account account){
        transactionService.createAccount(account);
        return "saved";
    }

    @PostMapping(value = "/transaction")
    public String saveTransaction(@RequestBody TransactionHistory transactionHistory){
        return transactionService.saveTransaction(transactionHistory);
    }

    @RequestMapping(value = "/getStatement",  method=RequestMethod.GET,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody Statment getStatement(Integer accountId){
        return transactionService.getStatement(accountId);
    }

    @GetMapping(value = "/getBalance")
    public double getBalance(Integer accountId){
        return transactionService.getBalance(accountId);
    }
}
