package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Comment;
import com.theone.springboot.repository.CommentDao;
import com.theone.springboot.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentDao commentDao;

	@Override
	public boolean existsById (Integer pk) {
		return commentDao.existsById(pk);
	}

	@Override
	public Comment saveOrUpdate(Comment comment) {
		return commentDao.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		return commentDao.findAll();
	}

	@Override
	public Optional<Comment> findById(Integer pk) {
		return commentDao.findById(pk);
	}

	@Override
	public void deleteById(Integer pk) {
		commentDao.deleteById(pk);
	}

	@Override
	public List<Comment> findByUserId(String userId) {
		
		return commentDao.findByUserId(userId);
	}

	@Override
	public List<Comment> findByMemberIdNumber(Integer idNumber) {
		return commentDao.findByMemberIdNumber(idNumber);
	}

	@Override
	public void deleteByMemberIdNumber(Integer idNumber) {
		commentDao.deleteByMemberIdNumber(idNumber);
	}

}
