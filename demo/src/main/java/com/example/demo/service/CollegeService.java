package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.College;
import com.example.demo.request.CollegeRq;

public interface CollegeService {

	public College getCollege(int id);
	public List<College> getCollegeList(int pageNumber, int pageSize);
	public College addCollege(CollegeRq college);
	public boolean updateCollege(CollegeRq college,int id);
	public boolean deleteCollege(int id);
}
