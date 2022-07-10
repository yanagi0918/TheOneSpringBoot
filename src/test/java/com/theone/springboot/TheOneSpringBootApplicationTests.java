package com.theone.springboot;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.theone.springboot.entity.Event;
import com.theone.springboot.repository.EventDao;
import com.theone.springboot.service.EventService;


@SpringBootTest
class TheOneSpringBootApplicationTests {
	
	@Autowired
	EventDao eventDao;
	
	@Autowired
	EventService eventService;
	
	@Test
	public void testJPA() {
		List<Event> events = eventDao.findByCompId("12345678");
		System.out.println("==================================");
		for (Event event : events) {
			System.out.println(event);
		}
		System.out.println("==================================");
	}
	
	@Test
	public void testJavaMail() {
		eventService.sendNotifyEmail("aass13172@gmail.com", "Test subject", "test msg");
	}


}
