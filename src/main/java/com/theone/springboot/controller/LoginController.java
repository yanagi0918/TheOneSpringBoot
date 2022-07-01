package com.theone.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String toLoginPage() {
		return "login/login";
	}
	
	@PostMapping("/login")
	public String loginToDashboard(String username, String password, HttpSession session) {
		System.out.println(username);
		System.out.println(password);
		if ("admin".equals(username) && "12345".equals(password)) {
			session.setAttribute("loginUser", username);
			return "redirect:dashboard/members";
		}
		return "redirect:login";
	}
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

}
