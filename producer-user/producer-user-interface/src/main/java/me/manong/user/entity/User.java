package me.manong.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {

    //指定主键生成策略为不含中划线的UUID
    //@TableId(value = "id",type = IdType.ASSIGN_UUID)
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("is_account_non_expired")
    private boolean isAccountNonExpired;

    @TableField("is_account_non_locked")
    private boolean isAccountNonLocked;

    @TableField("is_credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @TableField("is_enabled")
    private boolean isEnabled;

    @TableField("create_time")
    private Date createTime;

    @TableField("area_code")
    private String areaCode;

    @TableField("phone")
    private String phone;

    @TableField("address")
    private String address;

    @TableField("birthday")
    private Date birthday;

    @TableField("vip")
    private int vip;

}
