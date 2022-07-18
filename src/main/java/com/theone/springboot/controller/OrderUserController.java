package com.theone.springboot.controller;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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
	        model.addAttribute("courseList", courseServicer.findAllCourses());
	        model.addAttribute("orders", orderList);
	        model.addAttribute("total",orderList.size());
	        return "order/order_listing";
	 }
	
//	 @PostMapping("/orders")
//	    public String saveOrUpdate(Order Order, @RequestParam("imgURL") MultipartFile mf, HttpSession session) throws IOException {
//	        System.out.println(Order);
//	        Member loginUser = (Member) session.getAttribute("loginMember");
//	        File imageFile = new File(System.currentTimeMillis() + "_" + mf.getOriginalFilename());
//	        File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFile.getName());
//	        if (mf.getOriginalFilename().length() != 0) {
//	            mf.transferTo(savedFile);
//	        }
//	        Order.setUserId(loginUser.getUserid());
//	        Order.setMember(loginUser);
//	        orderService.saveOrUpdate(Order);
//	        return "redirect:/user/orders";
//	    }
//	 
//	 public File uploadDirInit() {
//	        String savedFilePath = new File("target\\classes\\static\\orderImg\\").getAbsolutePath();
//	        File uploadDir = new File(savedFilePath);
//	        if (!uploadDir.exists()) {
//	            uploadDir.mkdirs();
//	        }
//	        return uploadDir;
//	    }
	
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
		
		
		//查詢課程分類
		@GetMapping("/OrderCategory")
		public String OrderCategory(@RequestParam String courseCategory,Model model,HttpServletRequest request) {
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			List<Order> orders = orderService.findByCourseBeanCourseCategoryAndMember(courseCategory,loginMember);
			model.addAttribute("courseList", courseServicer.findAllCourses());
			model.addAttribute("courseCategory",courseCategory);
			model.addAttribute("loginMember",loginMember);
			model.addAttribute("orders",orders);
			model.addAttribute("total",orders.size());
			return  "order/order_listing";
		}
		
		//查詢訂單ID
		@GetMapping("/OrderSearch")
		public String OrderSearch(Integer orderId,Model model,HttpServletRequest request) {
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			List<Order> orders = orderService.findByOrderIdAndMember(orderId,loginMember);
			model.addAttribute("courseList", courseServicer.findAllCourses());
			model.addAttribute("loginMember",loginMember);
			model.addAttribute("orders",orders);
			model.addAttribute("orderId",orderId);
			model.addAttribute("total",orders.size());
			return  "order/order_listing";
		}
		
		//查詢狀態
		@GetMapping("/OrderState")
		public String OrderState(@RequestParam String state,Model model,HttpServletRequest request) {
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			List<Order> orders = orderService.findByStateAndMember(state, loginMember);
			model.addAttribute("courseList", courseServicer.findAllCourses());
			model.addAttribute("state",state);
			model.addAttribute("loginMember",loginMember);
			model.addAttribute("orders",orders);
			model.addAttribute("total",orders.size());
			return  "order/order_listing";
		}
		
		//查詢課程
		@GetMapping("/OrderCourseName")
		public String OrderCourseName(@RequestParam String courseName,Model model,HttpServletRequest request) {
			Member loginMember = (Member) request.getSession().getAttribute("loginMember");
			List<Order> orders = orderService.findByCourseBeanCourseNameAndMember(courseName, loginMember);
			model.addAttribute("courseList", courseServicer.findAllCourses());
			model.addAttribute("courseName",courseName);
			model.addAttribute("loginMember",loginMember);
			model.addAttribute("orders",orders);
			model.addAttribute("total",orders.size());
			return  "order/order_listing";
		}
	
		//審核
		@PutMapping("/orders")
	    @ResponseBody
	    public ResponseEntity<Order> saveOrUpdate(@RequestBody Order Order) {
			Order orders = orderService.findOrder(Order.getOrderId()).orElseThrow();
			orders.setState(Order.getState());
	        orderService.saveOrUpdate(orders);
	        return ResponseEntity.status(HttpStatus.OK).body(orders);
	    }
		
		
}
