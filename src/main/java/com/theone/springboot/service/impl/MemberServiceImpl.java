package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Member;
import com.theone.springboot.repository.MemberDao;
import com.theone.springboot.service.MemberService;


@Service
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean isDup(Integer pk) {
		return memberDao.existsById(pk);
	}

	@Override
	public Member saveOrUpdate(Member member) {
		return memberDao.save(member);
	}

	@Override
	public List<Member> getAllMembers() {
		return memberDao.findAll();
	}

	@Override
	public Optional<Member> getMember(Integer pk){
		return memberDao.findById(pk);
	}

	@Override
	public void deleteMember(Integer pk) {
		memberDao.deleteById(pk);
	}

	@Override
	public Member getByUserid(String userid) {
		return memberDao.getByUserid(userid);
	}


	
}
