package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.Role;
import com.cj.service.RoleService;
import com.cj.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【role】的数据库操作Service实现
* @createDate 2024-03-16 16:55:14
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




