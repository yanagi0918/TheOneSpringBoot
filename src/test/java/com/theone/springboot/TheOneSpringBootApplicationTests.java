package com.theone.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.theone.springboot.entity.Member;
import com.theone.springboot.repository.MemberDao;

import antlr.collections.List;

@SpringBootTest
class TheOneSpringBootApplicationTests {
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void testJPA() {
		Member users = memberDao.getByUserid("Z200000000");
			System.out.println(users);
	}

	@Test
	void contextLoads() {
	}

}
