package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
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
	
	@Autowired
	JavaMailSender mailSender;
	
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


	
	
	
	@Override
	public void sendNotifyEmail(String recipient, String subject, String message) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("eeit45theone@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject(subject);
			messageHelper.setText(message);
		};
		try {
			mailSender.send(messagePreparator);
			 System.out.println("sent");
		} catch (MailException e) {
			 System.out.println(e);
		}
	}


	
}
