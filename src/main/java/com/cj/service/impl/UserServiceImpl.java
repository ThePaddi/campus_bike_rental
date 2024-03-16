package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.User;
import com.cj.mapper.UserMapper;
import com.cj.service.UserService;
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
}
