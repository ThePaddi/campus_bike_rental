package com.cj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cj.common.R;
import com.cj.entity.Role;

import java.util.List;


public interface RoleService extends IService<Role> {
    public List<Integer> getRolesByUserId(Integer userId);

    Boolean insertRole(Role role);

    R page(Integer page, Integer pageSize, String name);

    Boolean deleteRole(Integer id);

    public Boolean deleteUserRole(Integer id);
}
