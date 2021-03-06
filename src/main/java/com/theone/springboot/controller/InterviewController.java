package com.theone.springboot.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
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

import com.theone.springboot.entity.Interview;
import com.theone.springboot.entity.InterviewMessage;
import com.theone.springboot.service.InterviewMessageService;
import com.theone.springboot.service.InterviewService;




@Controller
@RequestMapping("/dashboard")
public class InterviewController {
	
	@Autowired
	private InterviewService interviewService;
	@Autowired
	InterviewMessageService interviewMessageService;
	
	@GetMapping("/intvlist")
	public String getIntvListPage(Model model) {
		List<Interview> Allintvs = interviewService.getAllInterviews();
		model.addAttribute("intvs",Allintvs);

		return "interview_dashboard/intvlist";
	}

	@GetMapping("/intv")
	public String toCreate() {
		return "interview_dashboard/intvCreate";
	}

	@PostMapping("/saveintv")
	public String saveOrUpdate( Interview intv) {
//		 SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
//	        sdf.applyPattern("yyyy-MM-dd HH:mm:ss"); 
//	        LocalDate date = new LocalDate();
		Timestamp ts=new Timestamp(System.currentTimeMillis());
		intv.setCreateTime(ts);
		interviewService.saveOrUpdate(intv);
		
		return "redirect:/dashboard/intvlist";
	}

	@RequestMapping("/intv/{id}")
	public String toUpdate(@PathVariable Integer id, Model model) {
		Interview intv = interviewService.getInterview(id).get();
		model.addAttribute("intvs", intv);
		return "interview_dashboard/intvupdate";

	}
	@RequestMapping("/intvshow/{id}")
	public String toShow(@ModelAttribute("interviewMessage") InterviewMessage interviewMessage,@PathVariable Integer id, Model model) {
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
		
		return "interview_dashboard/intvshow";
		
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
