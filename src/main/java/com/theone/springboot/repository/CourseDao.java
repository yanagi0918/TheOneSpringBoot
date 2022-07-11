package com.theone.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;

import java.util.List;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {

    CourseBean getByCourseName(String courseName);

    List<CourseBean> getByCourseCategory(String courseCategory);

    List<CourseBean> getByLecturer(String lecturer);

}