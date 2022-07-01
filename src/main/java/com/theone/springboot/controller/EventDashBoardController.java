package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import com.theone.springboot.entity.Event;
import com.theone.springboot.service.EventService;

@Controller
@RequestMapping("/dashboard")
public class EventDashBoardController {

	@Autowired
	EventService eventService;

	@GetMapping("/events")
	public String toEventListPage(Model model) {
		List<Event> allEvents = eventService.getAllEvents();
		model.addAttribute("events", allEvents);
		return "event_dashboard/eventlist";
	}

	@GetMapping("/event")
	public String toCreatePage() {
		return "event_dashboard/eventcreate";
	}

	@PostMapping("/event")
	public String saveOrUpdate(Event event, MultipartFile imageFile) throws IllegalStateException, IOException {

		String newFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
		String ImgUrl = "/eventimages/" + newFileName;

		String saveFilePath = new File("target\\classes\\static\\eventimages\\" + newFileName).getAbsolutePath();

		if (imageFile.getOriginalFilename().length() != 0) {
			event.setImgUrl(ImgUrl);
			imageFile.transferTo(new File(saveFilePath));
		}

		eventService.saveOrUpdate(event);

		return "redirect:/dashboard/events";
	}

	@GetMapping("/event/{id}")
	public String toUpdatePage(@PathVariable("id") Integer id, Model model) {
		Event event = eventService.getEvent(id).get();
		model.addAttribute("event", event);
		return "event_dashboard/eventupdate";
	}
	
	@ResponseBody
	@DeleteMapping("/event/{id}")
	public String delete(@PathVariable("id") Integer id) {
		eventService.deleteEvent(id);
		return "ok";
	}
	
	
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, ce);
    }

}
