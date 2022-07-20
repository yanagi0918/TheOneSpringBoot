package com.theone.springboot.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.theone.springboot.entity.Order;
import com.theone.springboot.service.CourseService;
import com.theone.springboot.service.MemberService;
import com.theone.springboot.service.OrderService;

//後台
@Controller
@RequestMapping("/dashboard")
public class OrderDashBoardController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CourseService courseServicer;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping(path = "/orders")
	public String showData(Model model) {
		List<Order> orders = orderService.getAllOrders();
		model.addAttribute("memberList", memberService.getAllMembers());
		model.addAttribute("courseList", courseServicer.findAllCourses());
		model.addAttribute("orders", orders);
		model.addAttribute("total",orders.size());
		model.addAttribute("TotalPrice",orderService.findTotalPrice());
		return "order_dashboard/orderlist";
	}
	
	@GetMapping(path = "/order")
	public String toAdd(Model model) {
		model.addAttribute("memberList", memberService.getAllMembers());
		model.addAttribute("courseList", courseServicer.findAllCourses());
		return "order_dashboard/ordercreate";
	}
	
	@PostMapping(path = "/order")
	public String processCreate(Order order) {
		order.setMember(memberService.getMember(Integer.valueOf(order.getUserId())).get());
		orderService.saveOrUpdate(order);
		return "redirect:orders";
	}
	
	@GetMapping(path = "/order/{id}")
	public String processUpdate(@PathVariable("id") Integer id, Model model){
		Order order = orderService.getOrder(id).get();
		model.addAttribute("memberList", memberService.getAllMembers());
		model.addAttribute("courseList", courseServicer.findAllCourses());
		model.addAttribute("order", order);
		return "order_dashboard/orderupdate";
	}
	
	//刪除
	@ResponseBody
	@DeleteMapping(path = "/order/{id}")
	public String processDelete(@PathVariable("id")Integer id) {
		orderService.deleteOrder(id);
		return "ok";
	}
	
	//日期
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(java.util.Date.class, ce);
    }
	
	//查看頁面
	@GetMapping(path = "/Order/{id}")
	public String processDetail(@PathVariable("id") Integer id, Model model){
		model.addAttribute("memberList", memberService.getAllMembers());
		model.addAttribute("courseList", courseServicer.findAllCourses());
		Order order = orderService.getOrder(id).get();
		model.addAttribute("Order", order);
		return "order_dashboard/orderdetail";
	}
	
	//審核
	@PutMapping("/orders")
	@ResponseBody
	public ResponseEntity<Order> saveOrUpdate(@RequestBody Order Order) {
		Order orders = orderService.findOrder(Order.getOrderId()).orElseThrow();
		String state = Order.getState();		
		orders.setState(state);
		orderService.saveOrUpdate(orders);
		if("已退款".equals(state) || "已駁回".equals(state)) {
			Integer id = Order.getOrderId();			
			String msg = "<p style=\"font-size: large;\">" +
					 "訂單編號: " + id + "<br>" +
					 "審核結果: <font color=\"blue\"><b>" + state + "</b></font><br>" +
					 "連結: http://localhost:8080/user/orders" +
					 "</p>";
		orderService.sendNotifyEmail("wl02968970@gmail.com", "TheOne 訂單審核通知", msg);
		}
		return ResponseEntity.status(HttpStatus.OK).body(orders);
	}
	
	//統計圖
	@ResponseBody
	@GetMapping("/order/Orderchartdata")
	public int[] getChartData() {
		int[] chartdata = {0,0,0,0};
		List<Order> allOrders = orderService.getAllOrders();
		for (Order order : allOrders) {
			switch (order.getState()) {
			case "已付款": chartdata[0]++; break;
			case "待審核": chartdata[1]++; break;
			case "已退款": chartdata[2]++; break;
			case "已駁回": chartdata[3]++; break;
			default: break;
			}
		}
		return chartdata;
	}
	
	//CSV
	@GetMapping("/orders/csvExport")
	public void csvExport(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv;charset=UTF-8");
		response.addHeader("Content-Disposition","attachment; filename=resumes.csv");
		orderService.csvExport(response.getWriter());
	}
	
	
	//寄信
	@ResponseBody
	@GetMapping("/order/sendemail")
	public boolean sendNotifyEmail(String id, String result) {
		if ("已退款".equals(result) || "已駁回".equals(result)) {
			result = ("已退款".equals(result))?"已退款":"已駁回";
				
			String msg = "<p style=\"font-size: large;\">" +
						 "訂單編號: " + id + "<br>" +
						 "審核結果: <font color=\"blue\"><b>" + result + "</b></font><br>" +
						 "連結: http://localhost:8080/user/orders" +
						 "</p>";
			orderService.sendNotifyEmail("wl02968970@gmail.com", "TheOne 訂單審核通知", msg);
			return true;
		}
		
		return false;
	}
	
}
