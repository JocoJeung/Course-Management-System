package com.cms.dto;

import lombok.Data;

/**
 * 成绩异议DTO
 * 用于成绩异议数据传输
 * @author 许祖嘉
 * @date 2025-11-28
 */
@Data
public class ScoreDisputeDTO {
    /**
     * 异议ID
     */
    private Integer disputeId;
    
    /**
     * 学号
     */
    private String studentId;
    
    /**
     * 课程号
     */
    private String courseId;
    
    /**
     * 异议理由
     */
    private String reason;
    
    /**
     * 附件路径
     */
    private String attachment;
    
    /**
     * 审核状态
     */
    private String auditStatus;
    
    /**
     * 审核意见
     */
    private String auditOpinion;
    
    /**
     * 审核人ID
     */
    private String auditUserId;
    
    /**
     * 新成绩（审核通过时使用）
     */
    private Double newScore;
}