package me.manong.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.manong.common.utils.Result;
import me.manong.question.entity.Answer;
import me.manong.question.entity.Question;
import me.manong.question.service.AnswerService;
import me.manong.question.service.QuestionService;
import me.manong.web.pojo.AnswerRequest;
import me.manong.web.pojo.QuestionRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Api(tags = "回答 Controller")
@Validated
@RequestMapping(value = "/answer")
@RestController
public class AnswerController {

    @DubboReference
    private AnswerService answerService;


    @ApiOperation(value ="添加回答")
    @PostMapping("add")
    public Result addAnswer(HttpServletRequest request, @Valid @RequestBody AnswerRequest answerRequest){

        HttpSession session = request.getSession();
        Long userId=Long.parseLong(session.getAttribute("loginUserId").toString());

        Answer answer = new Answer();
        BeanUtils.copyProperties(answerRequest, answer);

        answer.setCreateTime(new Date());
        answer.setUserId(userId);
        this.answerService.addAnswer(answer);
        return Result.success();
    }

    @ApiOperation(value ="修改回答")
    @PostMapping("update")
    public Result updateAnswer(HttpServletRequest request, @Valid @RequestBody AnswerRequest answerRequest){

        HttpSession session = request.getSession();
        Long userId=Long.parseLong(session.getAttribute("loginUserId").toString());

        if(answerRequest.getId()==null)Result.error("id不能为空");

        Answer answer = new Answer();
        BeanUtils.copyProperties(answerRequest, answer);

        answer.setUpdateTime(new Date());
        answer.setUserId(userId);
        this.answerService.updateAnswer(answer);
        return Result.success();
    }

    @ApiOperation(value ="按QuestionId查询回答内容")
    @GetMapping("getAnswerByQuestionId")
    public Result<List<Answer>> getAnswerByQuestionId(@NotNull(message = "questionId不能为空")Long questionId){
        return Result.success(this.answerService.getAnswerByQuestion(questionId));
    }
}
