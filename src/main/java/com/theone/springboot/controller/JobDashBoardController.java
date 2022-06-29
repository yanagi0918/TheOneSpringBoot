package com.theone.springboot.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.theone.springboot.entity.Job;
import com.theone.springboot.service.JobService;

@RequestMapping("/job")
@Controller	
public class JobDashBoardController {
	@Autowired
	private JobService jobService;
	
	@GetMapping("/list")
	private String listJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "job_dashboard/joblist";
	}
	
	@GetMapping("/showForm")
	private String showFormForAdd(Model m){
		return "job_dashboard/jobcreate";
	}
	
	@PostMapping("/saveJob")
	private String saveJob(@ModelAttribute("job") Job job){
		jobService.saveOrUpdate(job);
		return "redirect:/job/list";
	}
	
	@GetMapping("/showupdateinformation/{pk}")
	private String showupdateinformation(@PathVariable("pk") Integer updateId,Model m){
		Job jobupdate = jobService.getJob(updateId).get();
		m.addAttribute("jobupdate", jobupdate);
		return "job_dashboard/jobupdate";
	}

	@GetMapping("/delete/{pk}")
	private String processDelete(@PathVariable("pk") Integer deleteId){
		jobService.delete(deleteId);
		return "redirect:/job/list";
	}

}
