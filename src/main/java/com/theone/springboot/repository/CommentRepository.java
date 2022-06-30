package com.theone.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.theone.springboot.entity.Comment;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
