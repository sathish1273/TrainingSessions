package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
	
	List<College> findByCollegeTypeAndLocation(String CollegeType,String location);
	List<College> findByNoOfBranchesOrYearOfEstablishment(int noOfBranches, int year);
	List<College> findByNameContains(String name);
	List<College> findTop10ByOrderByName();
	
	@Query(value="from College c where c.name=:name and c.location=:location")
	List<College> findByQuery(String name,String location);
	
	@Query(value="select c.* from College c where c.name=:name and c.location=:location", nativeQuery=true)
	List<College> findByNativeQuery(String name,String location);
	
}
