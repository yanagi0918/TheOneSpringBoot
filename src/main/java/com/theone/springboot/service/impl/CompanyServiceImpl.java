package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Company;
import com.theone.springboot.repository.CompanyDao;
import com.theone.springboot.service.CompanyService;

@Transactional
@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	private CompanyDao companyDao;
	
	
	@Override
	public boolean isDup(Integer compid) {
		
		return companyDao.findById(compid) != null;
	}
	@Override
	public Company saveOrUpdate(Company company) {
		return companyDao.save(company);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		
		return companyDao.findAll();
	}
	
	@Override
	public Optional<Company> getCompany(Integer pk) {
		
		return companyDao.findById(pk);
	}
	
	@Override
	public void deleteCompany(Integer pk) {
		
		companyDao.deleteById(pk);
	}
	@Override
	public Company getByCompid(Integer compid) {
		
		return companyDao.getByCompid(compid);
	}

}
