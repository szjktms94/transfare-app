package com.monese.interviewtest.transferapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int sourceAccountId;
    private int targetAccountId;
    private double amount;
    private LocalDateTime transactionTs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(int sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public int getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(int targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTs() {
        return transactionTs;
    }

    public void setTransactionTs(LocalDateTime transactionTs) {
        this.transactionTs = transactionTs;
    }
}
