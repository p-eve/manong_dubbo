package me.manong.question.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("question")
public class Question implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("des")
    private String des;

    @TableField("tag")
    private String tag;

    @TableField("view_times")
    private int viewTimes;

    @TableField("user_id")
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("create_time")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private Date updateTime;

    @TableField("status")
    private String status;

    @TableField("thumbs_up")
    private int thumbsUp;

    @TableField("comments")
    private int comments;

    @TableField("content")
    private String content;

}
