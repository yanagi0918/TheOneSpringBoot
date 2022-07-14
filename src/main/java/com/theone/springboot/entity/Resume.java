package com.theone.springboot.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Resume_Table")
@Component
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resumeId;
	private String resumeName;
	private String edu;
	private String school;
	private String dept;
	private String autobiography;
	private String workExp;
	private String skills;
	private String userId;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="resumes")
    private Set<Job> jobs;
	
	
	public Set<Job> getJobs() {
		return jobs;
	}
	
	
	
	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}
	
	
	public Integer getResumeId() {
		return resumeId;
	}



	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}



	public String getResumeName() {
		return resumeName;
	}



	public void setResumeName(String resumeName) {
		this.resumeName = resumeName;
	}



	public String getEdu() {
		return edu;
	}



	public void setEdu(String edu) {
		this.edu = edu;
	}



	public String getSchool() {
		return school;
	}



	public void setSchool(String school) {
		this.school = school;
	}



	public String getDept() {
		return dept;
	}



	public void setDept(String dept) {
		this.dept = dept;
	}



	public String getAutobiography() {
		return autobiography;
	}



	public void setAutobiography(String autobiography) {
		this.autobiography = autobiography;
	}



	public String getWorkExp() {
		return workExp;
	}



	public void setWorkExp(String workExp) {
		this.workExp = workExp;
	}



	public String getSkills() {
		return skills;
	}



	public void setSkills(String skills) {
		this.skills = skills;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	@Override
	public String toString() {
		return "Resume [resumeId=" + resumeId + ", resumeName=" + resumeName + ", edu=" + edu + ", school=" + school
				+ ", dept=" + dept + ", autobiography=" + autobiography + ", workExp=" + workExp + ", skills=" + skills
				+ ", userId=" + userId + "]";
	}





}
