package com.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService serv;
	
	@RequestMapping("/course")
	public List<Course> getAll(){
		return serv.getAllCourse();
	}
	@RequestMapping("/course/{id}")
	public Course getById(@PathVariable int id) {
		return serv.getById(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="/course")
	public void addTopics(@RequestBody Course course) {
		serv.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/course/{id}")
	public void updateTopics(@RequestBody Course course, @PathVariable int id) {
		serv.updateCourse(course,id);
	}
	@DeleteMapping(value="/course/{id}")
	public void deleteTopics(@PathVariable int id) {
		serv.deleteCourse(id);
	}
	
}
