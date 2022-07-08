package com.theone.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Resume_Table")
@Component
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer resume_id;
	private String resume_name;
	private String edu;
	private String school;
	private String dept;
	private String autobiography;
	private String work_exp;
	private String skills;
	private String userId;
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Integer idNumber;
	
	public Resume() {
	}

	public Integer getResume_id() {
		return resume_id;
	}

	public void setResume_id(Integer resume_id) {
		this.resume_id = resume_id;
	}

	public String getResume_name() {
		return resume_name;
	}

	public void setResume_name(String resume_name) {
		this.resume_name = resume_name;
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

	public String getWork_exp() {
		return work_exp;
	}

	public void setWork_exp(String work_exp) {
		this.work_exp = work_exp;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getUser_id() {
		return userId;
	}

	public void setUser_id(String userId) {
		this.userId = userId;
	}

	public Integer getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}

	@Override
	public String toString() {
		return "Resume [resume_id=" + resume_id + ", resume_name=" + resume_name + ", edu=" + edu + ", school=" + school
				+ ", dept=" + dept + ", autobiography=" + autobiography + ", work_exp=" + work_exp + ", skills="
				+ skills + ", userId=" + userId + ", idNumber=" + idNumber + "]";
	}

}
