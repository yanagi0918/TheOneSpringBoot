package com.theone.springboot.controller;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Job;
import com.theone.springboot.service.JobService;

@RequestMapping("/enterprise/job")
@Controller	
public class JobController {
	@Autowired
	private JobService jobService;
	
	@GetMapping("/list")
	private String listJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "job/job_list";
	}
	
	@GetMapping("/showForm")
	private String showFormForAdd(Model m){
		return "job/job_create";
	}
	
	@PostMapping("/saveJob")
	private String saveJob(@ModelAttribute("job") Job job){
		jobService.saveOrUpdate(job);
		return "redirect:/enterprise/job/list";
	}
	
	@GetMapping("/jobdeatail/{pk}")
	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m){
		Job jobdeatail = jobService.getJob(detailId).get();
		m.addAttribute("jobdeatail",jobdeatail);
		return "job/job_detail";
	}
	
	@GetMapping("/showupdateinformation/{pk}")
	private String showupdateinformation(@PathVariable("pk") Integer updateId,Model m){
		Job jobupdate = jobService.getJob(updateId).get();
		m.addAttribute("jobupdate", jobupdate);
		return "job/job_update";
	}

	@ResponseBody
	@DeleteMapping("/delete/{id}")
	private String processDelete(@PathVariable("id") Integer id){
		jobService.delete(id);
		return "ok";
	}

}
