package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theone.springboot.entity.Resume;
import com.theone.springboot.repository.ResumeDao;
import com.theone.springboot.service.ResumeService;



@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	
	@Override
	public boolean isDup(Integer pk) {
		
		return resumeDao.existsById(pk);
	}

	@Override
	public Resume saveOrUpdate(Resume resume) {
		
		return resumeDao.save(resume);
	}

	@Override
	public List<Resume> getAllResumes() {
		
		return resumeDao.findAll();
	}

	@Override
	public Optional<Resume> getResume(Integer pk) {
		
		return resumeDao.findById(pk);
	}

	@Override
	public void deleteResume(Integer pk) {
		resumeDao.deleteById(pk);
	}

}