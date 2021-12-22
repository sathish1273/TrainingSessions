package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {

}
