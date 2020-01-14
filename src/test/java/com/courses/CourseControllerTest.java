package com.courses;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import java.util.Optional;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.topics.Topics;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {CourseController.class} )
class CourseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired 
	private ObjectMapper mapper;
	
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
		Mockito.when(courseService.getById(Mockito.anyInt())).thenReturn(mockCourse2);
				
		RequestBuilder reqBuil=MockMvcRequestBuilders.get("/topics/1/course/3").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcRes=  mockMvc.perform(reqBuil).andReturn(); 
		System.out.println("aaaaaaa"+mvcRes.getResponse().getContentAsString());
		
		String expected= "{id:3,name:Python,description:\"Python description\",top:{id:1,name:\"\",description:\"\"}}";
		
		System.out.println("exp"+ expected);
		JSONAssert.assertEquals(expected, mvcRes.getResponse().getContentAsString(),false);
	}

	@Test
	void testAddCourse() throws Exception {
		Mockito.doNothing().when(courseService).addCourse(Mockito.any(Course.class));
		String json= mapper.writeValueAsString(mockCourse2);

		System.out.println("json is"+json);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/topics/1/course")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("the response is "+result +"response ends");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testUpdateTopics() throws Exception {
		Mockito.doNothing().when(courseService).updateCourse(Mockito.any(Course.class));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/topics/1/course/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockCourse2))
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("the response is "+result +"response ends");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Test
	void testDeleteTopics() throws Exception {
		Mockito.doNothing().when(courseService).deleteCourse(Mockito.anyInt());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/topics/1/course/3")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("the response is "+result +"response ends");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
