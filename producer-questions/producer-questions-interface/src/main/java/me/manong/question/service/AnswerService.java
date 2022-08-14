package me.manong.question.service;

import me.manong.question.entity.Answer;

import java.util.List;

public interface AnswerService {

    /**
     * 添加提问回答
     * @param answer
     */
    public void addAnswer(Answer answer);

    /**
     * 按提问id查询回答
     * @param questionId
     * @return
     */
    public List<Answer> getAnswerByQuestion(Long questionId);
}
