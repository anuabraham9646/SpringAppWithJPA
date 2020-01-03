package com.topics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService serv;
	
	@RequestMapping("/topics")
	public List<Topics> getAll(){
		return serv.getAllTopics();
	}
	@RequestMapping("/topics/{id}")
	public Topics getById(@PathVariable int id) {
		return serv.getById(id);
	}
	@RequestMapping(method=RequestMethod.POST,value="/topics")
	public void addTopics(@RequestBody Topics topic) {
		serv.addTopic(topic);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/topics/{id}")
	public void updateTopics(@RequestBody Topics topic, @PathVariable int id) {
		serv.updateTopic(topic,id);
	}
	@DeleteMapping(value="/topics/{id}")
	public void deleteTopics(@PathVariable int id) {
		serv.deleteTopic(id);
	}
	
}
