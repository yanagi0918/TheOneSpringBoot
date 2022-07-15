package com.theone.springboot.controller;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.JobService;
import com.theone.springboot.service.MemberService;

@Controller	
public class JobController {
	@Autowired
	private JobService jobService;
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/enterprise/job/companylist")
	private String companyJobs(HttpSession session,Model m){
		Company company = (Company)session.getAttribute("loginEnterprise");
		List<Job> jobList = jobService.findByCompany(company);
		m.addAttribute("jobList",jobList);
		return "job/company_job_list";
	}
	
	//用職缺型態查詢
	@GetMapping("/job/jobdescriptionchoicejoblist")
	private String jobdescriptionChoiceListJobs(@RequestParam String jobdescription,
            						  Model m,Job job) {
		if (jobdescription != null) {
		List<Job> jobs = jobService.findByJobdescription(jobdescription);
		m.addAttribute("jobdescription",jobdescription);
		m.addAttribute("jobs",jobs);
		
	}
		return "job/job_list";
	}
	
	
	//用薪水查詢
	@GetMapping("/job/salarychoicejoblist")
	private String salaryChoiceListJobs(@RequestParam String salary,
            						  Model m,Job job) {
		if (salary != null) {
		List<Job> jobs = jobService.getBySalary(salary);
		m.addAttribute("salary",salary);
		m.addAttribute("jobs",jobs);
		
	}
		return "job/job_list";
	}
	
	//用職缺名稱模糊+型態查詢
	@GetMapping("/job/search")
	private String jobSearch(String jobdescription,String title,Model m ) {
		List<Job> jobs = jobService.findByTitleContainingAndJobdescription(title,jobdescription);
		
		m.addAttribute("title",title);
		m.addAttribute("jobdescription",jobdescription);
		m.addAttribute("jobs",jobs);
		
		return "job/job_list";
	}
	
	//公共頁面
	@GetMapping("/job/list")
	private String allCanSeeListJobs(Model m){
		List<Job> jobs = jobService.getAllJobs();
		m.addAttribute("jobs", jobs);
		return "job/job_list";
	}
	
	@GetMapping("/enterprise/job/showForm")
	private String showFormForAdd(HttpSession session,Model m){
		return "job/job_create";
	}
	
	@PostMapping("/enterprise/job/saveJob")
	private String saveJob(HttpSession session, Job job){
		Company loginCompany = (Company)session.getAttribute("loginEnterprise");
		job.setCompany(loginCompany);
		jobService.saveOrUpdate2(job);
		return "redirect:/enterprise/job/companylist";
	}
	
	@GetMapping("/job/jobdeatail/{pk}")
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
	
	@GetMapping("/user/joblist/{jobid}")
	public String insertResume(@PathVariable Integer jobid,HttpSession session) {
		
		Member member = (Member) session.getAttribute("loginMember");
		Set<Job> collectionJobs = member.getCollectionJobs();
		
		Job job = jobService.findByJobid(jobid);
		collectionJobs.add(job);
		
		Set<Job> empty = new HashSet<Job>();
		member.setCollectionJobs(empty);
		memberService.saveOrUpdate(member);
		
		member.setCollectionJobs(collectionJobs);
		memberService.saveOrUpdate(member);
		
		return "job/job_list";
	}
	
	
	@GetMapping("/enterprise/job/showmember/{jobid}")
	public String showMember(@PathVariable Integer jobid,HttpSession session,Model m) {
		System.out.println("-------------------------------");
		Company loginCompany = (Company)session.getAttribute("loginEnterprise");
		List<Job> jobs = loginCompany.getJobs();
		Set<Member> collectonJobMembers =new HashSet<Member>();
		Iterator<Job> iterator = jobs.iterator();
		while (iterator.hasNext()) {
			Job job = (Job) iterator.next();
			if (job.getJobid().equals(jobid)) {
				 collectonJobMembers = job.getCollectonJobMembers();
			}
			m.addAttribute("collectonJobMembers",collectonJobMembers);
		}
		System.out.println("-------------------------------");
		return "job/job_member";
	}
	
	
}
