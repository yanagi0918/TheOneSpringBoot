package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.Job;

public interface JobDao extends JpaRepository<Job, Integer>{
}
