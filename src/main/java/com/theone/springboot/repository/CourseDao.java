package com.theone.springboot.repository;

import com.theone.springboot.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;

import java.util.List;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {


    CourseBean findByCourseName(String courseName);

    List<CourseBean> findByCourseCategoryAndStatus(String courseCategory, String status);

    List<CourseBean> findTop5ByCourseCategoryAndStatus(String courseCategory, String status);

    Page<CourseBean> findAllByStatus(Pageable pageable, String status);

    List<CourseBean> findByMember(Member Member);

    Page<CourseBean> findByCourseNameContainingOrCourseCategoryContainingOrLecturerContaining(Pageable pageable,String courseName, String courseCategory, String lecturer);

    List<CourseBean> findTop5ByStatusOrderByCourseNoDesc(String status);
}