package com.theone.springboot.repository;

import com.theone.springboot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {


    CourseBean findByCourseName(String courseName);

    List<CourseBean> findByCourseCategory(String courseCategory);

    List<CourseBean> findByCourseCategoryAndStatus(String courseCategory,String status);

    List<CourseBean> findByStatus(String status);

    List<CourseBean> findByLecturer(String lecturer);

    List<CourseBean> findByMember(Member Member);


}