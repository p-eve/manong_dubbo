package me.manong.web.controller;

import io.swagger.annotations.Api;
import me.manong.question.entity.Question;
import me.manong.common.utils.Result;
import me.manong.question.service.QuestionService;
import me.manong.web.pojo.QuestionRequest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Api(tags = "提问 Controller")
@Validated
@RequestMapping(value = "/question")
@RestController
public class QuestionController {


    @DubboReference
    private QuestionService questionService;


    /**
     * 添加提问
     * @param questionRequest
     * @return
     */
    @PostMapping("add")
    public Result addQuestion(HttpServletRequest request, @Valid @RequestBody QuestionRequest questionRequest){

        HttpSession session = request.getSession();
        Long userId=Long.parseLong(session.getAttribute("loginUserId").toString());

        Question question = new Question();
        BeanUtils.copyProperties(questionRequest, question);

        question.setCreateTime(new Date());
        question.setUserId(userId);
        question.setViewTimes(0);
        this.questionService.addQuestion(question);
        return Result.success();
    }

    /**
     * 查询全部提问
     * @return
     */
    @GetMapping("allQuestion")
    public Result<List<Question>> allQuestion(){
        return  Result.success(this.questionService.getAllQuestion());
    }

    /**
     * 查询全部提问
     * @return
     */
    @GetMapping("get/{id}")
    public Result<Question> getQuestion(@PathVariable(value = "id") @NotNull(message = "提问编号不能为空") @Min(value = 1,message = "提问编号不能小于1")Long id){
        return  Result.success(this.questionService.getQuestionById(id));
    }

}
