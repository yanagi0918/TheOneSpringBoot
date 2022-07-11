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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;
import com.theone.springboot.service.CompanyService;
import com.theone.springboot.service.JobService;

@RequestMapping("/dashboard/job")
@Controller	
public class JobDashBoardController {
	@Autowired
	private JobService jobService;
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/list")
	private String listJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("companyList",companyService.getAllCompanies());
		m.addAttribute("jobs", jobs);
		return "job_dashboard/joblist";
	}
	
	@GetMapping("/showForm")
	private String showFormForAdd(Model m){
		m.addAttribute("companyList",companyService.getAllCompanies());
		return "job_dashboard/jobcreate";
	}
	
	@PostMapping("/saveJob")
	private String saveJob(@ModelAttribute("job") Job job){
		jobService.saveOrUpdate(job);
		return "redirect:/dashboard/job/list";
	}
	
	@GetMapping("/jobdeatail/{pk}")
	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m){
		Job jobdeatail = jobService.getJob(detailId).get();
		m.addAttribute("jobdeatail",jobdeatail);
		return "job_dashboard/jobdetail";
	}
	
	@GetMapping("/showupdateinformation/{pk}")
	private String showupdateinformation(@PathVariable("pk") Integer updateId,Model m){
		Job jobupdate = jobService.getJob(updateId).get();
		m.addAttribute("companyList",companyService.getAllCompanies());
		m.addAttribute("jobupdate", jobupdate);
		return "job_dashboard/jobupdate";
	}

	@ResponseBody
	@DeleteMapping("/delete/{id}")
	private String processDelete(@PathVariable("id") Integer id){
		jobService.delete(id);
		return "ok";
	}
	
	@PostMapping(value = "/CheckUserFromCompId")
	public @ResponseBody boolean CheckUserFromCompId(@RequestParam Integer compId) {
		Company company = companyService.getByCompid(compId);
		if (company == null) {
			return true;
		}
		return false;
	}

}
