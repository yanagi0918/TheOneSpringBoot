package com.theone.springboot.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.theone.springboot.entity.Company;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CompanyService;


//@RequestMapping("/enterprise/company")
@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/enterprise/company/saveCompany")
	public String saveCustomer(@ModelAttribute("company") Company company,Model m) {
		companyService.saveOrUpdate(company);
		return "redirect:/enterprise/company/companydeatail";
	}
	
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
	public String processShowDetail(HttpSession session,Model m,Company company){
		company = companyService.saveOrUpdate(company);
		session.setAttribute("loginEnterprise", company);
		return "company/company_detail";
	}
	
	@GetMapping("/enterprise/companydetail")
	public String processShowDetailCompany(HttpSession session,Model m,Company company){
		company = companyService.saveOrUpdate(company);
		session.setAttribute("loginEnterprise", company);
		return "company/company_detail";
	}
	
	@GetMapping("/enterprise/company/showupdateinformation/{pk}")
	public String showInformaionFromUpdate(@PathVariable("pk") Integer updateId,Model m){
		Company companyupdate = companyService.getCompany(updateId).get();
		m.addAttribute("companyupdate",companyupdate);
		return "company/company_update";
	}
	
	@ResponseBody
	@DeleteMapping("/enterprise/company/delete/{id}")
	public String processDelete(@PathVariable("id") Integer id){
		companyService.deleteCompany(id);
		return "ok";
	}
	
	@PostMapping("/enterprise/company")
	public String update(HttpSession session, Company company) throws IllegalStateException, IOException {
		Company newcompany = companyService.saveOrUpdate(company);  //前端傳來的參數(新的company)接值存進資料庫，命名為newcompany
		session.setAttribute("loginEnterprise", newcompany);		
		return "company/company_update";
	}
	
}
