package com.cj.controller;

import com.cj.common.R;
import com.cj.entity.User;
import com.cj.entity.vo.UserVo;
import com.cj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    //登录
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

    //退出
    @PostMapping("/logout")
    public R<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        return R.success("退出成功！");
    }

    //分页查询
    @RequiresRoles("用户管理")
    @GetMapping("/page")
    public R page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize, String name, String username){
        return userService.page(page,pageSize,name,username);
    }

    @RequiresPermissions("user:delete")
    @DeleteMapping()
    public R<String> remove(Integer id){
        Boolean flag = userService.removeUser(id);
        if (flag){
            return R.success("删除成功！");
        }
        return R.error("删除失败！");
    }

    @RequiresPermissions("user:enable")
    @PutMapping("/enable")
    public R<String> enableUser(Integer id){
        Boolean flag = userService.enableUser(id);
        if (flag){
            return R.success("修改用户状态成功！");
        }
        return R.error("修改用户状态失败！");
    }

    @GetMapping("/userInfo")
    public R<UserVo> userInfo(Integer id){
        UserVo userInfo = userService.userInfo(id);
        if (userInfo != null){
            return R.success(userInfo);
        }
        return R.error("出错啦！");
    }

    @RequiresPermissions("user:add")
    @PostMapping
    public R<String> insertUser(@RequestBody User user){
        boolean flag = userService.insertUser(user);
        if (flag){
            R.success("添加成功！");
        }
        return R.error("添加失败！");
    }

    @RequiresPermissions("user:edit")
    @PutMapping("/edit")
    public R<String> editUser(@RequestBody User user){
        boolean flag = userService.updateById(user);
        if (flag){
            return R.success("修改成功！");
        }
        return R.error("修改失败！");
    }
}
