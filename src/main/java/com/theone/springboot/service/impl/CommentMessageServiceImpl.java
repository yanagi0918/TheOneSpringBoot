package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.CommentMessage;
import com.theone.springboot.repository.CommentMessageDao;
import com.theone.springboot.service.CommentMessageService;

@Service
@Transactional
public class CommentMessageServiceImpl implements CommentMessageService {
	
	@Autowired
	CommentMessageDao commentmessageDao;
	
	@Override
	public List<CommentMessage> findAll() {
		return commentmessageDao.findAll();
	}
	
	@Override
	public CommentMessage saveOrUpdate(CommentMessage commentMessage) {
		return commentmessageDao.save(commentMessage);
	}
	
	@Override
	public List<CommentMessage> findByCommentCommentId(Integer comment_id) {
		return commentmessageDao.findByCommentCommentId(comment_id);
	}

	@Override
	public void deleteByCommentCommentId(Integer comment_id) {
		commentmessageDao.deleteByCommentCommentId(comment_id);
	}

	@Override
	public void deleteByMessageId(Integer messageId) {
		commentmessageDao.deleteById(messageId);
	}

	@Override
	public Optional<CommentMessage> findById(Integer messageId) {
		return commentmessageDao.findById(messageId);
	}

	@Override
	public List<CommentMessage> findByMemberIdNumber(Integer idNumber) {
		return commentmessageDao.findByMemberIdNumber(idNumber);
	}

	@Override
	public void deleteByMemberIdNumber(Integer idNumber) {
		commentmessageDao.deleteByMemberIdNumber(idNumber);
	}

	@Override
	public List<CommentMessage> findByMessageReply(Integer messageReply) {
		return commentmessageDao.findByMessageReply(messageReply);
	}


}
