package com.theone.springboot.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "Course_Table")
@Component
public class CourseBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseNo;
	private String courseCategory;
	private String courseName;
	private String courseIntroduction;
	private String lecturer;
	@Column(columnDefinition = "Date", name = "launch_date")
	private Date date;
	private String coursePicUrl;
	private String courseVedioUrl;
	private Double score;
	private Integer price;
	
	public CourseBean() {
	}
	
	public CourseBean(Integer courseNo, String courseCategory, String courseName, String courseIntroduction,
			String lecturer, Date date, String coursePicUrl, String courseVedioUrl, Double score, Integer price) {
		
		this.courseNo = courseNo;
		this.courseCategory = courseCategory;
		this.courseName = courseName;
		this.courseIntroduction = courseIntroduction;
		this.lecturer = lecturer;
		this.date = date;
		this.coursePicUrl = coursePicUrl;
		this.courseVedioUrl = courseVedioUrl;
		this.score = score;
		this.price = price;
	}

	public Integer getCourseNo() {
		return courseNo;
	}

	public void setCourseNo(Integer courseNo) {
		this.courseNo = courseNo;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseIntroduction() {
		return courseIntroduction;
	}

	public void setCourseIntroduction(String courseIntroduction) {
		this.courseIntroduction = courseIntroduction;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCoursePicUrl() {
		return coursePicUrl;
	}

	public void setCoursePicUrl(String coursePicUrl) {
		this.coursePicUrl = coursePicUrl;
	}

	public String getCourseVedioUrl() {
		return courseVedioUrl;
	}

	public void setCourseVedioUrl(String courseVedioUrl) {
		this.courseVedioUrl = courseVedioUrl;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CourseBean [courseNo=" + courseNo + ", courseCategory=" + courseCategory + ", courseName=" + courseName
				+ ", courseIntroduction=" + courseIntroduction + ", lecturer=" + lecturer + ", date=" + date
				+ ", coursePicUrl=" + coursePicUrl + ", courseVedioUrl=" + courseVedioUrl + ", score=" + score
				+ ", price=" + price + "]";
	}
	
}
