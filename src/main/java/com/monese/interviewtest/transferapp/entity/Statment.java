package com.monese.interviewtest.transferapp.entity;

import javax.persistence.Entity;
import java.util.List;

public class Statment {

    private double currentBalance;
    private List<TransactionHistory> listOfTransactions;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<TransactionHistory> getListOfTransactions() {
        return listOfTransactions;
    }

    public void setListOfTransactions(List<TransactionHistory> listOfTransactions) {
        this.listOfTransactions = listOfTransactions;
    }
}
