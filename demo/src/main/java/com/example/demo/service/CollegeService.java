package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.College;

public interface CollegeService {

	public College getCollege(int id);
	public List<College> getCollegeList();
	public boolean addCollege(College college);
	public boolean updateCollege(College college,int id);
	public boolean deleteCollege(int id);
}
