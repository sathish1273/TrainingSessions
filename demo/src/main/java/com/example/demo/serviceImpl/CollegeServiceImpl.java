package com.example.demo.serviceImpl;

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
	public List<College> getCollegeList(CollegeRq college) {
		return collegeRepository.findByNameContainsAndLocationOrderByLocationAsc(college.getName(), college.getLocation());
	}

}
