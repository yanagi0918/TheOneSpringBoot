package com.theone.springboot.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.entity.Event;
import com.theone.springboot.entity.Interview;
import com.theone.springboot.entity.InterviewMessage;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.EventService;
import com.theone.springboot.service.InterviewMessageService;
import com.theone.springboot.service.InterviewService;




@Controller
@RequestMapping({"/"})
public class InterviewUserController {
	
	@Autowired
	InterviewService interviewService;
	@Autowired
	EventService eventService;
	@Autowired
	InterviewMessageService interviewMessageService;
	
	@RequestMapping("/intvlist")//所有目錄
	public String getIntvListPage(Model model) {
		List<Interview> Allintvs = interviewService.getAllInterviews();
		model.addAttribute("intvs",Allintvs);
		model.addAttribute("interviewMessageService",interviewMessageService);
		
		//放入廣告頁面
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvlist";
	}
	
	// 個人list
		@RequestMapping("/user/intvlist")
		public String listMyintv(HttpSession session, Model model) {
			Member member = (Member)session.getAttribute("loginMember");
			List<Interview> findByUserId = interviewService.findByUserId(member.getUserid());
			model.addAttribute("intvs", findByUserId);
			model.addAttribute("interviewMessageService",interviewMessageService);
			//放入廣告頁面
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

		@RequestMapping("/intv")//進入新增頁面
	public String toCreate(Model model) {
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvedit";
	}
	
	
	
		//新增資料
		@PostMapping("/saveintv")
	public String saveOrUpdate( Interview intv) {
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
	
	//進入show
	@RequestMapping("/intvshow/{id}")
	public String toShow(@PathVariable("id") Integer id,
			Model model,@ModelAttribute("interviewMessage") InterviewMessage interviewMessage,
			HttpSession httpSession) {
		System.out.println("---- 進入show----");
		
		//分享的心得區
		Interview intv = interviewService.getInterview(id).get();
		model.addAttribute("intvs", intv);
		
		//留言區
		List<InterviewMessage> intvmess = interviewMessageService.findByInterviewCvNo(id);//留言陣列
		model.addAttribute("intvmess",intvmess);
		model.addAttribute("interviewMessage",interviewMessage);
		
		if (intvmess.size() != 0) {
			InterviewMessage maxMessageId = intvmess.stream().max(Comparator.comparing(InterviewMessage::getMessageOrder))
					.get();
			interviewMessage.setMessageOrder((maxMessageId.getMessageOrder()) + 1);
		}else {
			interviewMessage.setMessageOrder(1);
		}
		//廣告區
		List<Event> events = eventService.findByStateAndPostStartBeforeAndPostEndAfter(1, new Date(), new Date());
		model.addAttribute("events",events);
		return "interview/intvshow";
		
	}
	
//	@PostMapping
//	public Map<String,Object>addMessage(InterviewMessage interviewMessage) {
//		
//		return interviewMessageService.addMessage(interviewMessage);
//		
//	}
	
	//新增留言
	@PostMapping("/{id}/addmess")
	public String saveMessagae(@PathVariable("id") Integer id,
			@ModelAttribute("interviewMessage") InterviewMessage interviewMessage, Model model) {
		System.out.println("---新增留言--");
		Timestamp ts= new Timestamp(System.currentTimeMillis());
		Interview intv = interviewService.getInterview(id).get();
		interviewMessage.setTime(ts);
		interviewMessage.setInterview(intv);
		
		interviewMessageService.save(interviewMessage);
		return "redirect:/intvshow/{id}";
		
	}
	
//	//顯示留言
//	@PostMapping("/findmess/")
//	public String findMess(@PathVariable Integer id,Model model) {
//		
//		System.out.println("--進入顯示留言區---");
//		List<InterviewMessage> intvmess=interviewMessageService.findByInterviewCvNo(id);
//		model.addAttribute("intvmess",intvmess);
//		return "interview/intvshow";
//		
//	}
	
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
