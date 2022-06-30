package com.theone.springboot.service;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.CourseBean;

public interface CourseService {

	boolean isDup(Integer pk);
	
	CourseBean saveOrUpdate(CourseBean courseBean);

	List<CourseBean> findAllCourses();
	
	Optional<CourseBean> findCourse(Integer pk);

	void deleteCourse(Integer pk);

	//List<CourseBean> getCourseByMultiQuery(String courseCategory, String courseName, String lecturer);
}