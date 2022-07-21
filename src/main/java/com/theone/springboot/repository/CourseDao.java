package com.theone.springboot.repository;

import com.theone.springboot.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.theone.springboot.entity.CourseBean;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseDao extends JpaRepository<CourseBean, Integer> {


    CourseBean findByCourseName(String courseName);

    List<CourseBean> findByCourseCategoryAndStatus(String courseCategory, String status);

    List<CourseBean> findTop5ByCourseCategoryAndStatus(String courseCategory, String status);

    List<CourseBean> findByStatus(String status);

    Page<CourseBean> findAllByStatus(Pageable pageable, String status);

    List<CourseBean> findByMember(Member Member);

    List<CourseBean> findByCourseNameContainingOrCourseCategoryContainingOrLecturerContaining(String courseName, String courseCategory, String lecturer);

    List<CourseBean> findTop5ByStatusOrderByDateDesc(String status);
}