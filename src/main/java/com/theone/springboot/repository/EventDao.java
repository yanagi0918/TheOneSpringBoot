package com.theone.springboot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Event;

public interface EventDao extends JpaRepository<Event, Integer> {
	
	List<Event> findByCompId(String compId);

	List<Event> findByCompIdAndStateNot(String compId, Integer state);
	
	List<Event> findByState(Integer state);
	
	List<Event> findByStateAndPostStartBeforeAndPostEndAfter(Integer state, Date beforeToday, Date afterToday);
	
	int countByState(Integer state);
}
