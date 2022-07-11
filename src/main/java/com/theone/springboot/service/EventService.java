package com.theone.springboot.service;

import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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
	
	List<Event> findByState(Integer state);
	
	List<Event> findByStateAndPostStartBeforeAndPostEndAfter(Integer state, Date beforeToday, Date afterToday);
	
	public void sendNotifyEmail(String recipient, String subject, String message);
	
	int countByState(Integer state);
	
	void csvExport(Writer writer);
	
	void pdfExport(HttpServletResponse response);

}
