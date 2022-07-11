package com.theone.springboot.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Comment_Table")
@Component
public class Comment{

	@Id
	@Column(name = "share_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer share_id;
	
	@Column(name = "ref_time", columnDefinition = "Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ref_time;
	
	@Column(name = "create_time", columnDefinition = "Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date create_time;
	
	@Column(name = "comp_name")
	private String comp_name;
	
	@Column(name = "comp_score")
	private Integer comp_score;
	
	@Column(name = "job_name")
	private String job_name;
	
	@Column(name = "job_score")
	private Integer job_score;
	
	@Column(name = "job_description")
	private String job_description;
	
	@Column(name = "std_hour")
	private Integer std_hour;
	
	@Column(name = "real_hour")
	private Integer real_hour;
	
	@Column(name = "over_freq")
	private Integer over_freq;
	
	@Column(name = "seniority")
	private Float seniority;
	
	@Column(name = "total_seniority")
	private Float total_seniority;
	
	@Column(name = "monthly_salary")
	private Integer monthly_salary;
	
	@Column(name = "yearly_salary")
	private Integer yearly_salary;
	
	@Column(name = "bonus_count")
	private Integer bonus_count;
	
	@Column(name = "share")
	private String share;
	
	@Column(name = "userId")
	private String userId;


	public Comment() {
	}

	public Comment(Integer share_id, Date ref_time,  Date create_time, String comp_name, Integer comp_score, String job_name, Integer job_score,
			String job_description, Integer std_hour, Integer real_hour, Integer over_freq, Float seniority, Float total_seniority,
			Integer monthly_salary, Integer yearly_salary, Integer bonus_count, String share, String userId) {
		this.share_id = share_id;
		this.ref_time = ref_time;
		this.create_time = create_time;
		this.comp_name = comp_name;
		this.comp_score = comp_score;
		this.job_name = job_name;
		this.job_score = job_score;
		this.job_description = job_description;
		this.std_hour = std_hour;
		this.real_hour = real_hour;
		this.over_freq = over_freq;
		this.seniority = seniority;
		this.total_seniority = total_seniority;
		this.monthly_salary = monthly_salary;
		this.yearly_salary = yearly_salary;
		this.bonus_count = bonus_count;
		this.share = share;
		this.userId = userId;

	}

	public Comment(Date ref_time, Date create_time, String comp_name, Integer comp_score, String job_name, Integer job_score,
			String job_description, Integer std_hour, Integer real_hour, Integer over_freq, Float seniority, Float total_seniority,
			Integer monthly_salary, Integer yearly_salary, Integer bonus_count, String share, String userId) {
		this.ref_time = ref_time;
		this.create_time = create_time;
		this.comp_name = comp_name;
		this.comp_score = comp_score;
		this.job_name = job_name;
		this.job_score = job_score;
		this.job_description = job_description;
		this.std_hour = std_hour;
		this.real_hour = real_hour;
		this.over_freq = over_freq;
		this.seniority = seniority;
		this.total_seniority = total_seniority;
		this.monthly_salary = monthly_salary;
		this.yearly_salary = yearly_salary;
		this.bonus_count = bonus_count;
		this.share = share;
		this.userId = userId;

	}

	public Comment(Integer share_id) {
		this.share_id = share_id;
	}

	public Integer getShare_id() {
		return share_id;
	}

	public void setShare_id(Integer share_id) {
		this.share_id = share_id;
	}

	public Date getRef_time() {
		return ref_time;
	}

	public void setRef_time(Date ref_time) {
		this.ref_time = ref_time;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getComp_name() {
		return comp_name;
	}

	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}

	public Integer getComp_score() {
		return comp_score;
	}

	public void setComp_score(Integer comp_score) {
		this.comp_score = comp_score;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public Integer getJob_score() {
		return job_score;
	}

	public void setJob_score(Integer job_score) {
		this.job_score = job_score;
	}

	public String getJob_description() {
		return job_description;
	}

	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}

	public Integer getStd_hour() {
		return std_hour;
	}

	public void setStd_hour(Integer std_hour) {
		this.std_hour = std_hour;
	}

	public Integer getReal_hour() {
		return real_hour;
	}

	public void setReal_hour(Integer real_hour) {
		this.real_hour = real_hour;
	}

	public Integer getOver_freq() {
		return over_freq;
	}

	public void setOver_freq(Integer over_freq) {
		this.over_freq = over_freq;
	}

	public Float getSeniority() {
		return seniority;
	}

	public void setSeniority(Float seniority) {
		this.seniority = seniority;
	}

	public Float getTotal_seniority() {
		return total_seniority;
	}

	public void setTotal_seniority(Float total_seniority) {
		this.total_seniority = total_seniority;
	}

	public Integer getMonthly_salary() {
		return monthly_salary;
	}

	public void setMonthly_salary(Integer monthly_salary) {
		this.monthly_salary = monthly_salary;
	}

	public Integer getYearly_salary() {
		return yearly_salary;
	}

	public void setYearly_salary(Integer yearly_salary) {
		this.yearly_salary = yearly_salary;
	}

	public Integer getBonus_count() {
		return bonus_count;
	}

	public void setBonus_count(Integer bonus_count) {
		this.bonus_count = bonus_count;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "CommentBean [share_id=" + share_id + ", ref_time=" + ref_time + ", create_time=" + create_time + ", comp_name" + comp_name + ", comp_score"
				+ comp_score + ", job_name" + job_name + ", job_score" + job_score + ", job_description"
				+ job_description + ", std_hour" + std_hour + ", real_hour" + real_hour + ", over_freq" + over_freq
				+ ", seniority" + seniority + ", total_seniority" + total_seniority + ", monthly_salary"
				+ monthly_salary + ", yearly_salary" + yearly_salary + ", bonus_count" + bonus_count + ", share" + share
				+ ", userId" + userId + "]";

	}

}
