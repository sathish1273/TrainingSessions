package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Integer> {
	
	List<College> findByCollegeTypeAndLocation(String collegeType,String location);
	List<College> findByNoOfBranchesOrYearOfEstablishment(int noOfBranches, int year);
	List<College> findByNameContains(String name);
	List<College> findByNameContainsOrderByNameDesc(String name);
	List<College> findByNameContainsAndLocationOrderByLocationAsc(String name,String location);
	List<College> findTop10ByOrderByName();
	List<College> findByNoOfBranchesGreaterThanEqual(int branches);
	List<College> findByNameIs(String name);
	List<College> findByNameEquals(String name);
	List<College> findByYearOfEstablishmentBetween(int from,int to);
	List<College> findByYearOfEstablishmentLessThan(int year);
	List<College> findByYearOfEstablishmentGreaterThan(int year);
	List<College> findByYearOfEstablishmentGreaterThanEqual(int year);
	List<College> findBystartDateAfter(LocalDate date);
	List<College> findBystartDateBefore(LocalDate date);
	List<College> findByNameIsNull();
	List<College> findByNameIsNotNull();
	List<College> findByNameLike(String name);
	List<College> findByNameNotLike(String name);
	List<College> findByNameStartingWith(String name);
	List<College> findByNameEndingWith(String name);
	List<College> findByNameContaining(String name);
	List<College> findByYearOfEstablishmentOrderByNameDesc(int year);
	List<College> findByNameNot(String name);
	List<College> findByYearOfEstablishmentIn(Collection<Integer> list);
	List<College> findByYearOfEstablishmentNotIn(Collection<College> list);
	List<College> findByActiveStatusTrue();
	List<College> findByActiveStatusFalse();
	List<College> findByNameIgnoreCase(String name);
	
	@Query(value="from College c where c.name=:name and c.location=:location")
	List<College> findByQuery(String name,String location);
	
	@Query(value="select c.* from College c where c.name=:name and c.location=:location", nativeQuery=true)
	List<College> findByNativeQuery(String name,String location);
	
}
