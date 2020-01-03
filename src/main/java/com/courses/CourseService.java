package com.courses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
	
	/*List<Topics> topics= new ArrayList<>(Arrays.asList(

			new Topics(1,"Spring boot", "its all about spring boot"),
			new Topics(2,"Java", "its all about Java"),
			new Topics(3,"Python", "its all about Python")
			));*/
	@Autowired
	private CourseRepository repo;
			
	public List<Course> getAllCourse(){
		
		return (List<Course>) repo.findAll();
	}
	
	public Course getById(int id) {
		
		//return topics.stream().filter(t -> t.getId()== id).findFirst().get();
		return repo.findById(id).orElse(null);
	}

	public void addCourse(Course course) {

		repo.save(course);
		
	}

	public void updateCourse(Course course, int id) {
		repo.save(course);
		
	}

	public void deleteCourse(int id) {
		repo.deleteById(id) ;
		
	}

}
