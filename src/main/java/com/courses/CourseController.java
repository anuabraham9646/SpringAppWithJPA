package com.courses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.topics.Topics;

@RestController
public class CourseController {
	
	@Autowired
	private CourseService serv;
	
	@RequestMapping("/topics/{id}/course")
	public List<Course> getAll(@PathVariable int id){
		return serv.getAllCourse(id);
	}
	@RequestMapping("/topics/{topicId}course/{id}")
	public Course getById(@PathVariable int id) {
		return serv.getById(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="/topics/{topicId}/course")
	public void addCourse(@RequestBody Course course ,@PathVariable int topicId) {
		course.setTop(new Topics(topicId,"",""));
		serv.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{topicId}/course/{id}")
	public void updateTopics(@RequestBody Course course, @PathVariable int topicId) {
		course.setTop(new Topics(topicId,"",""));
		serv.updateCourse(course);
	}
	@DeleteMapping(value="/topics/{topicId}/course/{id}")
	public void deleteTopics(@PathVariable int id) {
		serv.deleteCourse(id);
	}
	
}
