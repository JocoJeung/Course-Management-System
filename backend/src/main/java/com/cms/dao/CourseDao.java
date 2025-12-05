package com.cms.dao;

import com.cms.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程DAO接口
 * 定义课程表的数据库操作方法
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Mapper
public interface CourseDao {
    /**
     * 插入课程记录
     * @param course 课程实体
     * @return 影响行数
     */
    int insertCourse(Course course);
    
    /**
     * 更新课程记录
     * @param course 课程实体
     * @return 影响行数
     */
    int updateCourse(Course course);
    
    /**
     * 根据课程号删除课程记录
     * @param courseId 课程号
     * @return 影响行数
     */
    int deleteCourseById(@Param("courseId") String courseId);
    
    /**
     * 根据课程号查询课程记录
     * @param courseId 课程号
     * @return 课程实体
     */
    Course selectCourseById(@Param("courseId") String courseId);
    
    /**
     * 查询所有课程列表
     * @return 课程列表
     */
    List<Course> selectAllCourses();
    
    /**
     * 根据教师ID查询课程列表
     * @param teacherId 教师ID
     * @return 课程列表
     */
    List<Course> selectCoursesByTeacherId(@Param("teacherId") String teacherId);
    
    /**
     * 根据课程类型查询课程列表
     * @param courseType 课程类型
     * @return 课程列表
     */
    List<Course> selectCoursesByType(@Param("courseType") String courseType);
    
    /**
     * 根据课程名称模糊查询课程列表
     * @param courseName 课程名称
     * @return 课程列表
     */
    List<Course> selectCoursesByName(@Param("courseName") String courseName);
    
    /**
     * 更新课程已选人数
     * @param courseId 课程号
     * @param changeNum 变化数量（正数增加，负数减少）
     * @return 影响行数
     */
    int updateCourseSelectedNum(@Param("courseId") String courseId, @Param("changeNum") Integer changeNum);
}
