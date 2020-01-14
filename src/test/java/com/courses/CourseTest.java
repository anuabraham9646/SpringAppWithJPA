package com.courses;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.topics.Topics;

class CourseTest {

	@Test
	void testCourse() {
		new Course();
	}

	@Test
	void testCourseIntStringString() {
		new Course(1,"Java","Java desc");
	}

	@Test
	void testCourseIntStringStringInt() {
		new Course(1,"Java","Java desc",1);
	}

	@Test
	void testGetId() {
		Course c= new Course();
		c.setId(1);
		assertEquals(c.getId(),1);
	}

	@Test
	void testSetId() {
		Course c= new Course();
		c.setId(1);
		assertEquals(c.getId(),1);
	}

	@Test
	void testGetName() {
		Course c= new Course();
		c.setName("Java");
		assertTrue(c.getName()=="Java");
	}

	@Test
	void testSetName() {
		Course c= new Course();
		c.setName("Java");
		assertTrue(c.getName()=="Java");
	}

	@Test
	void testGetDescription() {
		Course c= new Course();
		c.setDescription("Java desc");
		assertEquals(c.getDescription(),"Java desc");
	}

	@Test
	void testSetDescription() {
		Course c= new Course();
		c.setDescription("Java desc");
		assertEquals(c.getDescription(),"Java desc");
	}

	@Test
	void testGetTop() {
		Course c= new Course();
		c.setTop(new Topics(1,"Java","Java updated"));
		assertEquals(c.getTop().getId(),1);
	}

	@Test
	void testSetTop() {
		Course c= new Course();
		c.setTop(new Topics(1,"Java","Java updated"));
		assertEquals(c.getTop().getId(),1);
	}

}
