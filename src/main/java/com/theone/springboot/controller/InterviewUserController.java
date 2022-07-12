package com.theone.springboot.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.theone.springboot.entity.Comment;
import com.theone.springboot.entity.Event;
import com.theone.springboot.entity.Interview;
import com.theone.springboot.service.EventService;
import com.theone.springboot.service.InterviewService;




@Controller
@RequestMapping({"/"})
public class InterviewUserController {
	
	@Autowired
	InterviewService interviewService;
	@Autowired
	EventService eventService;
	
	@GetMapping("/intvlist")//目錄
	public String getIntvListPage(Model model) {
		List<Interview> Allintvs = interviewService.getAllInterviews();
		model.addAttribute("intvs",Allintvs);
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvlist";
	}
//	@GetMapping("/intvaside")//aside目錄
//	public String getTointvaside(Model model) {
//		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
//		model.addAttribute("events",events);
//		return "interview/intvaside";
//	}

	@GetMapping("/intv")//進入新增頁面
	public String toCreate(Model model) {
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvedit";
	}
	
	
	
		//新增資料
		@PostMapping("/saveintv")
	public String saveOrUpdate( Interview intv) {
//		 SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
//	        sdf.applyPattern("yyyy-MM-dd HH:mm:ss"); 
//	        LocalDate date = new LocalDate();
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		intv.setCreateTime(ts);
		System.out.println(ts);
		interviewService.saveOrUpdate(intv);
		
		return "redirect:/intvlist";
	}

	@GetMapping("/intv/{id}")//進入修改頁面
	public String toUpdate(@PathVariable Integer id, Model model) {
		Interview intv = interviewService.getInterview(id).get();
		model.addAttribute("intvs", intv);
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvupdate";

	}
	// 更新資料
//			@RequestMapping("/intvUpdate")
//			public String updateComment(@ModelAttribute("intv") Interview intv) {
//				interviewService.saveOrUpdate(intv);
//				return "redirect:/intvlist";
//			}
	@GetMapping("/intvshow/{id}")
	public String toShow(@PathVariable Integer id, Model model) {
		Interview intv = interviewService.getInterview(id).get();
		model.addAttribute("intvs", intv);
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvshow";
		
	}
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id")Integer id) {
		interviewService.deleteInterview(id);
		return "ok";
	}
	
	@InitBinder 
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true); 
		binder.registerCustomEditor(Date.class, ce);
		// java.sql.Date		
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true); 
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}
	
}
