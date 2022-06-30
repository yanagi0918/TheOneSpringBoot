package com.theone.springboot.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.Comment;
import com.theone.springboot.repository.CommentRepository;
import com.theone.springboot.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public boolean existsById (Integer pk) {
		return commentRepository.existsById(pk);
	}

	@Override
	public Comment saveOrUpdate(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public Iterable<Comment> findAll() {
		return commentRepository.findAll();
	}

	@Override
	public Optional<Comment> findById(Integer pk) {
		return commentRepository.findById(pk);
	}

	@Override
	public void deleteById(Integer pk) {
		commentRepository.deleteById(pk);
	}

}
