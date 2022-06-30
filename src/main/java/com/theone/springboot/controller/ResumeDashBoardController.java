package com.theone.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Resume;
import com.theone.springboot.service.ResumeService;

@Controller
@RequestMapping("/dashboard")
public class ResumeDashBoardController {

	@Autowired
	ResumeService resumeService;
	
	@GetMapping(path = "/resumes")
	public String toResumeListPage(Model model){
		List<Resume> allresumes = resumeService.getAllResumes();
		model.addAttribute("resumes", allresumes);
		return "resume_dashboard/resumelist";
	}
	
	@GetMapping(path = "/resume")
	public String toCreatePage() {
		return "resume_dashboard/resumecreate";
	}
	
	@PostMapping(path = "/resume")
	public String saveOrUpdate(Resume resume){
		resumeService.saveOrUpdate(resume);
		return "redirect:/dashboard/resumes";  
	}
	
	@GetMapping("/resume/{id}")
	public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
		Resume resume = resumeService.getResume(id).get();
		model.addAttribute("resume", resume);
		return "resume_dashboard/resumeupdate";
	}
	
	@ResponseBody
	@DeleteMapping("/resume/{id}")
	public String delete(@PathVariable("id") Integer id) {
		resumeService.deleteResume(id);
		return "ok";
	}
	
}
