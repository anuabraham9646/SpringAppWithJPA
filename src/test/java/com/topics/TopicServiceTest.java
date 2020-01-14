package com.topics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
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

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers= {TopicService.class})
class TopicServiceTest {

	@MockBean
	private TopicRepository repo;
	
	Topics mockTopic= new Topics(1,"Java","Javaaaa");
	Topics mockTopic1= new Topics(2,"JavaScript","JavaScriptaaa");
	Topics mockTopic2= new Topics(3,"Python","Python description");
	ArrayList<Topics> lis= new ArrayList<Topics>();

	@Test
	void testGetAllTopics() throws UnsupportedEncodingException, JSONException {

		lis.add(mockTopic);
		lis.add(mockTopic1);
		lis.add(mockTopic2);
		when(repo.findAll()).thenReturn(lis);
		assertEquals(repo.findAll(), lis);
	}

	@Test
	void testGetById() {
		when(repo.findById(anyInt())).thenReturn(Optional.ofNullable(mockTopic1));
		assertEquals(repo.findById(2).get(), mockTopic1);
	}

	@Test
	void testAddTopic() {
		
		lis.add(mockTopic);
		lis.add(mockTopic1);
		doAnswer( mock -> {
			Topics o = mock.getArgument(0);
			lis.add( o ); 
			assertEquals(3,lis.size());
			return null;
			} 
		).when(repo).save(any(Topics.class));
	}


	@Test
	void testUpdateTopic() {
		lis.add(mockTopic);
		lis.add(mockTopic1);
		doAnswer( mock -> {
			Topics o = mock.getArgument(0);
			int id= o.getId();
			lis.set(id-1, o); 
			assertEquals("Python",lis.get(id-1).getName());
			return null;
			} 
		).when(repo).save(new Topics(2,"Python","Python description"));
	}

	@Test
	void testDeleteTopic() {
		lis.add(mockTopic);
		lis.add(mockTopic1);
		doAnswer( mock -> {
			int id = mock.getArgument(0);
			int size= lis.size();
			Predicate<Topics> top= lis-> lis.getId()==id;
			lis.removeIf(top); 
			assertEquals(1,lis.size());
			return null;
			} 
		).when(repo).deleteById(2);
	}

}
