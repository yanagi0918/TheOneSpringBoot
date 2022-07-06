package com.theone.springboot.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Member_Table")
@Component
public class Member {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer idNumber;
	@Column(unique = true)
	 private String userid;
	 private String pwd;
	 private String username;
	 private String gender;
	 @Column(columnDefinition = "Date")
	 private Date birth;  
	 private String tele;
	 private String phone;
	 private String address;
	 private String email;
//	 private Integer point;
	 private String image;


	 //Constructor
	 public Member() {}
	
	//建立一個帳號密碼驗證的建構子
	 public Member(String userid, String pwd) {
			super();
			this.userid = userid;
			this.pwd = pwd;
		}

	public Integer getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(Integer idNumber) {
		this.idNumber = idNumber;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Integer getPoint() {
//		return point;
//	}
//
//	public void setPoint(Integer point) {
//		this.point = point;
//	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Member [idNumber=" + idNumber + ", userid=" + userid + ", pwd=" + pwd + ", username=" + username
				+ ", gender=" + gender + ", birth=" + birth + ", tele=" + tele + ", phone=" + phone + ", address="
				+ address + ", email=" + email + ", image=" + image + "]";
	}
	
	
}
