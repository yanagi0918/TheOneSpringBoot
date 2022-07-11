package com.theone.springboot.controller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.theone.springboot.ecpay.payment.integration.AllInOne;
import com.theone.springboot.ecpay.payment.integration.domain.AioCheckOutALL;
import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;
import com.theone.springboot.service.CourseService;
import com.theone.springboot.service.OrderService;

//前台
@Controller
@RequestMapping("/enterprise")
public class OrderUserController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CourseService courseServicer;
	
	@GetMapping(path = "/orders")
	public String showData(Model model) {
		List<Order> ordersforward = orderService.getAllOrders();	
		model.addAttribute("courseList", courseServicer.findAllCourses());
		model.addAttribute("orders", ordersforward);
		model.addAttribute("total",ordersforward.size());
		return "order/order_listing";
	}
	
//	@GetMapping(path = "/order")
//	public String toAdd(Model model) {
//		model.addAttribute("courseList", courseServicer.findAllCourses());
//		return "order/order_details";
//	}
//	
//	@PostMapping(path = "/order")
//	public String processCreate(Order order) {
//		orderService.saveOrUpdate(order);
//		return "redirect:orders";
//	}
//	
//	@GetMapping(path = "/order/{id}")
//	public String processUpdate(@PathVariable("id") Integer id, Model model){
//		Order order = orderService.getOrder(id).get();
//		model.addAttribute("courseList", courseServicer.findAllCourses());
//		model.addAttribute("order", order);
//		return "order_dashboard/orderupdate";
//	}
//	
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
		Order order = orderService.getOrder(id).get();
		model.addAttribute("Order", order);
		return "order/order_details";
	}
	
	//綠界
	@ResponseBody
	@PostMapping(path = "/Order/Buy")
	public String genAioCheckOutALL(@RequestParam(value = "courseid", required = false)String courseid){
		int r=(int)(Math.random()*1000+1);
		java.util.Date date=new java.util.Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		AllInOne all= new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo("tuk004"+r);
		obj.setMerchantTradeDate(sdf.format(date));
		obj.setTotalAmount("50");
		obj.setTradeDesc("test Description");
		obj.setItemName("課程1#課程2");
		obj.setReturnURL("http://42.72.134.198:8080/enterprise/ordersDetail/"+courseid);
		obj.setClientBackURL("http://localhost:8080/enterprise/orders/");
		obj.setNeedExtraPaidInfo("N");
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
		//新增
		@GetMapping(path = "/ordersDetail/{id}")
		public void orderSave(@PathVariable("id") String id,HttpServletRequest request){
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			orderService.saveOrder(id,loginMember);
		}

}
