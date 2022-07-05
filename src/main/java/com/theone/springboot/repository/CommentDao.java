package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.theone.springboot.entity.Comment;


@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {



}
