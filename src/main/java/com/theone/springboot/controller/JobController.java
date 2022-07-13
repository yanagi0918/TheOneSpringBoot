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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Job;
import com.theone.springboot.service.CompanyService;
import com.theone.springboot.service.JobService;

//@RequestMapping("/enterprise/job")
@Controller	
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private CompanyService companyService;
	
	
	@GetMapping("/enterprise/job/companylist")
	private String companyJobs(HttpSession session,Model m){
		Company company = (Company)session.getAttribute("loginEnterprise");
		List<Job> jobList = jobService.findByCompany(company);
		m.addAttribute("jobList",jobList);
		return "job/company_job_list";
	}
	
	
	
	
	
	
	
	
	@GetMapping("/job/choicejoblist")
	private String pickChoiceListJobs(@RequestParam(required = false) String jobdescription,
            						  Model m,Job job) {
		if (jobdescription != null) {
		List<Job> jobs = jobService.findByJobdescription(jobdescription);
		m.addAttribute("jobdescription",jobdescription);
		m.addAttribute("jobs",jobs);
		
	}
		return "job/job_list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/job/list")
	private String allCanSeeListJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "job/job_list";
	}
	
	@GetMapping("/enterprise/job/showForm")
	private String showFormForAdd(HttpSession session,Model m){
//		Company company = (Company)session.getAttribute("loginEnterprise");
//		m.addAttribute("company",company);
		return "job/job_create";
	}
	
	@PostMapping("/enterprise/job/saveJob")
	private String saveJob(HttpSession session, Job job){
		Company loginCompany = (Company)session.getAttribute("loginEnterprise");
		job.setCompany(loginCompany);
		jobService.saveOrUpdate2(job);
		return "redirect:/enterprise/job/companylist";
	}
	
	@GetMapping("/enterprise/job/jobdeatail/{pk}")
	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m,Company company){
		
		Job jobdeatail = jobService.getJob(detailId).get();
		m.addAttribute("jobdeatail",jobdeatail);
		return "job/job_detail";
	}
	
	@GetMapping("/enterprise/job/showupdateinformation/{pk}")
	private String showupdateinformation(@PathVariable("pk") Integer updateId,Model m){
		Job jobupdate = jobService.getJob(updateId).get();
		m.addAttribute("jobupdate", jobupdate);
		return "job/job_update";
	}

	@ResponseBody
	@DeleteMapping("/enterprise/job/delete/{id}")
	private String processDelete(@PathVariable("id") Integer id){
		jobService.delete(id);
		return "ok";
	}

}
