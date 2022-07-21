package com.theone.springboot.entity;


import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    //	 VINCENT ONE(member講師) TO MANY(開設多個課程)
    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    @JsonManagedReference
    private List<CourseBean> lecturerCourses;


    //	 VINCENT COLLECTION MANY(member講師) TO MANY(收藏多個課程)
//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonManagedReference
    @JoinTable(name = "member_course",
            joinColumns = {@JoinColumn(name = "memberPk", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "courseNo", nullable = false)})
    private Set<CourseBean> collectionCourses = new HashSet<>();


    public Set<CourseBean> getCollectionCourses() {
        return collectionCourses;
    }

    public void setCollectionCourses(Set<CourseBean> collectionCourses) {
        this.collectionCourses = collectionCourses;
    }

    public Set<Job> getCollectionJobs() {
        return collectionJobs;
    }

    public void setCollectionJobs(Set<Job> collectionJobs) {
        this.collectionJobs = collectionJobs;
    }

    @Fetch(value = FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "T_JOB_MEMBER",
            joinColumns = {@JoinColumn(name = "MEMBER_FK")},
            inverseJoinColumns = {@JoinColumn(name = "JOB_FK")}
    )
    private Set<Job> collectionJobs = new HashSet<Job>();


    //Constructor
    public Member() {
    }

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

    //	VINCENT ONE(member講師) TO MANY(開設多個課程) start
    public List<CourseBean> getLecturerCourses() {
        return lecturerCourses;
    }

    public void setLecturerCourses(List<CourseBean> lecturerCourses) {
        this.lecturerCourses = lecturerCourses;
    }
    //	 VINCENT ONE(member講師) TO MANY(開設多個課程) end


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
