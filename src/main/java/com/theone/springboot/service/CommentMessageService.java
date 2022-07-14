package com.theone.springboot.service;

import java.util.List;

import com.theone.springboot.entity.CommentMessage;

public interface CommentMessageService {

	List<CommentMessage> findByCommentCommentId(Integer comment_id);
	
	void deleteByCommentCommentId(Integer comment_id);
	
	CommentMessage saveOrUpdate(CommentMessage commentMessage);
}
