package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Member;

import antlr.collections.List;

public interface MemberDao extends JpaRepository<Member, Integer> {
	
	Member getByUserid(String userid);
	
	

}
