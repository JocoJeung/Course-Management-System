package com.cms.dao;

import com.cms.entity.CourseSelection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 选课记录DAO接口
 * 定义选课记录表的数据库操作方法
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Mapper
public interface CourseSelectionDao {
    /**
     * 插入选课记录
     * @param courseSelection 选课记录实体
     * @return 影响行数
     */
    int insertCourseSelection(CourseSelection courseSelection);
    
    /**
     * 更新选课记录
     * @param courseSelection 选课记录实体
     * @return 影响行数
     */
    int updateCourseSelection(CourseSelection courseSelection);
    
    /**
     * 根据选课记录ID删除选课记录
     * @param selectionId 选课记录ID
     * @return 影响行数
     */
    int deleteCourseSelectionById(@Param("selectionId") Integer selectionId);
    
    /**
     * 根据学号和课程号删除选课记录
     * @param studentId 学号
     * @param courseId 课程号
     * @return 影响行数
     */
    int deleteCourseSelectionByStudentAndCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);
    
    /**
     * 根据选课记录ID查询选课记录
     * @param selectionId 选课记录ID
     * @return 选课记录实体
     */
    CourseSelection selectCourseSelectionById(@Param("selectionId") Integer selectionId);
    
    /**
     * 根据学号和课程号查询选课记录
     * @param studentId 学号
     * @param courseId 课程号
     * @return 选课记录实体
     */
    CourseSelection selectCourseSelectionByStudentAndCourse(@Param("studentId") String studentId, @Param("courseId") String courseId);
    
    /**
     * 根据学号查询选课记录列表
     * @param studentId 学号
     * @return 选课记录列表
     */
    List<CourseSelection> selectCourseSelectionsByStudentId(@Param("studentId") String studentId);
    
    /**
     * 根据课程号查询选课记录列表
     * @param courseId 课程号
     * @return 选课记录列表
     */
    List<CourseSelection> selectCourseSelectionsByCourseId(@Param("courseId") String courseId);
    
    /**
     * 根据教师ID查询选课记录列表
     * @param teacherId 教师ID
     * @return 选课记录列表
     */
    List<CourseSelection> selectCourseSelectionsByTeacherId(@Param("teacherId") String teacherId);
    
    /**
     * 查询所有选课记录列表
     * @return 选课记录列表
     */
    List<CourseSelection> selectAllCourseSelections();
}
