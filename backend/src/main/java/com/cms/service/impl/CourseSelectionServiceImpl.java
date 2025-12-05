package com.cms.service.impl;

import com.cms.entity.Course;
import com.cms.entity.CourseSelection;
import com.cms.dao.CourseDao;
import com.cms.dao.CourseSelectionDao;
import com.cms.service.CourseSelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 选课记录Service实现类
 * 实现选课管理的业务逻辑
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Service
public class CourseSelectionServiceImpl implements CourseSelectionService {
    
    @Autowired
    private CourseSelectionDao courseSelectionDao;
    
    @Autowired
    private CourseDao courseDao;
    
    @Override
    @Transactional
    public CourseSelection selectCourse(String studentId, String courseId) {
        // 检查课程是否存在
        Course course = courseDao.selectCourseById(courseId);
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }
        
        // 检查课程是否已满
        if (course.getSelectedNum() >= course.getCapacity()) {
            throw new RuntimeException("课程已满");
        }
        
        // 检查是否已选该课程
        CourseSelection existingSelection = courseSelectionDao.selectCourseSelectionByStudentAndCourse(studentId, courseId);
        if (existingSelection != null) {
            throw new RuntimeException("已选该课程");
        }
        
        // 创建选课记录
        CourseSelection courseSelection = new CourseSelection();
        courseSelection.setStudentId(studentId);
        courseSelection.setCourseId(courseId);
        courseSelection.setSelectionTime(LocalDateTime.now());
        courseSelection.setStatus("成功");
        
        // 插入选课记录
        courseSelectionDao.insertCourseSelection(courseSelection);
        
        // 更新课程已选人数
        courseDao.updateCourseSelectedNum(courseId, 1);
        
        return courseSelection;
    }
    
    @Override
    @Transactional
    public boolean dropCourse(String studentId, String courseId) {
        // 检查是否已选该课程
        CourseSelection existingSelection = courseSelectionDao.selectCourseSelectionByStudentAndCourse(studentId, courseId);
        if (existingSelection == null) {
            throw new RuntimeException("未选该课程");
        }
        
        // 删除选课记录
        int result = courseSelectionDao.deleteCourseSelectionByStudentAndCourse(studentId, courseId);
        
        // 更新课程已选人数
        courseDao.updateCourseSelectedNum(courseId, -1);
        
        return result > 0;
    }
    
    @Override
    public CourseSelection getCourseSelectionById(Integer selectionId) {
        return courseSelectionDao.selectCourseSelectionById(selectionId);
    }
    
    @Override
    public CourseSelection getCourseSelectionByStudentAndCourse(String studentId, String courseId) {
        return courseSelectionDao.selectCourseSelectionByStudentAndCourse(studentId, courseId);
    }
    
    @Override
    public List<CourseSelection> getCourseSelectionsByStudentId(String studentId) {
        return courseSelectionDao.selectCourseSelectionsByStudentId(studentId);
    }
    
    @Override
    public List<CourseSelection> getCourseSelectionsByCourseId(String courseId) {
        return courseSelectionDao.selectCourseSelectionsByCourseId(courseId);
    }
    
    @Override
    public List<CourseSelection> getCourseSelectionsByTeacherId(String teacherId) {
        return courseSelectionDao.selectCourseSelectionsByTeacherId(teacherId);
    }
    
    @Override
    public List<CourseSelection> getAllCourseSelections() {
        return courseSelectionDao.selectAllCourseSelections();
    }
}
