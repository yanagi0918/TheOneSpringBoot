package com.theone.springboot.entity;
import java.io.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name = "Job_Table")
public class Job implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer job_id;
	private String title;
	private String job_description;
	private String qualification;
	private Integer required_number;
	private String salary;
	private String comp_id;
	public Job() {}
	
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

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
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

	public String getComp_id() {
		return comp_id;
	}

	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}

	public Job(Integer job_id, String title, String job_description, String qualification, Integer required_number,
			String salary, String comp_id) {
		super();
		this.job_id = job_id;
		this.title = title;
		this.job_description = job_description;
		this.qualification = qualification;
		this.required_number = required_number;
		this.salary = salary;
		this.comp_id = comp_id;
	}
	public Job(String title, String job_description, String qualification, Integer required_number, String salary,
			String comp_id) {
		super();
		this.title = title;
		this.job_description = job_description;
		this.qualification = qualification;
		this.required_number = required_number;
		this.salary = salary;
		this.comp_id = comp_id;
	}
	public Job(Integer job_id) {
		super();
		this.job_id = job_id;
	}
	
	
}