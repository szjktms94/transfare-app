package com.monese.interviewtest.transferapp.repository;

import com.monese.interviewtest.transferapp.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}