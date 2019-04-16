package com.monese.interviewtest.transferapp.repository;

import com.monese.interviewtest.transferapp.entity.TransactionHistory;
import org.springframework.data.repository.CrudRepository;

public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Integer> {
}
