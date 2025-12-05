package com.cms.controller;

import com.cms.entity.CourseSelection;
import com.cms.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选课记录Controller
 * 处理选课管理的HTTP请求
 * @author 张嘉欢
 * @date 2025-11-30
 */
@RestController
@RequestMapping("/course-selections")
public class CourseSelectionController {
    
    @Autowired
    private CourseSelectionService courseSelectionService;
    
    /**
     * 学生选课
     * @param studentId 学号
     * @param courseId 课程号
     * @return 选课记录实体
     */
    @PostMapping
    public ResponseEntity<CourseSelection> selectCourse(@RequestParam String studentId, @RequestParam String courseId) {
        try {
            CourseSelection courseSelection = courseSelectionService.selectCourse(studentId, courseId);
            return new ResponseEntity<>(courseSelection, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 学生退课
     * @param studentId 学号
     * @param courseId 课程号
     * @return 是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> dropCourse(@RequestParam String studentId, @RequestParam String courseId) {
        try {
            boolean success = courseSelectionService.dropCourse(studentId, courseId);
            return new ResponseEntity<>(success, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据选课记录ID查询选课记录
     * @param selectionId 选课记录ID
     * @return 选课记录实体
     */
    @GetMapping("/{selectionId}")
    public ResponseEntity<CourseSelection> getCourseSelectionById(@PathVariable Integer selectionId) {
        try {
            CourseSelection courseSelection = courseSelectionService.getCourseSelectionById(selectionId);
            if (courseSelection != null) {
                return new ResponseEntity<>(courseSelection, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据学号查询选课记录
     * @param studentId 学号
     * @return 选课记录列表
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<CourseSelection>> getCourseSelectionsByStudentId(@PathVariable String studentId) {
        try {
            List<CourseSelection> selections = courseSelectionService.getCourseSelectionsByStudentId(studentId);
            return new ResponseEntity<>(selections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据课程号查询选课记录
     * @param courseId 课程号
     * @return 选课记录列表
     */
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<CourseSelection>> getCourseSelectionsByCourseId(@PathVariable String courseId) {
        try {
            List<CourseSelection> selections = courseSelectionService.getCourseSelectionsByCourseId(courseId);
            return new ResponseEntity<>(selections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 根据教师ID查询选课记录
     * @param teacherId 教师ID
     * @return 选课记录列表
     */
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<CourseSelection>> getCourseSelectionsByTeacherId(@PathVariable String teacherId) {
        try {
            List<CourseSelection> selections = courseSelectionService.getCourseSelectionsByTeacherId(teacherId);
            return new ResponseEntity<>(selections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /**
     * 查询所有选课记录
     * @return 选课记录列表
     */
    @GetMapping
    public ResponseEntity<List<CourseSelection>> getAllCourseSelections() {
        try {
            List<CourseSelection> selections = courseSelectionService.getAllCourseSelections();
            return new ResponseEntity<>(selections, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
