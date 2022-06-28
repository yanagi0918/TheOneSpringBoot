package com.theone.springboot.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.theone.springboot.entity.Company;
import com.theone.springboot.service.CompanyService;


@RequestMapping("/company")
@Controller
public class CompanyServlet {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/list")
	public String listCompanies(Model m) {
		List<Company> companies = companyService.getAllCompanies();
		m.addAttribute("companies",companies);
		return "company_dashboard/companylist";
	}
	
	@GetMapping("/showForm")
    public String showFormForAdd(Model m) {
        return "company_dashboard/companycreate";
    }
	
	@PostMapping("/saveCompany")
	public String saveCustomer(@ModelAttribute("company") Company company ,Model m) {
		
		//設定輸入錯誤
		Map<String, String> errorMsg = new HashMap<String, String>();
		m.addAttribute("error", errorMsg);
		//讀取資料
		int compid=company.getCompid();
		
		//判斷新增是否錯誤
		if(companyService.isDup(compid)) {
			errorMsg.put("compid", "帳號(統編)重複，請重新輸入新帳號");
		}
		
		if(!errorMsg.isEmpty()){
			return "CompanyCreate";
		}
		companyService.saveOrUpdate(company);
		return "redirect:/company/list";
	}
	
	@GetMapping("/companydeatail/{pk}")
	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m){
		Company companydeatail = companyService.getCompany(detailId).get();
		m.addAttribute("companydeatail",companydeatail);
		return "company_dashboard/companydetail";
	}
	@GetMapping("/showupdateinformation/{pk}")
	public String showInformaionFromUpdate(@PathVariable("pk") Integer updateId,Model m){
		Company companyupdate = companyService.getCompany(updateId).get();
		m.addAttribute("companyupdate",companyupdate);
		return "company_dashboard/companyUpdate";
	}
	
	@GetMapping("/delete/{pk}")
	public String processDelete(@PathVariable("pk") Integer deleteId){
		companyService.deleteCompany(deleteId);
		return "redirect:/company/list";
	}
	
}
