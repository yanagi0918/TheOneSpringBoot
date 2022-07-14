package com.theone.springboot.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;
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
    @NotBlank
    private String status;
    @Column(columnDefinition = "Date", name = "launch_date")
    private Date date;
    private String coursePicUrl;
    private String courseVedioUrl;
    private Double score;
    private Integer price;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    private String userid;
    //    VINCENT ONE(member講師) TO MANY(開設多個課程)
    @ManyToOne
    @JoinColumn(name = "FK_member_userId")
    @JsonBackReference
    private Member member;


    //	 VINCENT COLLECTION MANY(member講師) TO MANY(收藏多個課程)
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "member_course",
//                joinColumns = {@JoinColumn(name = "courseNo", nullable = false)},
//                inverseJoinColumns = {@JoinColumn(name = "memberPk", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "collectionCourses")
    private Set<Member> members = new HashSet<>();

    public Set<Member> getMembers() {
        return members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public CourseBean() {
    }

    public CourseBean(Integer courseNo, String courseCategory, String courseName, String courseIntroduction, String lecturer, String status, Date date, String coursePicUrl, String courseVedioUrl, Double score, Integer price) {
        this.courseNo = courseNo;
        this.courseCategory = courseCategory;
        this.courseName = courseName;
        this.courseIntroduction = courseIntroduction;
        this.lecturer = lecturer;
        this.status = status;
        this.date = date;
        this.coursePicUrl = coursePicUrl;
        this.courseVedioUrl = courseVedioUrl;
        this.score = score;
        this.price = price;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "CourseBean{" +
                "courseNo=" + courseNo +
                ", courseCategory='" + courseCategory + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseIntroduction='" + courseIntroduction + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", coursePicUrl='" + coursePicUrl + '\'' +
                ", courseVedioUrl='" + courseVedioUrl + '\'' +
                ", score=" + score +
                ", price=" + price +
                ", userid='" + userid + '\'' +
                '}';
    }
}
