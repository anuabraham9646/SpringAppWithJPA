package com.courses;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.topics.Topics;

@Entity
public class Course {
	@Id
	private int id;
	private String name;
	private String Description;
	@ManyToOne
	private Topics top;
	public Course() {
		
	}
	
	public Course(int id, String name, String description, int topicId) {
		super();
		this.id = id;
		this.name = name;
		Description = description;
		this.top= new Topics(topicId, "", "");
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Topics getTop() {
		return top;
	}
	public void setTop(Topics top) {
		this.top = top;
	}
}
