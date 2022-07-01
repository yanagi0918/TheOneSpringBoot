package com.theone.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/hello")
	public String testHello(Model model, String name) {
		model.addAttribute("message","Hello world");
//		model.addAttribute("message","Hello "+name);
		
		return "test";
	}
	
	@GetMapping("/hello/static")
	public String testStatic() {
		return "test";
	}
	
	

}
