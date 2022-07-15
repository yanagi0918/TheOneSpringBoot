package com.theone.springboot.entity;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@SuppressWarnings("serial")
@Entity
@Table(name = "Job_Table")
public class Job implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer jobid;
	private String title;
	private String jobdescription;
	private String qualification;
	private Integer required_number;
	private String salary;
	@Column(length = 1000)
	private String description;
	private Integer compId;
	

	@JsonIgnore
	@ManyToOne
	@JoinColumn
	private Company company;
	
	@ManyToMany(cascade=CascadeType.ALL , fetch = FetchType.EAGER , mappedBy="collectionJobs")
	
	    private Set<Member> collectonJobMembers = new HashSet<Member>();
	
	
	
	

	public Set<Member> getCollectonJobMembers() {
		return collectonJobMembers;
	}

	public void setCollectonJobMembers(Set<Member> collectonJobMembers) {
		this.collectonJobMembers = collectonJobMembers;
	}

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

	public Integer getJobid() {
		return jobid;
	}

	public void setJobid(Integer jobid) {
		this.jobid = jobid;
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

	public Job(Integer jobid) {
		super();
		this.jobid = jobid;
	}
	
	
}