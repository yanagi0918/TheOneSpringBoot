package com.theone.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import com.theone.springboot.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theone.springboot.entity.CourseBean;
import com.theone.springboot.repository.CourseDao;
import com.theone.springboot.service.CourseService;


@Service
@Transactional
public class CourseServicelmpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

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
        return courseDao.getByCourseName(courseName);
    }

    @Override
    public  List<CourseBean> findByCourseCategory(String courseCategory) {
        return courseDao.getByCourseCategory(courseCategory);
    }

    @Override
    public List<CourseBean> findByLecturer(String lecturer) {
        return courseDao.getByLecturer(lecturer);
    }

    @Override
    public List<CourseBean> findAllCourses() {
        return courseDao.findAll();
    }

    @Override
    public Optional<CourseBean> findCourse(Integer pk) {
        return courseDao.findById(pk);
    }

    @Override
    public List<CourseBean> findByMember(Member member) {
        return courseDao.getByMember(member);
    }


    @Override
    public void deleteCourse(Integer pk) {
        courseDao.deleteById(pk);
    }
}
