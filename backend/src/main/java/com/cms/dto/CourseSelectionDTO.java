package com.cms.dto;

import lombok.Data;

/**
 * 选课记录DTO
 * 用于选课记录数据传输
 * @author 张嘉欢
 * @date 2025-11-30
 */
@Data
public class CourseSelectionDTO {
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
     * 状态
     */
    private String status;
    
    /**
     * 失败原因
     */
    private String failReason;
}
