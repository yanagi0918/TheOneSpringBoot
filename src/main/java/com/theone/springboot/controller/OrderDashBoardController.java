package com.theone.springboot.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.Order;
import com.theone.springboot.service.OrderService;

@Controller
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

//	@PostMapping(path = "/order")
//	public String saveOrUpdate(Order order, MultipartFile imageFile) throws IllegalStateException, IOException {
//
//		String processCreate = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
//		String ProductId = "/orderimages/" + newFileName;
//
//		String saveFilePath = new File("target\\classes\\static\\eventimages\\" + newFileName).getAbsolutePath();
//
//		if (imageFile.getOriginalFilename().length() != 0) {
//			order.setProductId(ProductId);
//			imageFile.transferTo(new File(saveFilePath));
//		}
//
//		orderService.saveOrUpdate(order);
//		return "redirect:orders";
//	}
	
	@PostMapping(path = "/order")
	public String processCreate(Order order) {
		orderService.saveOrUpdate(order);
		return "redirect:orders";
	}
//	
//	@PostMapping(path = "/orderupdate")
//	public String processUpdate(Order order){
//		orderService.saveOrUpdate(order);
//		return "redirect:orders";
//	}

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

}
