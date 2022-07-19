package com.theone.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.theone.springboot.entity.Event;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    private final MemberService memberService;

    @Autowired
    public CourseDashBoardController(CourseService courseService, MemberService memberService) {
        this.courseService = courseService;
        this.memberService = memberService;
    }

    @GetMapping("/courses")
    public String findAllCourse(Model model) {
        List<CourseBean> courseList = courseService.findAllCourses();
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseCounts", courseList.size());
        return "course_dashboard/courseList";
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
        System.out.println(CourseBean);
        CourseBean.setMember(memberService.getByUserid(CourseBean.getUserid()));
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

    @GetMapping("/courses/chartdata")
    @ResponseBody
    public Map<String, Object> getChartData() {
        Map<String, Object> dataMap =new HashMap();
        int[] dataCategory = {0, 0, 0, 0, 0, 0, 0};
        int[] dataStatus = {0, 0, 0};
        List<CourseBean> courses = courseService.findAllCourses();
        for (CourseBean course : courses) {
            switch (course.getCourseCategory()) {
                case "英文證照" : dataCategory[0]++; break;
                case "日語證照": dataCategory[1]++; break;
                case "韓語證照": dataCategory[2]++; break;
                case "求職技巧": dataCategory[3]++; break;
                case "自我認知": dataCategory[4]++; break;
                case "生涯轉換與轉業": dataCategory[5]++; break;
                case "就業市場現況與趨勢": dataCategory[6]++; break;
                default: break;
            }
            switch (course.getStatus()) {
                case "待審核" : dataStatus[0]++; break;
                case "已審核": dataStatus[1]++; break;
                case "駁回": dataStatus[2]++; break;
                default: break;
            }
        }
        dataMap.put("dataCategory",dataCategory);
        dataMap.put("dataStatus",dataStatus);

        return dataMap;
    }

    @PutMapping("/courses")
    @ResponseBody
    public ResponseEntity<CourseBean> saveOrUpdate(@RequestBody CourseBean CourseBean) {
        CourseBean courseBean = courseService.findCourse(CourseBean.getCourseNo()).orElseThrow();
        courseBean.setStatus(CourseBean.getStatus());
        courseService.saveOrUpdate(courseBean);
        return ResponseEntity.status(HttpStatus.OK).body(courseBean);
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

    @GetMapping("/courses/csvExport")
    public void csvExport(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment; filename=courses.csv");
        courseService.csvExport(response.getWriter());
    }

    @GetMapping("/course/sendemail")
    @ResponseBody
    public boolean sendNotifyEmail(@RequestParam Integer courseNo, @RequestParam String result) {
        CourseBean course = courseService.findCourse(courseNo).orElseThrow();
        if ("已審核".equals(result) || "駁回".equals(result)) {
            result = ("已審核".equals(result))?"\"blue\"><b>通過 !":"\"red\"><b>駁回，請修正課程資訊後重新送審，謝謝 !";
            String msg = "<p style=\"font-size: large;\">" +
                    "親愛的 "+course.getLecturer()+" 講師您好: <br>"+
                    "<br>"+
                    "課程編號: " + courseNo + "<br>" +
                    "課程名稱: " + course.getCourseName() + "<br>" +
                    "審核結果: <font color=" + result + "</b></font><br>" +
                    "講師專區連結: http://localhost:8080/user/courses/lecturers" +
                    "</p>";
            courseService.sendNotifyEmail("eeit45@gmail.com", "TheOne 講師課程審核通知", msg);
            return true;
        }
        return false;
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