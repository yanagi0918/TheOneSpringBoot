package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Event;
import com.theone.springboot.service.EventService;

@Controller
@RequestMapping("/enterprise")
public class EventUserController {
	
	@Autowired
	EventService eventService;
	
	@GetMapping("/events")
	public String toEventListPage(HttpSession session, Model model) {
		Company company = (Company)session.getAttribute("loginEnterprise");
		String compid = String.valueOf(company.getCompid());
		List<Event> events = eventService.findByCompIdAndStateNot(compid, 3);
		
		List<Event> events0 = events.stream().filter((Event e) -> e.getState()==0).collect(Collectors.toList());
		List<Event> events1 = events.stream().filter((Event e) -> e.getState()==1).collect(Collectors.toList());
		List<Event> events2 = events.stream().filter((Event e) -> e.getState()==2).collect(Collectors.toList());
		
		model.addAttribute("events0",events0);
		model.addAttribute("events1",events1);
		model.addAttribute("events2",events2);
		model.addAttribute("total",events.size());
		return "event/event_listing";
	}
	
	@GetMapping("/event")
	public String toCreatePage() {
		return "event/event_application";
	}
	
	@PostMapping("/event")
	public String saveOrUpdate(Event event, MultipartFile imageFile) throws IllegalStateException, IOException {
		System.out.println(event);
		String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String ImgUrl = "/eventimages/" + newFileName;

		String saveFilePath = new File("target\\classes\\static\\eventimages\\" + newFileName).getAbsolutePath();

		if (imageFile.getOriginalFilename().length() != 0) {
			event.setImgUrl(ImgUrl);
			imageFile.transferTo(new File(saveFilePath));
		}

		eventService.saveOrUpdate(event);

		return "redirect:/enterprise/events";
	}
	
	@ResponseBody
	@DeleteMapping("/event/{id}")
	public String delete(@PathVariable("id") Integer id) {
		eventService.revokeEvent(id);
		return "ok";
	}
	
	/*
	@GetMapping("/event/detail")
	public String test() {
		return "event/event_detail";
	}
	*/
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, ce);
    }

}
