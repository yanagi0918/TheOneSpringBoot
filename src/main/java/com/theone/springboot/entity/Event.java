package com.theone.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Event_Table")
@Component
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	private String compId;
	private String imgUrl;
	private String eventLinkUrl;
	@Column(columnDefinition = "Date")
	private Date postStart;
	@Column(columnDefinition = "Date")
	private Date postEnd;
	private String remark;
	// 0:未審核 1:審核通過 2:已退件 3:已撤銷
	private Integer state;

	public Event() {
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getCompId() {
		return compId;
	}

	public void setCompId(String compId) {
		this.compId = compId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getEventLinkUrl() {
		return eventLinkUrl;
	}

	public void setEventLinkUrl(String eventLinkUrl) {
		this.eventLinkUrl = eventLinkUrl;
	}

	public Date getPostStart() {
		return postStart;
	}

	public void setPostStart(Date postStart) {
		this.postStart = postStart;
	}

	public Date getPostEnd() {
		return postEnd;
	}

	public void setPostEnd(Date postEnd) {
		this.postEnd = postEnd;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", compId=" + compId + ", imgUrl=" + imgUrl + ", eventLinkUrl="
				+ eventLinkUrl + ", postStart=" + postStart + ", postEnd=" + postEnd + ", remark=" + remark + ", state="
				+ state + "]";
	}

}
