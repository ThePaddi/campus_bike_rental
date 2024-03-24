package com.cj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.common.R;
import com.cj.entity.Role;
import com.cj.entity.User;
import com.cj.entity.vo.UserVo;
import com.cj.mapper.RoleMapper;
import com.cj.mapper.UserMapper;
import com.cj.service.MenuService;
import com.cj.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;

    @Override
    public boolean insertUser(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        String username = user.getUsername();
        queryWrapper.eq(User::getUsername,username);
        if (this.count(queryWrapper) != 0 || username.length() > 30){
            return false;
        }
        String password = user.getPassword();
        if (password.length() > 16){
            return false;
        }
        Md5Hash md5Hash = new Md5Hash(password,"salt",3);
        user.setPassword(md5Hash.toHex());
        this.save(user);
        return true;
    }

    @Override
    public R page(Integer page, Integer pageSize, String name, String username) {
        IPage<User> p = new Page<>(page,pageSize);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(User::getStatus,1);
        queryWrapper.like(name != null,User::getName,name).or()
                .like(username != null,User::getUsername,username);
        this.page(p,queryWrapper);
        return R.success(p);
    }

    @Override
    public Boolean removeUser(Integer id) {
        User user = this.getById(id);
        if (user != null){
            user.setStatus(0);
            this.updateById(user);
            return true;
        }
        return false;
    }



    @Override
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = this.getOne(queryWrapper);
        return user;
    }

    //查询用户角色
    @Override
    public List<String> getRolesByUsername(String username){
        List<String> userRoles = null;
        userRoles = userMapper.getRolesByUsername(username);
        return userRoles;
    }

    public List<Role> getRolesByUserId(Integer userId){
        List<Role> userRoles = null;
        userRoles = userMapper.getUserRoles(userId);
        return userRoles;
    }

    public List<Integer> getRolesIdByUserId(Integer userId){
        List<Integer> userRolesId = null;
        userRolesId = userMapper.getUserRolesId(userId);
        return userRolesId;
    }

    @Override
    public UserVo userInfo(Integer id) {
        UserVo userInfo = new UserVo();
        User user = this.getById(id);
        if (user.getStatus().intValue() == 0){
            return null;
        }
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setUsername(user.getUsername());
        userInfo.setIdentity(user.getIdentity());
        userInfo.setGender(user.getGender());
        userInfo.setImage(user.getImage());
        userInfo.setPhone(user.getPhone());
        userInfo.setRoles(this.getRolesByUsername(user.getUsername()));
        userInfo.setPerms(this.menuService.findMenusByUserId(id));
        return userInfo;
    }

    @Override
    public List<String> getPermsByUsername(String username) {
        List<String> perms = userMapper.getPermsByUsername(username);
        return perms;
    }

    @Override
    public Boolean enableUser(Integer id) {
        User user = this.getById(id);
        if (user != null){
            user.setStatus(1);
            this.updateById(user);
            return true;
        }
        return false;
    }
}
