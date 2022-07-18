package com.theone.springboot.service.impl;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theone.springboot.entity.Member;
import com.theone.springboot.entity.Resume;
import com.theone.springboot.repository.ResumeDao;
import com.theone.springboot.service.ResumeService;
import com.theone.springboot.utils.ResumeCsvExporter;



@Service
@Transactional
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	
	@Autowired
	ResumeCsvExporter csvExporter;
	
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

	@Override
	public List<Resume> findByUserId(String userId) {
		return resumeDao.findByUserId(userId);
	}

	
	
	@Override
	public void csvExport(Writer writer) {
		List<Resume> resumes = resumeDao.findAll();
		csvExporter.csvExport(writer, resumes);
	}
}