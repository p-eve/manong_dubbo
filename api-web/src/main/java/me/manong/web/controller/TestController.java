package me.manong.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.manong.user.entity.User;
import me.manong.user.service.UserService;
import me.manong.common.utils.Result;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "测试 Controller")
@RequestMapping(value = "/test")
@RestController
public class TestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference
    private UserService userService;

    @GetMapping("/get")
    public Result<User> getUser(){
        return Result.success(this.userService.getUserById(1L));
    }

    @GetMapping("/test")
    public Result<String> test(HttpServletRequest request){
        request.getSession().setAttribute("value", "app");
        return Result.success("test");
    }

    //获取session中的参数
    @GetMapping("getValue")
    public Result<String> getValue(HttpServletRequest request){
        String v=(String) request.getSession().getAttribute("username");
        return Result.success(v);
    }

    @ApiOperation(value ="测试登录接口",notes = "注意点说明：接口通过username password登录，参数必须传递")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query",defaultValue = "vincent"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String", paramType = "query",defaultValue = "123456"),
    })
    @GetMapping("login")
    public Result<String> login(HttpServletRequest request, String username, String password){
        User user=this.userService.getUserByUsername(username);

        if (user != null &&user.getPassword().equals(password))
        {
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId", user.getId());
            session.setAttribute("username", username);
            redisTemplate.opsForValue().set("loginUser:" + user.getId(), session.getId());
            return Result.success(session.getId());
        }
        else
        {
            return Result.error("用户不存在或密码错误！");
        }

    }

    @GetMapping("logout")
    public Result<String> logout(HttpServletRequest request){
        if(request.getSession().getAttribute("loginUserId")!=null)
        {
            this.redisTemplate.delete("loginUser:" + (long) request.getSession().getAttribute("loginUserId"));
        }
        request.getSession().invalidate();
        return Result.success();
    }
}
