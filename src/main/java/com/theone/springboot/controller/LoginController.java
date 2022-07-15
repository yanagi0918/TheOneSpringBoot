package com.theone.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CompanyService;
import com.theone.springboot.service.MemberService;

@Controller
public class LoginController {

	@Autowired
	CompanyService companyService;
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/login")
	public String toLoginPage() {
		return "login/login";
	}

	@PostMapping("/memberLogin")
	public String memberLogin(String username, String password, HttpSession session) {
		Member member = memberService.getByUserid(username);
		if (member != null && password.equals(member.getPwd())) {
			session.setAttribute("loginMember", member);
			return "redirect:/";
		}

		if ("admin".equals(username) && "12345".equals(password)) {
			session.setAttribute("loginAdmin", username);
			return "redirect:dashboard/members";
		}
		return "redirect:login";
	}

	@PostMapping("/companyLogin")
	public String companyLogin(String username, String password, HttpSession session, Model model) {
		Integer compId = Integer.parseInt(username);
		Company company = companyService.getByCompid(compId);
		if (company != null && password.equals(company.getCompwd())) {
			session.setAttribute("loginEnterprise", company);
			return "redirect:/";
		}
		return "redirect:login";
	}
	
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
