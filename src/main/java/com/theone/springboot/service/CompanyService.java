package com.theone.springboot.service;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Company;


public interface CompanyService {
	
	boolean isDup(Integer compid);
	
	Company saveOrUpdate(Company company);
	
	List<Company> getAllCompanies();
	
	Optional<Company> getCompany(Integer pk);
	
	void deleteCompany(Integer pk);
	
	Company getByCompid(Integer compid);
	
	Company getByWebsite(String website);
	
	public void sendNewPwd(String recipient, String subject, String message);
	
	void csvExport(Writer writer);
}
