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
		m.addAttribute("total",companies.size());
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
	
	//統計圖
		@ResponseBody
		@GetMapping("/companychartdata")
		public int[] getChartData() {
			int[] chartdata = {0,0,0,0,0,0,0,0,0,0,0,0,0};
			List<Company> allCompanies = companyService.getAllCompanies();
			for (Company company : allCompanies) {
				switch (company.getIndustry()) {
				case "金融業": chartdata[0]++; break;
				case "科技業": chartdata[1]++; break;
				case "文教相關": chartdata[2]++; break;
				case "餐飲服務": chartdata[3]++; break;
				case "旅遊相關": chartdata[4]++; break;
				case "運輸倉儲": chartdata[5]++; break;
				case "一般服務業": chartdata[6]++; break;
				case "建築及不動產": chartdata[7]++; break;
				case "政治及宗教相關": chartdata[8]++; break;
				case "醫療保健及環境衛生": chartdata[9]++; break;
				case "礦業及土石開發採取": chartdata[10]++; break;
				case "批發零售及傳直銷產業": chartdata[11]++; break;
				case "法律/會計/顧問/研發/設計業": chartdata[12]++; break;
				default: break;
				}
			}
			return chartdata;
		}
		
}
