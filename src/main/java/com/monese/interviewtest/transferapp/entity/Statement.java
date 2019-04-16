package com.monese.interviewtest.transferapp.entity;

import java.util.List;

public class Statement {

    private double currentBalance;
    private List<Transfer> transferList;

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }
}
