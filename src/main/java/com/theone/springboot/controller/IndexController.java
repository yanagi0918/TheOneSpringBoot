package com.theone.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.theone.springboot.entity.Event;
import com.theone.springboot.entity.Job;
import com.theone.springboot.service.EventService;
import com.theone.springboot.service.JobService;

@Controller
public class IndexController {
	
	@Autowired
	EventService eventService;
	
	@Autowired
	JobService jobService;
	
	@GetMapping("/")
	public String toIndex(Model model) {
		Date today = new Date();
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, today, today);
		model.addAttribute("events",events);
		
		List<Job> jobs = jobService.getAllJobs();
		model.addAttribute("jobs", jobs.subList(0, 3));
		
		return "index";
	}

}
