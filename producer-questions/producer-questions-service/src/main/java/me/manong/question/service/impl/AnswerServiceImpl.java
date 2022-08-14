package me.manong.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.manong.question.entity.Answer;
import me.manong.question.mapper.AnswerMapper;
import me.manong.question.service.AnswerService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@DubboService
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;


    @Override
    public void addAnswer(Answer answer) {
        this.answerMapper.insert(answer);
    }

    @Override
    public List<Answer> getAnswerByQuestion(Long questionId) {
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<Answer>();
        queryWrapper.lambda().eq(Answer::getQuestionId,questionId);
        return this.answerMapper.selectList(queryWrapper);
    }
}
