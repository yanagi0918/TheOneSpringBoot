package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Resume;

public interface ResumeService {

	boolean isDup(Integer pk);

	Resume saveOrUpdate(Resume resume);

	List<Resume> getAllResumes();

	Optional<Resume> getResume(Integer pk);

	void deleteResume(Integer pk);
	
	List<Resume> findByUserId(String userId);
	
	
	
}
