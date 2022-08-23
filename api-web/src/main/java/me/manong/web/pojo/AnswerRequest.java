package me.manong.web.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value="回答入参对象",description="回答入参对象")
public class AnswerRequest {

    @ApiModelProperty(value="回答id，添加可不填")
    private Long id;

    @ApiModelProperty(value="问题编号")
    @NotNull(message = "回答不能为空")
    private Long questionId;

    @ApiModelProperty(value="回答")
    @NotEmpty(message = "回答不能为空")
    @Length(min = 5, max = 600, message = "回答长度必须在5-600之间")
    private String content;
}
