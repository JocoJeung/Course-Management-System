package com.cms.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 选课记录实体类
 * 对应数据库中的course_selection表
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Data
public class CourseSelection {
    /**
     * 选课记录ID
     */
    private Integer selectionId;
    
    /**
     * 学号
     */
    private String studentId;
    
    /**
     * 课程号
     */
    private String courseId;
    
    /**
     * 选课时间
     */
    private LocalDateTime selectionTime;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 失败原因
     */
    private String failReason;
}
