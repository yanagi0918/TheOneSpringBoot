package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.service.CourseService;


@Controller
@RequestMapping("/dashboard")
public class CourseDashBoardController {

    private final CourseService courseService;

//    @Autowired
    public CourseDashBoardController(CourseService courseService) {
        this.courseService = courseService;
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
        //String savedFilePath = new File("target\\classes\\static\\courseImg\\", imageFile.getName()).getAbsolutePath();
        File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFile.getName());
        if (mf.getOriginalFilename().length() != 0) {
            mf.transferTo(savedFile);
            CourseBean.setCoursePicUrl("/courseImg" + File.separator + imageFile.getName());
        }
        courseService.saveOrUpdate(CourseBean);
        return "redirect:/dashboard/courses";
    }

    public File uploadDirInit() {
        String savedFilePath = new File("target\\classes\\static\\courseImg\\").getAbsolutePath();
        File uploadDir = new File(savedFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir;
    }
//1234
    @DeleteMapping("/courses/{courseNo}")
    @ResponseBody
    public ResponseEntity<CourseBean> deleteCourseByNo(@PathVariable Integer courseNo) {
        courseService.deleteCourse(courseNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        // java.sql.Date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        CustomDateEditor ce = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(java.sql.Date.class, ce);
    }

}