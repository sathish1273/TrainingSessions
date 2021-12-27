package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.entity.College;
import com.example.demo.repository.CollegeRepository;
import com.example.demo.request.CollegeRq;
import com.example.demo.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {
	
	@Autowired
	CollegeRepository collegeRepository;

	@Override
	public College getCollege(int id) {
		College college=null;
		Optional<College> coll= collegeRepository.findById(id);
		if(coll.isPresent())
			college=coll.get();
		return college;
	}

	@Override
	public List<College> getCollegeList(int pageNumber, int pageSize) {
		Pageable pageable= PageRequest.of(pageNumber, pageSize, Sort.by(Direction.ASC, "name"));
		return collegeRepository.findAll(pageable).getContent();
	}

	@Override
	public College addCollege(CollegeRq college) {
		College coll=new College();
		coll.setLocation(college.getLocation());
		coll.setName(college.getName());
		coll.setNoOfBranches(college.getNoOfBranches());
		coll.setYearOfEstablishment(college.getYearOfEstablishment());
		coll.setCollegeType(college.getCollegeType());
		College colleg=collegeRepository.save(coll);
		return colleg;
	}

	@Override
	public boolean updateCollege(CollegeRq college,int id) {
		College coll= getCollege(id);
		if(coll != null) {
			coll.setLocation(college.getLocation());
			coll.setName(college.getName());
			collegeRepository.save(coll);
			    return true;
		}
		else 
			return false;
	}

	@Override
	public boolean deleteCollege(int id) {
		College coll= getCollege(id);
		if(coll != null) {
			collegeRepository.delete(coll);
			    return true;
		}
		else 
			return false;
	}

	@Override
	public List<College> getCollegeList() {
		//return collegeRepository.findByNameContainsAndLocationOrderByLocationAsc(college.getName(), college.getLocation());
		//Collection<Integer> list= Arrays.asList(2009,2010);
		//return collegeRepository.findByYearOfEstablishmentIn(list);
		System.out.println("**** findByCollegeTypeAndLocation **** ");
		 collegeRepository.findByCollegeTypeAndLocation("JNTU","Warangal").stream().forEach(e->System.out.println(e.getName()));
		 
		 System.out.println("**** findByNoOfBranchesOrYearOfEstablishment **** ");
			collegeRepository.findByNoOfBranchesOrYearOfEstablishment(10, 2009).stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findByNameContains**** ");
			collegeRepository.findByNameContains("CBIT").stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findByNameContainsOrderByNameDesc**** ");
			collegeRepository.findByNameContainsOrderByNameDesc("CBIT").stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findByNameContainsAndLocationOrderByLocationAsc**** ");
			collegeRepository.findByNameContainsAndLocationOrderByLocationAsc("CBIT","Warangal").stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findTop10ByOrderByName**** ");
			collegeRepository.findTop10ByOrderByName().stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findByNoOfBranchesGreaterThanEqual**** ");
			collegeRepository.findByNoOfBranchesGreaterThanEqual(10).stream().forEach(e->System.out.println(e.getName()));
			
			System.out.println("**** findByNameIs");
			collegeRepository.findByNameIs("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameEquals");
			collegeRepository.findByNameEquals("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByYearOfEstablishmentBetween");
			collegeRepository.findByYearOfEstablishmentBetween(1990,2009).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByYearOfEstablishmentLessThan");
			collegeRepository.findByYearOfEstablishmentLessThan(2009).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByYearOfEstablishmentGreaterThan *****");
			collegeRepository.findByYearOfEstablishmentGreaterThan(2009).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByYearOfEstablishmentGreaterThanEqual *****");
			collegeRepository.findByYearOfEstablishmentGreaterThanEqual(2009).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findBystartDateAfter *****");
			collegeRepository.findBystartDateAfter(LocalDate.now()).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findBystartDateBefore *****");
			collegeRepository.findBystartDateBefore(LocalDate.now()).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("****findByNameIsNull  *****");
			collegeRepository.findByNameIsNull().stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameIsNotNull *****");
			collegeRepository.findByNameIsNotNull().stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameLike *****");
			collegeRepository.findByNameLike("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameNotLike *****");
			collegeRepository.findByNameNotLike("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameStartingWith *****");
			collegeRepository.findByNameStartingWith("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameEndingWith *****");
			collegeRepository.findByNameEndingWith("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameContaining *****");
			collegeRepository.findByNameContaining("CBIT").stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByYearOfEstablishmentOrderByNameDesc *****");
			collegeRepository.findByYearOfEstablishmentOrderByNameDesc(2009).stream().forEach(e->System.out.println(e.getName()));
			System.out.println("**** findByNameNot *****");
			collegeRepository.findByNameNot("CBIT").stream().forEach(e->System.out.println(e.getName()));
		return collegeRepository.findByActiveStatusTrue();
	}

}
