package com.theone.springboot;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.theone.springboot.entity.Event;
import com.theone.springboot.repository.EventDao;

@SpringBootTest
class TheOneSpringBootApplicationTests {
	
	@Autowired
	EventDao eventDao;
	
	@Test
	public void testJPA() {
		List<Event> events = eventDao.findByCompId("12345678");
		System.out.println("==================================");
		for (Event event : events) {
			System.out.println(event);
		}
		System.out.println("==================================");
	}


}
