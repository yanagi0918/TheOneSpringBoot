package com.theone.springboot.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;
import com.theone.springboot.service.CompanyService;
import com.theone.springboot.service.JobService;

@RequestMapping("/enterprise/job")
@Controller	
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping("/companylist")
	private String companyListJobs(HttpSession session,Model m){
		Company company = (Company)session.getAttribute("loginEnterprise");
		Integer compid = company.getCompid();
		List<Job> companyjobs = jobService.findByCompId(compid);
		m.addAttribute("companyjobs", companyjobs);
		return "job/company_job_list";
	}
	
	
	@GetMapping("/list")
	private String listJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "job/job_list";
	}
	
	@GetMapping("/showForm")
	private String showFormForAdd(HttpSession session,Model m){
		Company company = (Company)session.getAttribute("loginEnterprise");
		m.addAttribute("company",company);
		return "job/job_create";
	}
	
	@PostMapping("/saveJob")
	private String saveJob(Job job){
		jobService.saveOrUpdate(job);
		return "redirect:/enterprise/job/companylist";
	}
	
	@GetMapping("/jobdeatail/{pk}")
	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m,Company company){
		List<Job> jobsforward = jobService.getAllJobs();
		m.addAttribute("companylist",companyService.getAllCompanies());
		m.addAttribute("jobs",jobsforward);
		
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
