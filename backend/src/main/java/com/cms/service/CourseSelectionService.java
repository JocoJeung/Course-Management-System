package com.cms.service;

import com.cms.entity.CourseSelection;
import com.cms.dto.CourseSelectionDTO;

import java.util.List;

/**
 * 选课记录Service接口
 * 定义选课管理的业务逻辑方法
 * @author 张嘉欢
 * @date 2025-11-30
 */
public interface CourseSelectionService {
    /**
     * 学生选课
     * @param studentId 学号
     * @param courseId 课程号
     * @return 选课记录实体
     */
    CourseSelection selectCourse(String studentId, String courseId);
    
    /**
     * 学生退课
     * @param studentId 学号
     * @param courseId 课程号
     * @return 是否成功
     */
    boolean dropCourse(String studentId, String courseId);
    
    /**
     * 根据选课记录ID查询选课记录
     * @param selectionId 选课记录ID
     * @return 选课记录实体
     */
    CourseSelection getCourseSelectionById(Integer selectionId);
    
    /**
     * 根据学号和课程号查询选课记录
     * @param studentId 学号
     * @param courseId 课程号
     * @return 选课记录实体
     */
    CourseSelection getCourseSelectionByStudentAndCourse(String studentId, String courseId);
    
    /**
     * 根据学号查询选课记录列表
     * @param studentId 学号
     * @return 选课记录列表
     */
    List<CourseSelection> getCourseSelectionsByStudentId(String studentId);
    
    /**
     * 根据课程号查询选课记录列表
     * @param courseId 课程号
     * @return 选课记录列表
     */
    List<CourseSelection> getCourseSelectionsByCourseId(String courseId);
    
    /**
     * 根据教师ID查询选课记录列表
     * @param teacherId 教师ID
     * @return 选课记录列表
     */
    List<CourseSelection> getCourseSelectionsByTeacherId(String teacherId);
    
    /**
     * 查询所有选课记录列表
     * @return 选课记录列表
     */
    List<CourseSelection> getAllCourseSelections();
}
