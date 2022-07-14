package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

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
import com.theone.springboot.service.CompanyService;
import com.theone.springboot.service.EventService;

@Controller
@RequestMapping("/dashboard")
public class EventDashBoardController {

	@Autowired
	EventService eventService;

	@Autowired
	CompanyService companyService;

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
	
	@ResponseBody
	@GetMapping("/event/sendemail")
	public boolean sendNotifyEmail(String id, String result, String remark) {
		if ("1".equals(result) || "2".equals(result)) {
			result = ("1".equals(result))?"審核通過":"退件";
			
			String msg = "<p style=\"font-size: large;\">" +
						 "廣告編號: " + id + "<br>" +
						 "審核結果: <font color=\"blue\"><b>" + result + "</b></font><br>" +
						 "備註: " + remark + "<br>" +
						 "連結: http://localhost:8080/enterprise/events" +
						 "</p>";
			eventService.sendNotifyEmail("yc20150701@gmail.com", "TheOne 廣告審核通知", msg);
			return true;
		}
		return false;
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

	@ResponseBody
	@GetMapping("/event/compid_exist/{compid}")
	public boolean checkUser(@PathVariable("compid") Integer compid) {
		Company company = companyService.getByCompid(compid);
		return ((company == null) ? false : true );
	}
	
	@ResponseBody
	@GetMapping("/event/chartdata")
	public int[] getChartData() {
		int[] chartdata = {0,0,0,0};
		List<Event> allEvents = eventService.getAllEvents();
		for (Event event : allEvents) {
			switch (event.getState()) {
			case 0: chartdata[0]++; break;
			case 1: chartdata[1]++; break;
			case 2: chartdata[2]++; break;
			case 3: chartdata[3]++; break;
			default: break;
			}
		}
		return chartdata;
	}
	
	@GetMapping("/events/csvExport")
	public void csvExport(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv;charset=UTF-8");
		response.addHeader("Content-Disposition","attachment; filename=events.csv");
		eventService.csvExport(response.getWriter());
	}
	
	@GetMapping("/events/pdfExport")
	public void pdfExport(HttpServletResponse response) throws IOException {
		response.setContentType("application/pdf;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=events.pdf");
        eventService.pdfExport(response);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, ce);
	}

}
