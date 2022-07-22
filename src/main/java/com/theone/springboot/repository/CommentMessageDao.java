package com.theone.springboot.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theone.springboot.entity.CommentMessage;

@Repository
public interface CommentMessageDao extends JpaRepository<CommentMessage, Integer> {

	List<CommentMessage> findByCommentCommentId(Integer comment_id);
	
	List<CommentMessage> findByMemberIdNumber(Integer idNumber);
	
	List<CommentMessage> findByMessageReply(Integer messageReply);
	
	List<CommentMessage> findByCommentCommentIdAndMessageReplyOrderByMessageIdDesc(Integer comment_id, Integer messageReply);

	@Transactional
	void deleteByCommentCommentId(Integer comment_id);
	
	@Transactional
	void deleteByMemberIdNumber(Integer idNumber);
	
	List<CommentMessage> findAllByOrderByMessageIdDesc();

	
}
