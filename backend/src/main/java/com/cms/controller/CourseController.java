package com.cms.controller;

import com.cms.entity.Course;
import com.cms.dto.CourseDTO;
import com.cms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程Controller
 * 处理课程管理的HTTP请求
 * @author 张嘉欢
 * @date 2025-11-30
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    /**
     * 创建课程
     * @param courseDTO 课程DTO
     * @return 课程实体
     */
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDTO courseDTO) {
        try {
            Course course = courseService.createCourse(courseDTO);
            return new ResponseEntity<>(course, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 修改课程
     * @param courseId 课程号
     * @param courseDTO 课程DTO
     * @return 课程实体
     */
    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable String courseId, @RequestBody CourseDTO courseDTO) {
        try {
            courseDTO.setCourseId(courseId);
            Course course = courseService.updateCourse(courseDTO);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 删除课程
     * @param courseId 课程号
     * @return 是否成功
     */
    @DeleteMapping("/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable String courseId) {
        try {
            boolean success = courseService.deleteCourse(courseId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据课程号查询课程
     * @param courseId 课程号
     * @return 课程实体
     */
    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseId) {
        try {
            Course course = courseService.getCourseById(courseId);
            if (course != null) {
                return new ResponseEntity<>(course, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 查询所有课程
     * @return 课程列表
     */
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据教师ID查询课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacherId(@PathVariable String teacherId) {
        try {
            List<Course> courses = courseService.getCoursesByTeacherId(teacherId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据课程类型查询课程
     * @param courseType 课程类型
     * @return 课程列表
     */
    @GetMapping("/type/{courseType}")
    public ResponseEntity<List<Course>> getCoursesByType(@PathVariable String courseType) {
        try {
            List<Course> courses = courseService.getCoursesByType(courseType);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据课程名称模糊查询课程
     * @param courseName 课程名称
     * @return 课程列表
     */
    @GetMapping("/name/{courseName}")
    public ResponseEntity<List<Course>> getCoursesByName(@PathVariable String courseName) {
        try {
            List<Course> courses = courseService.getCoursesByName(courseName);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
