package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Member;

public interface MemberService {

	boolean isDup(Integer pk);

	Member saveOrUpdate(Member member);

	List<Member> getAllMembers();

	Optional<Member> getMember(Integer pk);

	void deleteMember(Integer pk);
	
	Member getByUserid(String userid);
	
	
	
}
