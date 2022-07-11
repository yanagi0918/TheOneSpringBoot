package com.theone.springboot.controller;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CourseService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class CourseUserController {

    private final CourseService courseService;

    //    @Autowired
    public CourseUserController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public String findAllCourse(@RequestParam(required = false) String courseCategory,
                                @RequestParam(required = false) Integer courseNo,
                                Model model) {
        if (courseNo != null) {
            Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
            model.addAttribute("CourseBean", findCourse.orElseThrow());
            return "course/customerDetail";
        } else if (courseCategory != null) {
            List<CourseBean> courseList = courseService.findByCourseCategory(courseCategory);
            model.addAttribute("courseCategory", courseCategory);
            model.addAttribute("courseList", courseList);
            return "course/allCustomerListByCategory";
        } else {
            List<CourseBean> courseList = courseService.findAllCourses();
            model.addAttribute("courseList", courseList);
            return "course/allCustomerList";
        }

    }

    @GetMapping("/courses/lecturers")
    public String toCoursesBylecturer(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
//  Member lecturerMember = (Member)request.getSession().getAttribute("");
//  lecturerMember.getUserid();
        String lecturer = "王大陸"; //寫死
        String url = "/user/courses/"+URLEncoder.encode(lecturer,"utf-8");
        return "redirect:"+url;
    }

    //講師開課(之後要修改成session)
    @GetMapping("/courses/{lecturer}")
    public String findAllCourseByLecturer(@PathVariable String lecturer, Model model) {
//    public String findAllCourseByLecturer(@PathVariable String lecturer, Model model) {
        System.out.println(lecturer);
        List<CourseBean> courseList = courseService.findByLecturer(lecturer);
        model.addAttribute("courseList", courseList);
        return "course/lecturerCourseList";
    }

    //ajax (jquery)檢查課程名稱是否重複，並回傳JSON物件給前端，顯示課程編號幾號與之重複
    @PostMapping(path = "/courses/checkName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CourseBean> findByCourseName(@RequestBody CourseBean courseBean) {
        CourseBean bean = courseService.findByCourseName(courseBean.getCourseName());
        if (bean != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/courses/detail/{courseNo}")
    public String showDetail(@PathVariable Integer courseNo, Model model) {
        Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
        model.addAttribute("CourseBean", findCourse.orElseThrow());
        return "course/lecturerCourseDetail";

    }

    @GetMapping("/toCreatePage")
    public String toCreate() {
        return "course/lecturerCourseInset";
    }

    @GetMapping("/toUpdatePage/{courseNo}")
    public String toUpdate(@PathVariable Integer courseNo, Model model) {
        Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
        model.addAttribute("CourseBean", findCourse.orElseThrow());
        return "course/lecturerCourseUpdate";
    }

    @PostMapping("/courses")
    public String saveOrUpdate(CourseBean CourseBean, @RequestParam("imgURL") MultipartFile mf) throws IOException {
        System.out.println(CourseBean);
        File imageFile = new File(System.currentTimeMillis() + "_" + mf.getOriginalFilename());
        //String savedFilePath = new File("target\\classes\\static\\courseImg\\", imageFile.getName()).getAbsolutePath();
        File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFile.getName());
        if (mf.getOriginalFilename().length() != 0) {
            mf.transferTo(savedFile);
            CourseBean.setCoursePicUrl("/courseImg" + File.separator + imageFile.getName());
        }
        courseService.saveOrUpdate(CourseBean);
        return "redirect:/user/courses/lecturers";
    }

    public File uploadDirInit() {
        String savedFilePath = new File("target\\classes\\static\\courseImg\\").getAbsolutePath();
        File uploadDir = new File(savedFilePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        return uploadDir;
    }

    @DeleteMapping("/courses/{courseNo}")
    @ResponseBody
    public ResponseEntity<CourseBean> deleteCourseByNo(@PathVariable Integer courseNo) {
        courseService.deleteCourse(courseNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/courses")
    @ResponseBody
    public ResponseEntity<CourseBean> saveOrUpdate(@RequestBody CourseBean CourseBean) {
        CourseBean courseBean = courseService.findCourse(CourseBean.getCourseNo()).orElseThrow();
        courseBean.setStatus(CourseBean.getStatus());
        courseService.saveOrUpdate(courseBean);
        return ResponseEntity.status(HttpStatus.OK).body(courseBean);
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