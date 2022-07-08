package com.theone.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer> {

	List<Resume> findByUserId(String userId);
}
