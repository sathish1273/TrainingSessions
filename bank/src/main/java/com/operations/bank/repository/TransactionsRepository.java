package com.operations.bank.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operations.bank.enity.Transactions;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, Long>{

	List<Transactions> findByDateBetween(LocalDate from,LocalDate to);
	List<Transactions> findByFromAccountOrToAccountAndDateBetween(long fromAccount,long toAccount,LocalDateTime startDate, LocalDateTime endDate);
}
