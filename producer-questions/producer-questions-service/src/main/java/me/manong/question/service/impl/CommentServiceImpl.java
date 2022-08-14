package me.manong.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.manong.question.entity.Comment;
import me.manong.question.mapper.CommentMapper;
import me.manong.question.service.CommentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@DubboService
@Transactional
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public void addComment(Comment comment) {
        this.commentMapper.insert(comment);
    }

    @Override
    public List<Comment> getCommentByQuestion(Long questionId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<Comment>();
        queryWrapper.lambda().eq(Comment::getQuestionId,questionId);
        return this.commentMapper.selectList(queryWrapper);
    }
}
