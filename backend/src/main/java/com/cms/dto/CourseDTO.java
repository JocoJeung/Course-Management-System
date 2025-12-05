package com.cms.dto;

import lombok.Data;

/**
 * 课程DTO
 * 用于课程数据传输
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Data
public class CourseDTO {
    /**
     * 课程号
     */
    private String courseId;
    
    /**
     * 课程名
     */
    private String courseName;
    
    /**
     * 学分
     */
    private Integer credit;
    
    /**
     * 课程类型
     */
    private String courseType;
    
    /**
     * 授课教师ID
     */
    private String teacherId;
    
    /**
     * 上课时间
     */
    private String classTime;
    
    /**
     * 上课地点
     */
    private String classLocation;
    
    /**
     * 容量
     */
    private Integer capacity;
    
    /**
     * 简介
     */
    private String intro;
}
