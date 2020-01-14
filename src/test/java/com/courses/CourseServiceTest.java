package com.courses;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topics.TopicRepository;
import com.topics.TopicService;
import com.topics.Topics;



@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {CourseService.class})
class CourseServiceTest {
	
	@MockBean
	private CourseRepository repo;
	Course c1= new Course(1,"javva","javva desc",1);
	Course c2= new Course(2,"Hibernate","hibernate desc",1);
	Course c3= new Course(3,"Sql","SQl desc",1);
	ArrayList<Course> lis= new ArrayList<Course>();

	@Test
	void testGetAllCourse() {
		lis.add(c1);
		lis.add(c2);
		lis.add(c3);
		when(repo.findByTopId(1)).thenReturn(lis);
		assertEquals(repo.findByTopId(1),lis);
	}

	@Test
	void testGetById() {
		when(repo.findById(anyInt())).thenReturn(Optional.ofNullable(c1));
		assertEquals(repo.findById(2).get(), c1);
	}

	@Test
	void testAddCourse() {
		lis.add(c1);
		lis.add(c2);
		doAnswer( mock -> {
			Course o = mock.getArgument(0);
			lis.add( o ); 
			assertEquals(3,lis.size());
			return null;
			} 
		).when(repo).save(any(Course.class));
	}

	@Test
	void testUpdateCourse() {
		lis.add(c1);
		lis.add(c2);
		doAnswer( mock -> {
			Course o = mock.getArgument(0);
			int id= o.getId();
			lis.set(id-1, o); 
			assertEquals("Python",lis.get(id-1).getName());
			return null;
			} 
		).when(repo).save(new Course(2,"Python","Python description",1));
	}

	@Test
	void testDeleteCourse() {
		lis.add(c1);
		lis.add(c2);
		doAnswer( mock -> {
			int id = mock.getArgument(0);
			int size= lis.size();
			Predicate<Course> top= lis-> lis.getId()==id;
			lis.removeIf(top); 
			assertEquals(1,lis.size());
			return null;
			} 
		).when(repo).deleteById(2);
	}

}
