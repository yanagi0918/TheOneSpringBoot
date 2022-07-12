package com.theone.springboot.repository;

import com.theone.springboot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;

import java.util.List;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {


    CourseBean getByCourseName(String courseName);

    List<CourseBean> getByCourseCategory(String courseCategory);

    List<CourseBean> getByCourseCategoryAndStatus(String courseCategory,String status);

    List<CourseBean> getByStatus(String status);

    List<CourseBean> getByLecturer(String lecturer);

    List<CourseBean> getByMember(Member Member);

}