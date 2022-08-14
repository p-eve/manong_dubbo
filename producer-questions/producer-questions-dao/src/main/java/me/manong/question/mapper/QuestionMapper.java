package me.manong.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.manong.question.entity.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

    public List<Question> getAllQuestion();
}
