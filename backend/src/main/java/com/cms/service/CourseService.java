package com.cms.service;

import com.cms.entity.Course;
import com.cms.dto.CourseDTO;

import java.util.List;

/**
 * 课程Service接口
 * 定义课程管理的业务逻辑方法
 * @author 张嘉欢
 * @date 2025-11-30
 */
public interface CourseService {
    /**
     * 创建课程
     * @param courseDTO 课程DTO
     * @return 课程实体
     */
    Course createCourse(CourseDTO courseDTO);
    
    /**
     * 修改课程
     * @param courseDTO 课程DTO
     * @return 课程实体
     */
    Course updateCourse(CourseDTO courseDTO);
    
    /**
     * 删除课程
     * @param courseId 课程号
     * @return 是否成功
     */
    boolean deleteCourse(String courseId);
    
    /**
     * 根据课程号查询课程
     * @param courseId 课程号
     * @return 课程实体
     */
    Course getCourseById(String courseId);
    
    /**
     * 查询所有课程
     * @return 课程列表
     */
    List<Course> getAllCourses();
    
    /**
     * 根据教师ID查询课程
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> getCoursesByTeacherId(String teacherId);
    
    /**
     * 根据课程类型查询课程
     * @param courseType 课程类型
     * @return 课程列表
     */
    List<Course> getCoursesByType(String courseType);
    
    /**
     * 根据课程名称模糊查询课程
     * @param courseName 课程名称
     * @return 课程列表
     */
    List<Course> getCoursesByName(String courseName);
    
    /**
     * 检查课程是否已满
     * @param courseId 课程号
     * @return 是否已满
     */
    boolean isCourseFull(String courseId);
    
    /**
     * 更新课程已选人数
     * @param courseId 课程号
     * @param changeNum 变化数量
     * @return 是否成功
     */
    boolean updateSelectedNum(String courseId, Integer changeNum);
}
