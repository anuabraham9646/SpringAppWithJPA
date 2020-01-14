package com.topics;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.courses.CourseController;
import com.courses.CourseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {TopicController.class})
class TopicControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired 
	private ObjectMapper mapper;
	
	@MockBean
	private TopicService topicService;
	
	Topics mockTopic= new Topics(1,"Java","Javaaaa");
	Topics mockTopic1= new Topics(2,"JavaScript","JavaScriptaaa");
	//Topics mockTopic2= new Topics(3,"Python","Python description");
	ArrayList<Topics> lis= new ArrayList<Topics>();
	
	String exampleCourseJson = "{\"id\":\"1\",\"name\":\"Java\",\"description\":\"Javaaaa\"}";


	@Test
	void testGetAll() throws Exception {
		//fail("Not yet implemented");
		lis.add(mockTopic);
		lis.add(mockTopic1);
		Mockito.when(topicService.getAllTopics()).thenReturn(lis);
		RequestBuilder reqBuil=MockMvcRequestBuilders.get("/topics").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcRes=  mockMvc.perform(reqBuil).andReturn(); 
		System.out.println("aaaaaaa"+mvcRes.getResponse().getContentAsString());
		
		String expected= "[{id:1,name:Java,description:Javaaaa}, {id:2,name:JavaScript,description:JavaScriptaaa}]";
		System.out.println("exp"+ expected);
		JSONAssert.assertEquals(expected, mvcRes.getResponse().getContentAsString(),false);
		
	}

	@Test
	void testGetById() throws Exception {
		String id= String.valueOf(mockTopic.getId());
		System.out.println(id);
		Mockito.when(topicService.getById(Mockito.anyInt())).thenReturn(mockTopic);
		RequestBuilder reqBuil=MockMvcRequestBuilders.get("/topics/1").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcRes=  mockMvc.perform(reqBuil).andReturn(); 
		System.out.println("aaaaaaa"+mvcRes.getResponse().getContentAsString());
		
		String expected= "{id:1,name:Java,description:Javaaaa}";
		System.out.println("exp"+ expected);
		JSONAssert.assertEquals(expected, mvcRes.getResponse().getContentAsString(),false);
	}

	@Test
	void testAddTopics() throws Exception {
		
		Mockito.doNothing().when(topicService).addTopic(Mockito.any(Topics.class));
		String json= mapper.writeValueAsString(mockTopic1);

		System.out.println("json is"+json);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/topics")
				.contentType(MediaType.APPLICATION_JSON)
				.content(exampleCourseJson)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("the response is "+result +"response ends");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
	

	@Test
	void testUpdateTopics() throws Exception {
		Mockito.doNothing().when(topicService).updateTopic(Mockito.any(Topics.class), Mockito.anyInt());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/topics/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(mockTopic1))
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
		Mockito.doNothing().when(topicService).deleteTopic(Mockito.anyInt());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/topics/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		System.out.println("the response is "+result +"response ends");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}
