package com.theone.springboot.entity;
import java.io.*;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Job_Table")
public class Job implements Serializable{
	public Set<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(Set<Resume> resumes) {
		this.resumes = resumes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer job_id;
	private String title;
	private String jobdescription;
	private String qualification;
	private Integer required_number;
	private String salary;
	private String description;
	private Integer compId;

	@ManyToOne
	@JoinColumn
	private Company company;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
	        name="T_JOB_RESUME",
	        joinColumns={@JoinColumn(name="JOB_FK")},
	        inverseJoinColumns={@JoinColumn(name="RESUME_FK")}
	    )
	    private Set<Resume> resumes;
	
	
	
	
	public Integer getCompId() {
		return compId;
	}
	
	public void setCompId(Integer compId) {
		this.compId = compId;
	}
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Job() {}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getJob_id() {
		return job_id;
	}

	public void setJob_id(Integer job_id) {
		this.job_id = job_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Integer getRequired_number() {
		return required_number;
	}

	public void setRequired_number(Integer required_number) {
		this.required_number = required_number;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Job(Integer job_id) {
		super();
		this.job_id = job_id;
	}
	
	
}