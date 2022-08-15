package me.manong.web.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel(value="提问入参对象",description="提问入参对象")
public class QuestionRequest {

    @ApiModelProperty(value="标题")
    @NotEmpty(message = "标题不能为空")
    @Length(min = 5, max = 60, message = "标题长度必须在5-60之间")
    private String title;

    @ApiModelProperty(value="描述")
    @NotEmpty(message = "提问描述不能为空")
    @Length( max = 300, message = "描述长度最大300")
    private String des;


    @ApiModelProperty(value="标签")
    private String tag;

    @ApiModelProperty(value="内容")
    @NotEmpty(message = "提问内容不能为空")
    private String content;

    @ApiModelProperty(value="图片地址")
    private String imgStr;
}
