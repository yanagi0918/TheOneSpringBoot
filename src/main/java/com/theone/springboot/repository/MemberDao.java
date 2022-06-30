package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Member;

public interface MemberDao extends JpaRepository<Member, Integer> {

}
