package com.theone.springboot.service;

import java.util.List;

import com.theone.springboot.entity.CommentMessage;

public interface CommentMessageService {
	
	List<CommentMessage> findAll();
	
	CommentMessage saveOrUpdate(CommentMessage commentMessage);
	
	void deleteByMessageId(Integer messageId);

	List<CommentMessage> findByCommentCommentId(Integer comment_id);
	
	void deleteByCommentCommentId(Integer comment_id);
	
}
