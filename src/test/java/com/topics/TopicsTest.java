package com.topics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TopicsTest {

	@Test
	void testTopics() {
		new Topics();
	}

	@Test
	void testTopicsIntStringString() {
		new Topics(1,"JavaEE","JavaEE desc");
	}

	@Test
	void testGetId() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getId(), 1);
	}

	@Test
	void testSetId() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getId(), 1);
	}

	@Test
	void testGetName() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getName(), "Java");
	}

	@Test
	void testSetName() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getName(), "Java");
	}

	@Test
	void testGetDescription() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getDescription(), "Java updated");
	}

	@Test
	void testSetDescription() {
		Topics t= new Topics(1,"Java","Java updated");
		assertEquals(t.getDescription(), "Java updated");
	}

}
