package com.theone.springboot.controller;

import java.io.IOException;
import java.util.Random;

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
		// 存進資料庫
		// 放進Session
		// 當session不是空的時候，才能離開登入畫面

		return "company/company_update";
	}

	@PostMapping("/enterprise/companydetail")
	public String processToupdate(HttpSession session, Company company, Model m) {
		company = (Company) session.getAttribute("loginEnterprise");
		Integer compid = company.getCompid();
		Company companydetail = companyService.getByCompid(compid);
		m.addAttribute("company", companydetail);
		return "company/company_update";
	}

	@GetMapping("/enterprise/companydetail")
	public String processToDetail(HttpSession session, Company company, Model m) {
		company = (Company) session.getAttribute("loginEnterprise");
		Integer compid = company.getCompid();
		Company companydetail = companyService.getByCompid(compid);
		m.addAttribute("company", companydetail);
		return "company/company_detail";
	}

	@PostMapping("/enterprise/company")
	public String update(HttpSession session, Company company) throws IllegalStateException, IOException {
		Company newcompany = companyService.saveOrUpdate(company); // 前端傳來的參數(新的company)接值存進資料庫，命名為newcompany
		session.setAttribute("loginEnterprise", newcompany);
		return "company/company_detail";
	}

	@GetMapping("/forgetcompanypwd")
	public String forgetcompanypwd() {
		return "company/forgetcompanypwd";
	}

	@ResponseBody
	@PostMapping("/forgetcompanypwd")
	public String sendNewPwd(@RequestParam Integer compid, @RequestParam String website, Company company) {
		if (companyService.getByCompid(compid).getCompid().equals(compid)
				&& companyService.getByWebsite(website).getWebsite().equals(website)) {

			company = companyService.getByCompid(compid);
			String randomPwd = getRandomPwd(10);
			company.setCompwd(randomPwd);
			System.out.println(randomPwd);
			String msg = "帳號: " + compid + "\n" + "臨時密碼:" + randomPwd + "\n" + "登入後記得更改成新密碼" + "\n" + "重新登入:   "
					+ "http://localhost:8080/login";
			companyService.sendNewPwd(website, "找回密碼信件", msg);
			return "新密碼已發送至公司信箱";
		} else {
			return "帳號或信箱錯誤";
		}
	}

	private String getRandomPwd(int len) {
		Random r = new Random();
		r.nextInt(62);
		String s = "";
		char c = 'x';
		for (int i = 0; i < len; i++) {
			int no = r.nextInt(62);
			if (no < 26) {
				c = (char) ('a' + no);
			} else if (no < 52) {
				c = (char) ('A' + no - 26);
			} else {
				c = (char) ('0' + no - 52);
			}
			s = s + c;
		}
		return s;
	}
}