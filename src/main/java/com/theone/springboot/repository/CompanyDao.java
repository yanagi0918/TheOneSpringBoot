package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Company;
public interface CompanyDao  extends JpaRepository<Company, Integer>{
	Company getByCompid(Integer compid);

}
