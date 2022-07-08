package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Event;

public interface EventService {
	boolean isDup(Integer pk);

	Event saveOrUpdate(Event event);

	List<Event> getAllEvents();
	
	List<Event> findByCompIdAndStateNot(String compId, Integer state);

	Optional<Event> getEvent(Integer pk);

	void deleteEvent(Integer pk);
	
	Event revokeEvent(Integer pk);
	
	List<Event> findByCompId(String compId);

}
