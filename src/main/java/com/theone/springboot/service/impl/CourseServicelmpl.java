package com.theone.springboot.service.impl;

import java.io.Writer;
import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Member;
import com.theone.springboot.utils.CourseCsvExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.repository.CourseDao;
import com.theone.springboot.service.CourseService;

import javax.mail.internet.MimeMessage;


@Service
@Transactional
public class CourseServicelmpl implements CourseService {

    private final CourseDao courseDao;
    private final CourseCsvExporter courseCsvExporter;
    private final JavaMailSender javaMailSender;

    @Autowired
    public CourseServicelmpl(CourseDao courseDao, CourseCsvExporter courseCsvExporter, JavaMailSender javaMailSender) {
        this.courseDao = courseDao;
        this.courseCsvExporter = courseCsvExporter;
        this.javaMailSender = javaMailSender;
    }


    @Override
    public boolean isDup(Integer pk) {
        return courseDao.existsById(pk);
    }

    @Override
    public CourseBean saveOrUpdate(CourseBean courseBean) {

        return courseDao.save(courseBean);
    }

    @Override
    public CourseBean findByCourseName(String courseName) {
        return courseDao.findByCourseName(courseName);
    }


    @Override
    public List<CourseBean> findByCourseCategory(String courseCategory) {
        return courseDao.findByCourseCategory(courseCategory);
    }

    @Override
    public List<CourseBean> findByLecturer(String lecturer) {
        return courseDao.findByLecturer(lecturer);
    }

    @Override
    public List<CourseBean> findAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public List<CourseBean> findAllCoursesByStatus(String status) {
        return courseDao.findByStatus(status);
    }

    @Override
    public List<CourseBean> findByCourseCategoryAndStatus(String courseCategory, String status) {
        return courseDao.findByCourseCategoryAndStatus(courseCategory, status);
    }

    @Override
    public Optional<CourseBean> findCourse(Integer pk) {
        return courseDao.findById(pk);
    }

    @Override
    public List<CourseBean> findByMember(Member member) {
        return courseDao.findByMember(member);
    }


    @Override
    public void deleteCourse(Integer pk) {
        courseDao.deleteById(pk);
    }

    @Override
    public void csvExport(Writer writer) {
        List<CourseBean> courseBeans = courseDao.findAll();
        courseCsvExporter.csvExport(writer, courseBeans);
    }

    @Override
    public void sendNotifyEmail(String recipient, String subject, String message) {
        MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("eeit45theone@gmail.com");
                messageHelper.setTo(recipient);
                messageHelper.setSubject(subject);
                messageHelper.setText(message, true);
            }
        };
//        MimeMessagePreparator messagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setFrom("eeit45theone@gmail.com");
//            messageHelper.setTo(recipient);
//            messageHelper.setSubject(subject);
//            messageHelper.setText(message, true);
//        };
        try {
            javaMailSender.send(messagePreparator);
            System.out.println("sent success");
        } catch (MailException e) {
            System.out.println("mail error");
            e.printStackTrace();
        }

    }

    @Override
    public List<CourseBean> findByCourseNameOrCourseCategoryOrLecturerContaining(String courseName, String courseCategory, String lecturer) {
        return courseDao.findByCourseNameOrCourseCategoryOrLecturerContaining(courseName,courseCategory,lecturer);
    }
}
