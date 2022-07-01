package com.theone.springboot.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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

import com.theone.springboot.entity.Order;
import com.theone.springboot.service.OrderService;

@Controller
@RequestMapping("/dashboard")
public class OrderDashBoardController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(path = "/orders")
	public String showData(Model model) {
		List<Order> orders = orderService.getAllOrders();
		model.addAttribute("orders", orders);
		return "order_dashboard/orderlist";
	}
	
	@GetMapping(path = "/order")
	public String toAdd() {
		return "order_dashboard/ordercreate";
	}
	
	@PostMapping(path = "/order")
	public String processCreate(Order order) {
		orderService.saveOrUpdate(order);
		return "redirect:orders";
	}

	@GetMapping(path = "/order/{id}")
	public String processUpdate(@PathVariable("id") Integer id, Model model){
		Order order = orderService.getOrder(id).get();
		model.addAttribute("order", order);
		return "order_dashboard/orderupdate";
	}
	
	@ResponseBody
	@DeleteMapping(path = "/order/{id}")
	public String processDelete(@PathVariable("id")Integer id) {
		orderService.deleteOrder(id);
		return "ok";
	}
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(java.util.Date.class, ce);
    }

}
