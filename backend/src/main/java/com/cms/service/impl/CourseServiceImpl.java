package com.cms.service.impl;

import com.cms.entity.Course;
import com.cms.dao.CourseDao;
import com.cms.dto.CourseDTO;
import com.cms.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程Service实现类
 * 实现课程管理的业务逻辑
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Service
public class CourseServiceImpl implements CourseService {
    
    @Autowired
    private CourseDao courseDao;
    
    @Override
    @Transactional
    public Course createCourse(CourseDTO courseDTO) {
        // 检查课程是否已存在
        Course existingCourse = courseDao.selectCourseById(courseDTO.getCourseId());
        if (existingCourse != null) {
            throw new RuntimeException("课程已存在");
        }
        
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setSelectedNum(0);
        course.setCreateTime(LocalDateTime.now());
        course.setUpdateTime(LocalDateTime.now());
        
        courseDao.insertCourse(course);
        return course;
    }
    
    @Override
    @Transactional
    public Course updateCourse(CourseDTO courseDTO) {
        // 检查课程是否存在
        Course existingCourse = courseDao.selectCourseById(courseDTO.getCourseId());
        if (existingCourse == null) {
            throw new RuntimeException("课程不存在");
        }
        
        Course course = new Course();
        BeanUtils.copyProperties(courseDTO, course);
        course.setSelectedNum(existingCourse.getSelectedNum());
        course.setCreateTime(existingCourse.getCreateTime());
        course.setUpdateTime(LocalDateTime.now());
        
        courseDao.updateCourse(course);
        return course;
    }
    
    @Override
    @Transactional
    public boolean deleteCourse(String courseId) {
        // 检查课程是否存在
        Course existingCourse = courseDao.selectCourseById(courseId);
        if (existingCourse == null) {
            throw new RuntimeException("课程不存在");
        }
        
        // 检查课程是否已有学生选课
        if (existingCourse.getSelectedNum() > 0) {
            throw new RuntimeException("课程已有学生选课，无法删除");
        }
        
        int result = courseDao.deleteCourseById(courseId);
        return result > 0;
    }
    
    @Override
    public Course getCourseById(String courseId) {
        return courseDao.selectCourseById(courseId);
    }
    
    @Override
    public List<Course> getAllCourses() {
        return courseDao.selectAllCourses();
    }
    
    @Override
    public List<Course> getCoursesByTeacherId(String teacherId) {
        return courseDao.selectCoursesByTeacherId(teacherId);
    }
    
    @Override
    public List<Course> getCoursesByType(String courseType) {
        return courseDao.selectCoursesByType(courseType);
    }
    
    @Override
    public List<Course> getCoursesByName(String courseName) {
        return courseDao.selectCoursesByName(courseName);
    }
    
    @Override
    public boolean isCourseFull(String courseId) {
        Course course = courseDao.selectCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        return course.getSelectedNum() >= course.getCapacity();
    }
    
    @Override
    @Transactional
    public boolean updateSelectedNum(String courseId, Integer changeNum) {
        int result = courseDao.updateCourseSelectedNum(courseId, changeNum);
        return result > 0;
    }
}
