package me.manong.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.manong.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    public List<User> getAllUser();

}
