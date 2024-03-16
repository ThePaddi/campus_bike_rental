package com.cj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.User;
import com.cj.mapper.UserMapper;
import com.cj.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package com.cj.service.impl
 * @company 千锋教育
 * @date 2024/3/16 15:56
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
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
    public User getUserByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,username);
        User user = this.getOne(queryWrapper);
        return user;
    }
}
