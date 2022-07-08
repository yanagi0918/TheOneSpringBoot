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

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Resume;
import com.theone.springboot.service.ResumeService;

@Controller
@RequestMapping("/user")
public class ResumeUserController {

	@Autowired
	ResumeService resumeService;
	
	@GetMapping(path = "/resumes")
	public String toResumeListPage(HttpSession session, Model model){
		Member member = (Member)session.getAttribute("loginUser");
		String userid = String.valueOf(member.getUserid());
		List<Resume> resumes = resumeService.findByUserId(userid);
		model.addAttribute("resumes", resumes);
		model.addAttribute("total",resumes.size()); //共幾筆
		return "resume/allResume";
	}
	
	@GetMapping(path = "/resume")
	public String toCreatePage(HttpSession session, Model model) {
		Member member = (Member)session.getAttribute("loginUser");
		model.addAttribute("member",member);
		return "resume/insertResume";
	}
	
	@PostMapping(path = "/resume")
	public String saveOrUpdate(Resume resume){
		resumeService.saveOrUpdate(resume);
		return "redirect:/user/resumes";  
	}
	
//	@GetMapping("/resume/{id}")
//	public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
//		Resume resume = resumeService.getResume(id).get();
//		model.addAttribute("resume", resume);
//		return "resume_dashboard/resumeupdate";
//	}
	
	@ResponseBody
	@DeleteMapping("/resume/{id}")
	public String delete(@PathVariable("id") Integer id) {
		resumeService.deleteResume(id);
		return "ok";
	}
	
}
