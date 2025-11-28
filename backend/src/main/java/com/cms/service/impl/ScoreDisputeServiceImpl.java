package com.cms.service.impl;

import com.cms.entity.Score;
import com.cms.entity.ScoreDispute;
import com.cms.dao.ScoreDisputeDao;
import com.cms.dto.ScoreDTO;
import com.cms.dto.ScoreDisputeDTO;
import com.cms.service.ScoreDisputeService;
import com.cms.service.ScoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 成绩异议Service实现类
 * 实现成绩异议处理的业务逻辑
 * @author 许祖嘉
 * @date 2025-11-28
 */
@Service
public class ScoreDisputeServiceImpl implements ScoreDisputeService {
    
    @Autowired
    private ScoreDisputeDao scoreDisputeDao;
    
    @Autowired
    private ScoreService scoreService;
    
    @Override
    @Transactional
    public ScoreDispute submitDispute(ScoreDisputeDTO scoreDisputeDTO) {
        ScoreDispute scoreDispute = new ScoreDispute();
        BeanUtils.copyProperties(scoreDisputeDTO, scoreDispute);
        
        scoreDispute.setApplyTime(LocalDateTime.now());
        scoreDispute.setAuditStatus("待审核");
        
        scoreDisputeDao.insertScoreDispute(scoreDispute);
        return scoreDispute;
    }
    
    @Override
    @Transactional
    public ScoreDispute auditDispute(ScoreDisputeDTO scoreDisputeDTO) {
        // 检查成绩异议是否存在
        ScoreDispute existingDispute = scoreDisputeDao.selectScoreDisputeById(
                scoreDisputeDTO.getDisputeId());
        if (existingDispute == null) {
            throw new RuntimeException("成绩异议不存在");
        }
        
        ScoreDispute scoreDispute = new ScoreDispute();
        BeanUtils.copyProperties(scoreDisputeDTO, scoreDispute);
        
        scoreDispute.setAuditTime(LocalDateTime.now());
        
        scoreDisputeDao.updateScoreDispute(scoreDispute);
        
        // 如果审核通过，需要更新成绩
        if ("通过".equals(scoreDispute.getAuditStatus())) {
            // 检查是否提供了新成绩
            if (scoreDisputeDTO.getNewScore() == null) {
                throw new RuntimeException("审核通过时必须提供新成绩");
            }
            
            // 根据studentId和courseId查询当前成绩
            Score currentScore = scoreService.getScoreByStudentAndCourse(
                    existingDispute.getStudentId(), existingDispute.getCourseId());
            
            if (currentScore != null) {
                // 创建ScoreDTO对象，设置新的成绩值
                ScoreDTO scoreDTO = new ScoreDTO();
                scoreDTO.setScoreId(currentScore.getScoreId());
                scoreDTO.setStudentId(currentScore.getStudentId());
                scoreDTO.setCourseId(currentScore.getCourseId());
                scoreDTO.setScore(scoreDisputeDTO.getNewScore());
                scoreDTO.setInputTeacherId(scoreDisputeDTO.getAuditUserId());
                scoreDTO.setStatus(currentScore.getStatus());
                
                // 更新成绩
                scoreService.updateScore(scoreDTO);
            } else {
                throw new RuntimeException("未找到对应的成绩记录");
            }
        }
        
        return scoreDispute;
    }
    
    @Override
    @Transactional
    public boolean deleteDispute(Integer disputeId) {
        // 检查成绩异议是否存在
        ScoreDispute existingDispute = scoreDisputeDao.selectScoreDisputeById(disputeId);
        if (existingDispute == null) {
            return false;
        }
        
        return scoreDisputeDao.deleteScoreDisputeById(disputeId) > 0;
    }
    
    @Override
    public ScoreDispute getDisputeById(Integer disputeId) {
        return scoreDisputeDao.selectScoreDisputeById(disputeId);
    }
    
    @Override
    public List<ScoreDispute> getDisputesByStudentId(String studentId) {
        return scoreDisputeDao.selectScoreDisputesByStudentId(studentId);
    }
    
    @Override
    public List<ScoreDispute> getDisputesByCourseId(String courseId) {
        return scoreDisputeDao.selectScoreDisputesByCourseId(courseId);
    }
    
    @Override
    public List<ScoreDispute> getDisputesByTeacherId(String teacherId) {
        return scoreDisputeDao.selectScoreDisputesByTeacherId(teacherId);
    }
    
    @Override
    public List<ScoreDispute> getAllDisputes() {
        return scoreDisputeDao.selectAllScoreDisputes();
    }
    
    @Override
    public List<ScoreDispute> getDisputesByAuditStatus(String auditStatus) {
        return scoreDisputeDao.selectScoreDisputesByAuditStatus(auditStatus);
    }
}