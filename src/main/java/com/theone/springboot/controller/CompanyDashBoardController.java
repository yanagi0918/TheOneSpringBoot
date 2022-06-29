package com.theone.springboot.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.theone.springboot.entity.Company;
import com.theone.springboot.service.CompanyService;


@RequestMapping("/company")
@Controller
public class CompanyDashBoardController {
	
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
	public String saveCustomer(@ModelAttribute("company") Company company) {
		
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
