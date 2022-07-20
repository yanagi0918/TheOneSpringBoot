package com.theone.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theone.springboot.entity.Comment;


@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
	
//	List<Comment> findByUserId(String userId);

	List<Comment> findByCommentMemberIdNumber(Integer idNumber);

	@Transactional
	void deleteByCommentMemberIdNumber(Integer idNumber);
	
//	List<Comment> findByJob_DescriptionAndComp_NameOrJob_Name(String job_description, String comp_name, String job_name);
}
