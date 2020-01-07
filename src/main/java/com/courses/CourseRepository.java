package com.courses;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseRepository extends CrudRepository<Course, Integer>{
	public List<Course> findByTopId(int id);

}
