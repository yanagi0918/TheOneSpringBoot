package com.theone.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Comment_Table")
@Component
public class Comment {

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	@Column(columnDefinition = "Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date refTime;

	@Column(columnDefinition = "Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

	private String compName;

	private Integer compScore;

	private String jobName;

	private Integer job_score;

	private String jobDescription;

	private Integer std_hour;

	private Integer real_hour;

	private Integer over_freq;

	private Float seniority;

	private Float total_seniority;

	private Integer monthly_salary;

	private Integer yearly_salary;

	private Integer bonus_count;

	@Column(length = 1000)
	private String share;
	
	// 0:刊登中 1:下架 2:撤銷
	private Integer status;

//	private String userId;
	
	private String nickName;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "member_idNumber", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Member commentMember;
	
	public Comment() {
	}

	

	public Comment(Integer commentId, Date refTime, Date createTime, String compName, Integer compScore,
			String jobName, Integer job_score, String jobDescription, Integer std_hour, Integer real_hour,
			Integer over_freq, Float seniority, Float total_seniority, Integer monthly_salary, Integer yearly_salary,
			Integer bonus_count, String share, Integer status, String nickName) {
		this.commentId = commentId;
		this.refTime = refTime;
		this.createTime = createTime;
		this.compName = compName;
		this.compScore = compScore;
		this.jobName = jobName;
		this.job_score = job_score;
		this.jobDescription = jobDescription;
		this.std_hour = std_hour;
		this.real_hour = real_hour;
		this.over_freq = over_freq;
		this.seniority = seniority;
		this.total_seniority = total_seniority;
		this.monthly_salary = monthly_salary;
		this.yearly_salary = yearly_salary;
		this.bonus_count = bonus_count;
		this.share = share;
		this.status = status;
		this.nickName = nickName;
	}


	public Comment(Date refTime, Date createTime, String compName, Integer compScore, String jobName,
			Integer job_score, String jobDescription, Integer std_hour, Integer real_hour, Integer over_freq,
			Float seniority, Float total_seniority, Integer monthly_salary, Integer yearly_salary, Integer bonus_count,
			String share, Integer status, String nickName) {
		this.refTime = refTime;
		this.createTime = createTime;
		this.compName = compName;
		this.compScore = compScore;
		this.jobName = jobName;
		this.job_score = job_score;
		this.jobDescription = jobDescription;
		this.std_hour = std_hour;
		this.real_hour = real_hour;
		this.over_freq = over_freq;
		this.seniority = seniority;
		this.total_seniority = total_seniority;
		this.monthly_salary = monthly_salary;
		this.yearly_salary = yearly_salary;
		this.bonus_count = bonus_count;
		this.share = share;
		this.status = status;
		this.nickName = nickName;
	}

	public Comment(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Date getRefTime() {
		return refTime;
	}

	public void setRefTime(Date refTime) {
		this.refTime = refTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public Integer getCompScore() {
		return compScore;
	}

	public void setCompScore(Integer compScore) {
		this.compScore = compScore;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJob_score() {
		return job_score;
	}

	public void setJob_score(Integer job_score) {
		this.job_score = job_score;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
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
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public Member getCommentMember() {
		return commentMember;
	}

	public void setCommentMember(Member commentMember) {
		this.commentMember = commentMember;
	}
	

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", refTime=" + refTime + ", createTime=" + createTime
				+ ", compName=" + compName + ", compScore=" + compScore + ", jobName=" + jobName + ", job_score="
				+ job_score + ", jobDescription=" + jobDescription + ", std_hour=" + std_hour + ", real_hour="
				+ real_hour + ", over_freq=" + over_freq + ", seniority=" + seniority + ", total_seniority="
				+ total_seniority + ", monthly_salary=" + monthly_salary + ", yearly_salary=" + yearly_salary
				+ ", bonus_count=" + bonus_count + ", share=" + share + ", status=" + status + ", nickName=" + nickName + "]";
	}
}
