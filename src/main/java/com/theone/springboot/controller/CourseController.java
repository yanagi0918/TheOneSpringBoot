package com.theone.springboot.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.service.CourseService;


@Controller
public class CourseController  {
	
	private final CourseService courseService;
	
	private final ServletContext context;

	@Autowired
	public CourseController(CourseService courseService, ServletContext context) {
		this.courseService = courseService;
		this.context = context;
	}

	@GetMapping("/courses")
	public String findAllCourse(Model model) {
		List<CourseBean> courseList = courseService.findAllCourses();
		model.addAttribute("courseList", courseList);
		return "course_dashboard/courseList";
	}
	
	@GetMapping("/courses/{courseNo}")
	public String findCourseByNo(@PathVariable Integer courseNo, Model model) {
		List<CourseBean> courseList = new ArrayList<CourseBean>();
		Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
		courseList.add(findCourse.orElseThrow());
		model.addAttribute("courseList", courseList);
		return "course_dashboard/courseList";
	}
	
	@GetMapping("/courses/detail/{courseNo}")
	public String showDetail(@PathVariable Integer courseNo, Model model) {
		Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
		model.addAttribute("CourseBean", findCourse.orElseThrow());
		model.addAttribute("contextPath", context.getContextPath());
		return "course_dashboard/courseDetail";

	}
	
	@GetMapping("/toCreatePage")
	public String toCreate() {
		return "course_dashboard/courseCreate";
	}
	
	@GetMapping("/toUpdatePage/{courseNo}")
	public String toUpdate(@PathVariable Integer courseNo, Model model) {
		Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
		model.addAttribute("CourseBean", findCourse.orElseThrow());
		return "course_dashboard/courseUpdate";
	}

	@PostMapping("/courses")
	public String saveOrUpdate(CourseBean CourseBean, @RequestParam("imgURL") MultipartFile mf) throws IOException {
		File imageFile = new File(System.currentTimeMillis() + "_" + mf.getOriginalFilename());
		File savedFile = new File("target\\classes\\static\\courseImg\\", imageFile.getName());
		if (mf.getOriginalFilename().length() != 0) {
			mf.transferTo(savedFile);
			CourseBean.setCoursePicUrl("courseImg" + File.separator + imageFile.getName());
		}
		courseService.saveOrUpdate(CourseBean);
		return "redirect:/courses";
	}

	@DeleteMapping("/courses/{courseNo}")
	@ResponseBody
	public ResponseEntity<CourseBean> deleteCourseByNo(@PathVariable Integer courseNo) {
		courseService.deleteCourse(courseNo);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}