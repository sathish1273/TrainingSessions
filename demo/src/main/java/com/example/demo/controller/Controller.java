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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.College;
import com.example.demo.request.CollegeRq;
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
	HttpStatus httpstatus=null;
	
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
	public ResponseEntity<List<College>> getCollegeList(@RequestParam int pageNumber, @RequestParam int pageSize)
	{
		return new ResponseEntity<>(collegeService.getCollegeList(pageNumber,pageSize),HttpStatus.OK);
	}
	
	@PostMapping("/filteredList")
	public ResponseEntity<List<College>> getCollegeListByCustomeQueries()
	{
		return new ResponseEntity<>(collegeService.getCollegeList(),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Response> addCollege(@RequestBody CollegeRq college)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		College b=collegeService.addCollege(college);
		if(b != null) {
			bm.add(new BusinessMessage("Successfully posted to DB"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
			httpstatus=HttpStatus.OK;
		}
		else {	
			bm.add(new BusinessMessage("Not posted.."));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		    httpstatus=HttpStatus.NOT_FOUND;
		}
		res.setResponse(b);
		return new ResponseEntity<>(res,httpstatus);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Response> upadteCollege(@RequestBody CollegeRq college,@PathVariable int id)
	{
		res=new Response();
		bm=new ArrayList<BusinessMessage>();
		boolean b=collegeService.updateCollege(college,id);
		if(b) {
			bm.add(new BusinessMessage("Successfully updated"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
			httpstatus=HttpStatus.OK;
		}
		else {	
			bm.add(new BusinessMessage("Not updated"));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		    httpstatus=HttpStatus.NOT_FOUND;
		}
		res.setResponse(collegeService.getCollege(id));
		return new ResponseEntity<>(res,httpstatus);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteCollege(@PathVariable int id)
	{
		res=new Response();
		bm=new ArrayList<>();
		boolean b=collegeService.deleteCollege(id);
		if(b) {
			bm.add(new BusinessMessage("Successfully deleted"));
			res.setBusinessMessage(bm);
			res.setStatus(StatusEnum.SUCCESS);
			httpstatus=HttpStatus.OK;
		}
		else {	
			bm.add(new BusinessMessage("Not deleted"));
		    res.setBusinessMessage(bm);
		    res.setStatus(StatusEnum.FAIL);
		    httpstatus=HttpStatus.NOT_FOUND;
		}
		
		return new ResponseEntity<>(res,httpstatus);
	}

	
}
