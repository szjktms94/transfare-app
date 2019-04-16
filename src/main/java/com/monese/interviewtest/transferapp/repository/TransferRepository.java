package com.monese.interviewtest.transferapp.repository;

import com.monese.interviewtest.transferapp.entity.Transfer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.stream.Stream;

public interface TransferRepository extends CrudRepository<Transfer, Integer> {

    @Query("select t from Transfer t where t.sourceAccountId = :sourceAccountId OR t.targetAccountId = :sourceAccountId order by t.transactionTs desc")
    Stream<Transfer> findTransactionHistoriesByAccountId(@Param("sourceAccountId") Integer accountId);
}
