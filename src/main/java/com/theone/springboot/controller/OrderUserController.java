package com.theone.springboot.controller;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.ecpay.payment.integration.AllInOne;
import com.theone.springboot.ecpay.payment.integration.domain.AioCheckOutALL;
import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Order;
import com.theone.springboot.service.CourseService;
import com.theone.springboot.service.MemberService;
import com.theone.springboot.service.OrderService;

//前台
@Controller
@RequestMapping("/user")
public class OrderUserController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CourseService courseServicer;
	
	@Autowired
	MemberService memberService;
	
	
	//顯示全部資料
//	@GetMapping(path = "/orders")
//	public String showData(Model model){
//		List<Order> ordersforward = orderService.getAllOrders();	
//		model.addAttribute("courseList", courseServicer.findAllCourses());
//		model.addAttribute("orders", ordersforward);
//		model.addAttribute("total",ordersforward.size());
//		return "order/order_listing";
//	}
	
	 //查看該會員有的訂單
	 @GetMapping("/orders")
	 public String showData(Model model, HttpSession session) throws UnsupportedEncodingException {
	        Member loginUser = (Member) session.getAttribute("loginMember");
	        List<Order> orderList = orderService.findByMember(loginUser);
	        model.addAttribute("member",memberService.getAllMembers());
	        model.addAttribute("courseList", courseServicer.findAllCourses());
	        model.addAttribute("orders", orderList);
	        model.addAttribute("total",orderList.size());
	        return "order/order_listing";
	 }
	
	 @PostMapping("/orders")
	    public String saveOrUpdate(Order Order, @RequestParam("imgURL") MultipartFile mf, HttpSession session) throws IOException {
	        System.out.println(Order);
	        Member loginUser = (Member) session.getAttribute("loginMember");
	        File imageFile = new File(System.currentTimeMillis() + "_" + mf.getOriginalFilename());
	        File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFile.getName());
	        if (mf.getOriginalFilename().length() != 0) {
	            mf.transferTo(savedFile);
	        }
	        Order.setUserId(loginUser.getUserid());
	        Order.setMember(loginUser);
	        orderService.saveOrUpdate(Order);
	        return "redirect:/user/orders";
	    }
	 
	 public File uploadDirInit() {
	        String savedFilePath = new File("target\\classes\\static\\orderImg\\").getAbsolutePath();
	        File uploadDir = new File(savedFilePath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
	        return uploadDir;
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
	@PostMapping(path = "/courses/Buy")
	public String genAioCheckOutALL(@RequestParam(value = "courseid", required = false)String courseid,HttpServletRequest request){
		Member loginMember = (Member) request.getSession().getAttribute("loginMember");
		orderService.saveOrder(courseid,loginMember);
		CourseBean courseBean = courseServicer.findCourse(Integer.valueOf(courseid)).get();
		int r =(int)(Math.random()*1000+1);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String orderNo = Long.toString(UUID.randomUUID().getMostSignificantBits(),19);
		System.err.println(orderNo.replace("-", ""));
		AllInOne all= new AllInOne("");
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(orderNo.replace("-", ""));
		obj.setMerchantTradeDate(sdf.format(new Date()));
		obj.setTotalAmount(String.valueOf(courseBean.getPrice()));
		obj.setTradeDesc("test Description");
		obj.setItemName(courseBean.getCourseName());
		obj.setReturnURL("http://localhost:8080/user/ordersDetail/"+courseid);
		obj.setClientBackURL("http://localhost:8080/user/orders/");
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
		
		
//		@GetMapping("/OrderType/{courseName}")
//	    public ResponseEntity<List<Order>> findByType(@PathVariable String courseName) {
//	        List<Order> OrderTypeList = null;
//	        if ("全部".equals(courseName)) {
//	        	OrderTypeList = orderService.getAllOrders();
//	        } else {
//	        	OrderTypeList = orderService.findByCourseBeanCourseName(courseName);
//	        }
//	        return ResponseEntity.status(HttpStatus.OK).body(OrderTypeList);
//	    }
		
//		 @GetMapping("/orders")
//		    public String findAllorder(@RequestParam(required = false) String courseCategory,
//		                                @RequestParam(required = false) Integer orderId,
//		                                Model model) {
//		        if (orderId != null) {
//		            Optional<Order> findOrder = orderService.getOrder(orderId);
//		            model.addAttribute("CourseBean", findOrder.orElseThrow());
//		            return "order/order_listing";
//		        } 
//		        else if (courseCategory != null) {
//		            List<Order> orderList = orderService.findByCourseBeanCourseName(courseCategory);
//		            model.addAttribute("courseCategory", courseCategory);
//		            model.addAttribute("orderList", orderList);
//		            return "order/order_listing";
//		        } 
//		        else {
//		            List<Order> orderList = orderService.findByState("已付款");
//		            model.addAttribute("courseList", orderList);
//		            return "order/order_listing";
//		        }
//		    }
		
		@GetMapping("/order")
		public String x(@RequestParam String courseCategory,Model model) {
			List<Order> findByCourseBeanCourseCategory = orderService.findByCourseBeanCourseCategory(courseCategory);
			model.addAttribute("orders",findByCourseBeanCourseCategory);
			return  "order/order_details";
		}

}
