package com.theone.springboot.service;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;

public interface CourseService {

	boolean isDup(Integer pk);
	
	CourseBean saveOrUpdate(CourseBean courseBean);

	CourseBean findByCourseName(String courseName);

	List<CourseBean> findByCourseCategory(String courseCategory);

	List<CourseBean> findByLecturer(String lecturer);

	List<CourseBean> findAllCourses();

	List<CourseBean> findAllCoursesByStatus(String status);

	List<CourseBean> findByCourseCategoryAndStatus(String courseCategory,String status);
	
	Optional<CourseBean> findCourse(Integer pk);

	List<CourseBean> findByMember(Member member);

	void deleteCourse(Integer pk);

	void csvExport(Writer writer);

	void sendNotifyEmail(String recipient, String subject, String message);


}