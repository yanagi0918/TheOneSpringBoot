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
	
	List<Comment> findByCompNameLike(String compName);
	
	List<Comment> findByJobNameLike(String jobName);
	
	List<Comment> findByJobDescriptionLike(String jobDescription);
	
	List<Comment> findAllByOrderByCommentIdDesc();
	
}
