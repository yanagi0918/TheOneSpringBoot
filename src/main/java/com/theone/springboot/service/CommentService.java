package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Comment;

public interface CommentService {

	Comment saveOrUpdate(Comment comment);

	boolean existsById(Integer pk);

	List<Comment> findAll();

	Optional<Comment> findById(Integer pk);

	void deleteById(Integer pk);

	List<Comment> findByCommentMemberIdNumber(Integer idNumber);

	void deleteByCommentMemberIdNumber(Integer idNumber);

	List<Comment> findByCompNameLike(String compName);

	List<Comment> findByJobNameLike(String jobName);
	
	List<Comment> findByJobDescriptionLike(String jobDescription);
	
	List<Comment> findAllByOrderByCommentIdDesc();
	

}
