package com.theone.springboot.service;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import org.springframework.data.domain.Page;

public interface CourseService {

	boolean isDup(Integer pk);
	
	CourseBean saveOrUpdate(CourseBean courseBean);

	CourseBean findByCourseName(String courseName);


	List<CourseBean> findAllCourses();

	Page<CourseBean> findAllCoursesByStatus(int page, int size, String status);

	List<CourseBean> findByCourseCategoryAndStatus(String courseCategory,String status);
	
	Optional<CourseBean> findCourse(Integer pk);

	List<CourseBean> findByMember(Member member);

	void deleteCourse(Integer pk);

	void csvExport(Writer writer);

	void sendNotifyEmail(String recipient, String subject, String message);

	Page<CourseBean> findByCourseNameContainingOrCourseCategoryContainingOrLecturerContaining(String search);

	List<CourseBean> findTop5ByCourseCategoryAndStatus(String courseCategory,String status);

	List<CourseBean> findTop5ByStatusOrderByCourseNoDesc(String status);
}