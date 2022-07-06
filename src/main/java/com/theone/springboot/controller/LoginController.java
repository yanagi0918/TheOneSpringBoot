package com.theone.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.theone.springboot.entity.Company;
import com.theone.springboot.service.CompanyService;

@Controller
public class LoginController {

	@Autowired
	CompanyService companyService;

	@GetMapping("/login")
	public String toLoginPage() {
		return "login/login";
	}

	@PostMapping("/memberLogin")
	public String memberLogin(String username, String password, HttpSession session) {
		if ("admin".equals(username) && "12345".equals(password)) {
			session.setAttribute("loginUser", username);
			return "redirect:dashboard/members";
		}
		return "redirect:login";
	}

	@PostMapping("/companyLogin")
	public String companyLogin(String username, String password, HttpSession session) {
		Integer compId = Integer.parseInt(username);
		Company company = companyService.getByCompid(compId);
		if (company != null && password.equals(company.getCompwd())) {
			session.setAttribute("loginEnterprise", company);
			return "redirect:enterprise/events";
		}
		return "redirect:login";
	}

	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
