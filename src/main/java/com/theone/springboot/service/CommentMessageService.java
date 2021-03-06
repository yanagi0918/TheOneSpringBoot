package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.CommentMessage;

public interface CommentMessageService {
	
	List<CommentMessage> findAll();
	
	CommentMessage saveOrUpdate(CommentMessage commentMessage);
	
	void deleteByMessageId(Integer messageId);

	List<CommentMessage> findByCommentCommentId(Integer comment_id);
	
	void deleteByCommentCommentId(Integer comment_id);
	
	Optional<CommentMessage> findById(Integer messageId);
	
	List<CommentMessage> findByMemberIdNumber(Integer idNumber);
	
	void deleteByMemberIdNumber(Integer idNumber);
	
	List<CommentMessage> findByMessageReply(Integer messageReply);
	
	List<CommentMessage> findByCommentCommentIdAndMessageReplyOrderByMessageIdDesc(Integer comment_id, Integer messageReply);
	
	List<CommentMessage> findAllByOrderByMessageIdDesc();


}
