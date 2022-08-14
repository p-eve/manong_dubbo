package me.manong.question.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.manong.question.entity.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 添加提问
     * @param question
     */
    public void addQuestion(Question question);

    /**
     * 查看全部提问
     * @return
     */
    public List<Question> getAllQuestion();

    /**
     * 按ID查询提问
     * @param id
     * @return
     */
    public Question getQuestionById(Long id);

    /**
     *
     * @param page
     * @param sortName 排序名称
     * @return
     */
    public Page<Question> getPageQuestions(Page<Question> page,String sortName);
}
