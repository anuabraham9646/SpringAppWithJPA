package com.topics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	/*List<Topics> topics= new ArrayList<>(Arrays.asList(

			new Topics(1,"Spring boot", "its all about spring boot"),
			new Topics(2,"Java", "its all about Java"),
			new Topics(3,"Python", "its all about Python")
			));*/
	@Autowired
	private TopicRepository repo;
			
	public List<Topics> getAllTopics(){
		
		return (List<Topics>) repo.findAll();
	}
	
	public Topics getById(int id) {
		
		//return topics.stream().filter(t -> t.getId()== id).findFirst().get();
		return repo.findById(id).orElse(null);
	}

	public void addTopic(Topics topic) {

		repo.save(topic);
		
	}

	public void updateTopic(Topics topic, int id) {
		repo.save(topic);
		
	}

	public void deleteTopic(int id) {
		repo.deleteById(id) ;
		
	}

}
