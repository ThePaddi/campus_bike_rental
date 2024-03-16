package com.cj.controller;

import com.cj.common.R;
import com.cj.entity.User;
import com.cj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package com.cj.controller
 * @company 千锋教育
 * @date 2024/3/16 15:37
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    //注册
    @PostMapping("/register")
    public R<String> register(@RequestBody User user){
        boolean flag = userService.insertUser(user);
        if (!flag){
            return R.error("注册失败！");
        }else {
            return R.success("注册成功！");
        }
    }

    @PostMapping("/login")
    public R<String> login(String username,String password){
        //1 获取 Subject 对象
        Subject subject = SecurityUtils.getSubject();
        //2 封装请求数据到 token 对象中
        AuthenticationToken token = new UsernamePasswordToken(username,password);
        //3 调用 login 方法进行登录认证
        try {
            subject.login(token);
            return R.success("登录成功！");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败！");
            return R.error("登录失败！");
        }
    }
}
