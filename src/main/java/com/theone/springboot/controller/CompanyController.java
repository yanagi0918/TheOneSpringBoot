package com.theone.springboot.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Company;
import com.theone.springboot.service.CompanyService;


//@RequestMapping("/enterprise/company")
@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping(value = "/CheckUser")
	public @ResponseBody boolean checkUser(@RequestParam Integer compid) {
		Company company = companyService.getByCompid(compid);
		if (company == null) {
			return true;
		}
		return false;
	}
	
	@PostMapping("/signupcompany")
	public String signUp(HttpSession session, Company company) throws IllegalStateException, IOException {
		Company newcompany = companyService.saveOrUpdate(company);  
		session.setAttribute("loginEnterprise", newcompany);
		//存進資料庫
		//放進Session
		//當session不是空的時候，才能離開登入畫面	
		
		return "company/company_update";
	}
	
	@PostMapping("/enterprise/companydetail")
	public String processToupdate(HttpSession session,Company company,Model m){
		company = (Company)session.getAttribute("loginEnterprise");
		Integer compid = company.getCompid();
		Company companydetail = companyService.getByCompid(compid);
		m.addAttribute("company", companydetail);
		return "company/company_update";
	}
	
	@GetMapping("/enterprise/companydetail")
	public String processToDetail(HttpSession session,Company company,Model m){
		company = (Company)session.getAttribute("loginEnterprise");
		Integer compid = company.getCompid();
		Company companydetail = companyService.getByCompid(compid);
		m.addAttribute("company", companydetail);
		return "company/company_detail";
	}
	
	
	
	@PostMapping("/enterprise/company")
	public String update(HttpSession session, Company company) throws IllegalStateException, IOException {
		Company newcompany = companyService.saveOrUpdate(company);  //前端傳來的參數(新的company)接值存進資料庫，命名為newcompany
		session.setAttribute("loginEnterprise", newcompany);		
		return "company/company_detail";
	}
	
}
