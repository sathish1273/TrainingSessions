package com.operations.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operations.bank.enity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findByFnameAndLnameAndPhoneNo(String fname,String lname,long phoneNo);

}
