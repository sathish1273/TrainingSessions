package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.College;
import com.example.demo.response.BusinessMessage;
import com.example.demo.response.Response;
import com.example.demo.response.StatusEnum;
import com.example.demo.service.CollegeService;

@RestController
@RequestMapping("/college")
public class Controller {
	@Autowired
	CollegeService collegeService;
	
	Response res=null;
	List<BusinessMessage> bm=null;
	
	@GetMapping("/")
	public ResponseEntity<String> healthCheck()
	{
		return new ResponseEntity<>("Success",HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<College> getCollege(@PathVariable int id)
	{
		return new ResponseEntity<>(collegeService.getCollege(id),HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<College>> getCollegeList()
	{
		return new ResponseEntity<>(collegeService.getCollegeList(),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addCollege(@RequestBody College college)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		boolean b=collegeService.addCollege(college);
		if(b) {
			bm.add(new BusinessMessage("Successfully posted to DB"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
		}
		else {	
			bm.add(new BusinessMessage("Not posted.."));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		}
		res.setResObj(collegeService.getCollegeList());
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> upadteCollege(@RequestBody College college,@PathVariable int id)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		boolean b=collegeService.updateCollege(college,id);
		if(b) {
			bm.add(new BusinessMessage("Successfully updated"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
		}
		else {	
			bm.add(new BusinessMessage("Not updated"));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		}
		res.setResObj(collegeService.getCollege(id));
		return new ResponseEntity<>(res,HttpStatus.OK);
	}
	
	@DeleteMapping("/update/{id}")
	public ResponseEntity<Response> deleteCollege(@PathVariable int id)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		boolean b=collegeService.deleteCollege(id);
		if(b) {
			bm.add(new BusinessMessage("Successfully deleted"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
		}
		else {	
			bm.add(new BusinessMessage("Not deleted"));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		}
		
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	
}
