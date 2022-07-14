package com.theone.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.repository.CommentMessageDao;
import com.theone.springboot.service.CommentMessageService;

public class CommentMessageServiceImpl implements CommentMessageService {
	
	@Autowired
	CommentMessageDao commentmessageDao;
	
	@Override
	public List<CommentMessage> findByCommentCommentId(Integer comment_id) {
		return commentmessageDao.findByCommentCommentId(comment_id);
	}

	@Override
	public void deleteByCommentCommentId(Integer comment_id) {
		commentmessageDao.deleteByCommentCommentId(comment_id);
	}

	@Override
	public CommentMessage saveOrUpdate(CommentMessage commentMessage) {
		return commentmessageDao.save(commentMessage);
	}

}
