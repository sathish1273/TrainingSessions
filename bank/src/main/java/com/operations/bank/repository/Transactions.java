package com.operations.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Transactions extends JpaRepository<Transactions, Long>{

}
