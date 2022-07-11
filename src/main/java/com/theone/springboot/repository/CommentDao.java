package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theone.springboot.entity.Comment;


@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
	
	List<Comment> findByUserId(String userId);


}
