package com.courses;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {CourseController.class} )
class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CourseService courseService;
	
	Course mockCourse= new Course(1,"Java","Java description",1);
	Course mockCourse1= new Course(2,"JavaScript","JavaScript description",1);
	Course mockCourse2= new Course(3,"Python","Python description",1);
	List<Course> lis= new ArrayList<Course>();

	@Test
	void testGetAll() throws Exception{

		lis.add(mockCourse);
		lis.add(mockCourse1);
		Mockito.when(courseService.getAllCourse(Mockito.anyInt())).thenReturn(lis);
		RequestBuilder reqBuil=MockMvcRequestBuilders.get("/topics/1/course").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcRes=  mockMvc.perform(reqBuil).andReturn(); 
		System.out.println("aaaaaaa"+mvcRes.getResponse().getContentAsString());
		
		String expected= "[{id:1,name:Java,description:\"Java description\",top:{id:1,name:\"\",description:\"\"}}, {id:2,name:JavaScript,description:\"JavaScript description\",top:{id:1,name:\"\",description:\"\"}}]";
		//top:{id:1,name:,description:}
		System.out.println("exp"+ expected);
		JSONAssert.assertEquals(expected, mvcRes.getResponse().getContentAsString(),false);
	}

	@Test
	void testGetById() throws Exception {
		String id= String.valueOf(mockCourse.getId());
		System.out.println(id);
		//Mockito.when(courseService.getById(3).thenReturn(mockCourse2);
		Mockito.when(courseService.getById(Mockito.eq(3))).thenReturn(mockCourse2);
				
		RequestBuilder reqBuil=MockMvcRequestBuilders.get("/topics/1/course/3").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcRes=  mockMvc.perform(reqBuil).andReturn(); 
		System.out.println("aaaaaaa"+mvcRes.getResponse().getContentAsString());
		
		String expected= "{id:3,name:Python,description:\"Python description\",top:{id:1,name:\"\",description:\"\"}}";
		
		System.out.println("exp"+ expected);
		JSONAssert.assertEquals(expected, mvcRes.getResponse().getContentAsString(),false);
	}

	@Test
	void testAddCourse() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateTopics() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteTopics() {
		fail("Not yet implemented");
	}

}
