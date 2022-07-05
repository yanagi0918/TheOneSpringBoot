package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Event;
import com.theone.springboot.repository.EventDao;
import com.theone.springboot.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	@Autowired
	EventDao eventDao;

	@Override
	public boolean isDup(Integer pk) {
		return eventDao.existsById(pk);
	}

	@CachePut(cacheNames = "events", key = "#result.eventId")
	@Override
	public Event saveOrUpdate(Event event) {
		return eventDao.save(event);
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.findAll();
	}

	@Cacheable(cacheNames = "events", key = "#pk")
	@Override
	public Optional<Event> getEvent(Integer pk) {
		return eventDao.findById(pk);
	}

	@CacheEvict(cacheNames = "events", key = "#pk")
	@Override
	public void deleteEvent(Integer pk) {
		eventDao.deleteById(pk);
	}

}
