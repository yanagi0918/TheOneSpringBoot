
package com.theone.springboot.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Interview")
@Component
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cvNo;

	@Column(columnDefinition = "Date")
	private Date intTime;

	@Column(columnDefinition = "TIMESTAMP")
	private Timestamp createTime;

	private String compName;

	private String jobName;

	private String offer;

	private String test;

	private String QA;

	private String share;

	private Integer compScore;

	private String userId;

	public Interview() {
	}

	public Interview(Integer cvNo, Date intTime, Timestamp createTime, String compName, String jobName, String offer,
			String test, String qA, String share, Integer compScore, String userId) {
		super();
		this.cvNo = cvNo;
		this.intTime = intTime;
		this.createTime = createTime;
		this.compName = compName;
		this.jobName = jobName;
		this.offer = offer;
		this.test = test;
		this.QA = qA;
		this.share = share;
		this.compScore = compScore;
		this.userId = userId;
	}

	public Integer getCvNo() {
		return cvNo;
	}

	public void setCvNo(Integer cvNo) {
		this.cvNo = cvNo;
	}

	public Date getIntTime() {
		return intTime;
	}

	public void setIntTime(Date intTime) {
		this.intTime = intTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public String getQA() {
		return QA;
	}

	public void setQA(String qA) {
		QA = qA;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	public Integer getCompScore() {
		return compScore;
	}

	public void setCompScore(Integer compScore) {
		this.compScore = compScore;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Interview [cvNo=" + cvNo + ", intTime=" + intTime + ", createTime=" + createTime + ", compName="
				+ compName + ", jobName=" + jobName + ", offer=" + offer + ", test=" + test + ", QA=" + QA + ", share="
				+ share + ", compScore=" + compScore + ", userId=" + userId + "]";
	}

}