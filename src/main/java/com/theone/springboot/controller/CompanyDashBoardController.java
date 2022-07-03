package com.theone.springboot.controller;
import java.util.List;

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
import com.theone.springboot.service.CompanyService;


@RequestMapping("/dashboard/company")
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
	public String saveCustomer(@ModelAttribute("company") Company company,Model m) {
		companyService.saveOrUpdate(company);
		return "redirect:/dashboard/company/list";
	}
	
	@PostMapping(value = "/CheckUser")
	public @ResponseBody boolean checkUser(@RequestParam Integer compid) {
		Company company = companyService.getByCompid(compid);
		if (company == null) {
			return true;
		}
		return false;
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
	
	@ResponseBody
	@DeleteMapping("/delete/{id}")
	public String processDelete(@PathVariable("id") Integer id){
		companyService.deleteCompany(id);
		return "ok";
	}
	
}
