package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.College;
import com.example.demo.repository.CollegeRepository;
import com.example.demo.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {
	
	@Autowired
	CollegeRepository collegeRepository;

	@Override
	public College getCollege(int id) {
		// TODO Auto-generated method stub
		College college=null;
		Optional<College> coll= collegeRepository.findById(id);
		if(coll.isPresent())
			college=coll.get();
		return college;
	}

	@Override
	public List<College> getCollegeList() {
		return collegeRepository.findAll();
	}

	@Override
	public boolean addCollege(College college) {
		College colleg=collegeRepository.save(college);
		if(colleg != null)
		    return true;
		else 
			return false;
	}

	@Override
	public boolean updateCollege(College college,int id) {
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

}
