package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Resume;

public interface ResumeDao extends JpaRepository<Resume, Integer> {

}
