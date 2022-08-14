package me.manong.question.service;

import me.manong.question.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * 添加评论
     */
    public void addComment(Comment comment);

    /**
     * 按提问id查询评论
     * @param questionId
     * @return
     */
    public List<Comment> getCommentByQuestion(Long questionId);
}
