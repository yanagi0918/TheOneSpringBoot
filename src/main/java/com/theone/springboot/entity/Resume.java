package com.theone.springboot.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	
	@Fetch(value = FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	        name="T_JOB_RESUME",
	        joinColumns={@JoinColumn(name="RESUME_FK")},
	        inverseJoinColumns={@JoinColumn(name="JOB_FK")}
	    )
    private Set<Job> collectionJobs = new HashSet<Job>();
	
	
	
	
	public Set<Job> getCollectionJobs() {
		return collectionJobs;
	}



	public void setCollectionJobs(Set<Job> collectionJobs) {
		this.collectionJobs = collectionJobs;
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
