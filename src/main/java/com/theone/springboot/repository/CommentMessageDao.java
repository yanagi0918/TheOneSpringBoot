package com.theone.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.theone.springboot.entity.CommentMessage;

public interface CommentMessageDao extends JpaRepository<CommentMessage, Integer> {

	List<CommentMessage> findByCommentCommentId(Integer comment_id);

	@Transactional
	void deleteByCommentCommentId(Integer comment_id);
}
