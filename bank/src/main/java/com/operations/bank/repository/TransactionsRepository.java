package com.operations.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operations.bank.enity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{

}
