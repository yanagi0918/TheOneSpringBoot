//package com.theone.springboot.controller;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.theone.springboot.entity.Company;
//import com.theone.springboot.service.CompanyService;
//
//
//@RequestMapping("/enterprise/company")
//@Controller
//public class CompanyController {
//	
//	@Autowired
//	private CompanyService companyService;
//	
////	@GetMapping("/list")
////	public String listCompanies(Model m) {
////		List<Company> companies = companyService.getAllCompanies();
////		m.addAttribute("companies",companies);
////		return "company/companylist";
////	}
//	
//	@GetMapping("/showForm")
//    public String showFormForAdd(Model m) {
//        return "company/companycreate";
//    }
//	
//	@PostMapping("/saveCompany")
//	public String saveCustomer(@ModelAttribute("company") Company company,Model m) {
//		companyService.saveOrUpdate(company);
//		return "redirect:/enterprise/company/companydeatail";
//	}
//	
//	@PostMapping(value = "/CheckUser")
//	public @ResponseBody boolean checkUser(@RequestParam Integer compid) {
//		Company company = companyService.getByCompid(compid);
//		if (company == null) {
//			return true;
//		}
//		return false;
//	}
//	
//	@GetMapping("/companydeatail/{pk}")
//	public String processShowDetail(@PathVariable("pk") Integer detailId,Model m){
//		Company companydeatail = companyService.getCompany(detailId).get();
//		m.addAttribute("companydeatail",companydeatail);
//		return "company_detail";
//	}
//	@GetMapping("/showupdateinformation/{pk}")
//	public String showInformaionFromUpdate(@PathVariable("pk") Integer updateId,Model m){
//		Company companyupdate = companyService.getCompany(updateId).get();
//		m.addAttribute("companyupdate",companyupdate);
//		return "company_update";
//	}
//	
//	@ResponseBody
//	@DeleteMapping("/delete/{id}")
//	public String processDelete(@PathVariable("id") Integer id){
//		companyService.deleteCompany(id);
//		return "ok";
//	}
//	
//}
