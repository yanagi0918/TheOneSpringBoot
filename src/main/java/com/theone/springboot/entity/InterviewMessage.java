package com.theone.springboot.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Interview_Message")
@Component
public class InterviewMessage {
	
	@Id
	@Column(name = "messageId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId ;
	
	private Integer messageOrder;
	
	private String name ;
	
	private String words ;
	
	private Timestamp time ;
	
	@ManyToOne( fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "interview_cvNo", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Interview interview ;
	
	public InterviewMessage() {}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer id) {
		this.messageId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public Integer getMessageOrder() {
		return messageOrder;
	}

	public void setMessageOrder(Integer messageOrder) {
		this.messageOrder = messageOrder;
	}
	
}
