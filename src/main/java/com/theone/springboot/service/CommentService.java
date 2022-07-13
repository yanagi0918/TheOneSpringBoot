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
	
	List<Comment> findByUserId(String userId);
	
}