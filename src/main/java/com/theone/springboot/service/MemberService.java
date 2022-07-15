package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.theone.springboot.entity.Member;

public interface MemberService {

	boolean isDup(Integer pk);

	Member saveOrUpdate(Member member);

	void saveAllAndFlush(Set<Member> members);

	List<Member> getAllMembers();

	Optional<Member> getMember(Integer pk);

	void deleteMember(Integer pk);
	
	Member getByUserid(String userid);
	
	
	
}
