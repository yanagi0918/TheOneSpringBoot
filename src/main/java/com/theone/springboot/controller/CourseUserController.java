package com.theone.springboot.controller;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.entity.Member;
import com.theone.springboot.service.CourseService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class CourseUserController {

    private final CourseService courseService;
    private final MemberService memberService;

    @Autowired
    public CourseUserController(CourseService courseService, MemberService memberService) {
        this.courseService = courseService;
        this.memberService = memberService;
    }

    @GetMapping("/courses")
    public String findAllCourse(@RequestParam(required = false) String courseCategory,
                                @RequestParam(required = false) Integer courseNo,
                                Model model) {
        List<CourseBean> courseListRecent = courseService.findTop5ByStatusOrderByDateDesc("已審核");
        if (courseNo != null) {
            Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
            List<CourseBean> courseListCate = courseService.findTop5ByCourseCategoryAndStatus(findCourse.orElseThrow().getCourseCategory(), "已審核");
            model.addAttribute("CourseBean", findCourse.orElseThrow());
            model.addAttribute("courseListCate", courseListCate);
            model.addAttribute("courseListRecent", courseListRecent);

            return "course/customerDetail";
        } else if (courseCategory != null) {
            List<CourseBean> courseList = courseService.findByCourseCategoryAndStatus(courseCategory, "已審核");
            model.addAttribute("courseCategory", courseCategory);
            model.addAttribute("courseListRecent", courseListRecent);
            model.addAttribute("courseList", courseList);
            return "course/allCustomerListByCategory";
        } else {
            List<CourseBean> courseList = courseService.findAllCoursesByStatus("已審核");
            model.addAttribute("courseList", courseList);
            model.addAttribute("courseListRecent", courseListRecent);
            return "course/allCustomerList";
        }

    }

    @GetMapping("/conditionsSearch")
    public String findByCondition(@RequestParam String search, Model model) {
        List<CourseBean> courseList = courseService.findByCourseNameContainingOrCourseCategoryContainingOrLecturerContaining(search, search, search);
        model.addAttribute("courseList", courseList);
        return "course/allCustomerList";
    }



    @GetMapping("/user/courses/lecturers")
    public String toCoursesBylecturer(Model model, HttpSession session) throws UnsupportedEncodingException {
        Member loginUser = (Member) session.getAttribute("loginMember");
        List<CourseBean> courseList = courseService.findByMember(loginUser);
        model.addAttribute("courseList", courseList);
        return "course/lecturerCourseList";
    }


    //ajax (jquery)檢查課程名稱是否重複，並回傳JSON物件給前端，顯示課程編號幾號與之重複
    @PostMapping(path = "/user/courses/checkName", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<CourseBean> findByCourseName(@RequestBody CourseBean courseBean) {
        CourseBean bean = courseService.findByCourseName(courseBean.getCourseName());
        if (bean != null) {
            return ResponseEntity.status(HttpStatus.OK).body(bean);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/user/courses/detail/{courseNo}")
    public String showDetail(@PathVariable Integer courseNo, Model model) {
        Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
        model.addAttribute("CourseBean", findCourse.orElseThrow());
        return "course/lecturerCourseDetail";

    }

    @GetMapping("/checkFavorite")
    @ResponseBody
    public boolean isFavorite(@RequestParam Integer courseNo, HttpSession session) {
        boolean isFavorite = false;
        Member member = (Member) session.getAttribute("loginMember");
        if (member != null) {
            Set<CourseBean> courses = member.getCollectionCourses();
            for (CourseBean course : courses) {
                if (course.getCourseNo().equals(courseNo)) {
                    isFavorite = true;
                    break;
                }
            }
        }
        return isFavorite;
    }


    @GetMapping("/user/collections")
    public String findAllCollections(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("loginMember");
        Set<CourseBean> collectionCourses = member.getCollectionCourses();

        model.addAttribute("username", member.getUsername());
        model.addAttribute("collectionCourses", collectionCourses);
        return "course/collections";
    }

    @GetMapping("/collectionInsert/{courseNo}")
    @ResponseBody
    public ResponseEntity<Boolean> collectionInsertCourse(@PathVariable Integer courseNo,HttpSession session)   {
        boolean check = true;
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            check =false;
        }else {
            Set<Member> members = new HashSet<>();
            Set<CourseBean> collectionCourses = member.getCollectionCourses();

            CourseBean courseBean = courseService.findCourse(courseNo).orElseThrow();
            collectionCourses.add(courseBean);

            Set<CourseBean> empty = new HashSet<>();
            member.setCollectionCourses(empty);
            members.add(member);
            memberService.saveAllAndFlush(members);

            member.setCollectionCourses(collectionCourses);
            courseBean.setMembers(members);
            members.add(member);
            memberService.saveAllAndFlush(members);
        }
        return ResponseEntity.status(HttpStatus.OK).body(check);
    }

    //backup
//    @GetMapping("/collectionInsert/{courseNo}")
//    @ResponseBody
//    public ResponseEntity<CourseBean> collectionInsertCourse(@PathVariable Integer courseNo, HttpSession session) {
//        Member member = (Member) session.getAttribute("loginMember");
//        Set<CourseBean> collectionCourses = member.getCollectionCourses();
//        CourseBean courseBean = courseService.findCourse(courseNo).orElseThrow();
//        collectionCourses.add(courseBean);
//        Set<CourseBean> empty = new HashSet<>();
//        member.setCollectionCourses(empty);
//        memberService.saveOrUpdate(member);
//        member.setCollectionCourses(collectionCourses);
//        memberService.saveOrUpdate(member);
//        return ResponseEntity.status(HttpStatus.OK).body(courseBean);
//    }

    @GetMapping("/collectionDelete/{courseNo}")
    @ResponseBody
    public ResponseEntity<Boolean> collectionDeleteCourse(@PathVariable Integer courseNo,HttpSession session)  {
        boolean check = true;
        Member member = (Member) session.getAttribute("loginMember");
        if (member == null) {
            check=false;
        }else {
            Set<CourseBean> collectionCourses = member.getCollectionCourses();

            collectionCourses.removeIf(courseBean1 -> courseBean1.getCourseNo().equals(courseNo));

            Set<CourseBean> empty = new HashSet<>();
            member.setCollectionCourses(empty);
            memberService.saveOrUpdate(member);

            member.setCollectionCourses(collectionCourses);
            memberService.saveOrUpdate(member);
        }
        return ResponseEntity.status(HttpStatus.OK).body(check);
    }

    @GetMapping("/user/toCreatePage")
    public String toCreate() {
        return "course/lecturerCourseInset";
    }

    @GetMapping("/user/toUpdatePage/{courseNo}")
    public String toUpdate(@PathVariable Integer courseNo, Model model) {
        Optional<CourseBean> findCourse = courseService.findCourse(courseNo);
        model.addAttribute("CourseBean", findCourse.orElseThrow());
        return "course/lecturerCourseUpdate";
    }

    @PostMapping("/user/courses")
    public String saveOrUpdate(CourseBean CourseBean, @RequestParam("imgURL") MultipartFile mf, HttpSession session) throws IOException {
        Member loginUser = (Member) session.getAttribute("loginMember");

        if (CourseBean.getLecturer() == null) {
            CourseBean.setLecturer(loginUser.getUsername());
            CourseBean.setScore(5.0);
        }
        File imageFile = new File(System.currentTimeMillis() + "_" + mf.getOriginalFilename());
        File savedFile = new File(uploadDirInit().getAbsolutePath(), imageFile.getName());
        if (mf.getOriginalFilename().length() != 0) {
            mf.transferTo(savedFile);
            CourseBean.setCoursePicUrl("/courseImg" + File.separator + imageFile.getName());
        }
        CourseBean.setUserid(loginUser.getUserid());
        CourseBean.setMember(loginUser);
        CourseBean.setStatus("待審核");
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

    @DeleteMapping("/user/courses/{courseNo}")
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