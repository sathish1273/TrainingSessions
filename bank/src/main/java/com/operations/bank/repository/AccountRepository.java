package com.operations.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operations.bank.enity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
