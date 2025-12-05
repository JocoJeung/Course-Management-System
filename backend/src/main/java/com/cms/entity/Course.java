package com.cms.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 课程实体类
 * 对应数据库中的course表
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Data
public class Course {
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
     * 已选人数
     */
    private Integer selectedNum;
    
    /**
     * 简介
     */
    private String intro;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
