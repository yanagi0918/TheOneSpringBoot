package com.theone.springboot.service.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lowagie.text.DocumentException;
import com.theone.springboot.entity.Event;
import com.theone.springboot.repository.EventDao;
import com.theone.springboot.service.EventService;
import com.theone.springboot.utils.EventCsvExporter;
import com.theone.springboot.utils.EventPdfExporter;

@Service
@Transactional
public class EventServiceImpl implements EventService {
	@Autowired
	EventDao eventDao;

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	EventCsvExporter csvExporter;
	
	@Autowired
	EventPdfExporter pdfExporter;
	
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

	@Override
	public List<Event> findByCompId(String compId) {
		return eventDao.findByCompId(compId);
	}

	@CachePut(cacheNames = "events", key = "#pk")
	@Override
	public Event revokeEvent(Integer pk) {
		Event event = eventDao.findById(pk).get();
		event.setState(3);
		return eventDao.save(event);
	}

	@Override
	public List<Event> findByCompIdAndStateNot(String compId, Integer state) {
		return eventDao.findByCompIdAndStateNot(compId, state);
	}

	@Override
	public List<Event> findByState(Integer state) {
		return eventDao.findByState(state);
	}

	@Override
	public List<Event> findByStateAndPostStartBeforeAndPostEndAfter(Integer state, Date beforeToday, Date afterToday) {
		return eventDao.findByStateAndPostStartBeforeAndPostEndAfter(state, beforeToday, afterToday);
	}

	@Override
	public void sendNotifyEmail(String recipient, String subject, String message) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("eeit45theone@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject(subject);
			messageHelper.setText(message, true);
		};
		try {
			mailSender.send(messagePreparator);
			 System.out.println("sent");
		} catch (MailException e) {
			 System.out.println(e);
		}
	}

	@Override
	public int countByState(Integer state) {
		return eventDao.countByState(state);
	}

	@Override
	public void csvExport(Writer writer) {
		List<Event> events = eventDao.findAll();
		csvExporter.csvExport(writer, events);
	}

	@Override
	public void pdfExport(HttpServletResponse response) {
		List<Event> events = eventDao.findAll();
		try {
			pdfExporter.pdfExport(response, events);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

}
