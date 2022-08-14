package me.manong.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.manong.question.entity.Question;
import me.manong.question.mapper.QuestionMapper;
import me.manong.question.service.QuestionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@DubboService
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public void addQuestion(Question question) {
        this.questionMapper.insert(question);
    }

    @Override
    public List<Question> getAllQuestion() {
        return this.questionMapper.getAllQuestion();
    }

    public Question getQuestionById(Long id){
        return this.questionMapper.selectById(id);
    }

    /**
     *
     * @param page
     * @param sortName 排序名称
     * @return
     */
    public Page<Question> getPageQuestions(Page<Question> page, String sortName){
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<Question>();
        queryWrapper.orderByDesc(Question::getCreateTime);
        return this.questionMapper.selectPage(page,queryWrapper);
    }

}
