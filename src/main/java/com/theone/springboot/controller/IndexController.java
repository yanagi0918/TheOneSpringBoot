package com.theone.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.theone.springboot.entity.Comment;
import com.theone.springboot.entity.Event;
import com.theone.springboot.entity.Job;
import com.theone.springboot.service.CommentMessageService;
import com.theone.springboot.service.CommentService;
import com.theone.springboot.service.EventService;
import com.theone.springboot.service.JobService;

@Controller
public class IndexController {

	@Autowired
	EventService eventService;

	@Autowired
	JobService jobService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	CommentMessageService commentMessageService;

	@GetMapping("/")
	public String toIndex(Model model) {
		Date today = new Date();
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, today, today);
		model.addAttribute("events", events);

		List<Job> jobs = jobService.getAllJobs();
		if (jobs.size() >= 3) {
			model.addAttribute("jobs", jobs.subList(jobs.size()-3, jobs.size()));
		} else {
			model.addAttribute("jobs", jobs);
		}
		
		List<Comment> listComment = commentService.findAll();
		if (listComment.size() >= 3) {
			model.addAttribute("listComment", listComment.subList(listComment.size()-3, listComment.size()));
		} else {
			model.addAttribute("listComment", listComment);
		}
		model.addAttribute("commentMessageService", commentMessageService);

		return "index";
	}

}
