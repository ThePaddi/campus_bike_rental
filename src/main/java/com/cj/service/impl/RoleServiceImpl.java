package com.cj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.common.R;
import com.cj.entity.Role;
import com.cj.mapper.RoleMapper;
import com.cj.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    //查询用户角色
    @Override
    public List<Integer> getRolesByUserId(Integer userId){
        List<Integer> userRoles = null;
        userRoles = roleMapper.selectRoleIdsByUserId(userId);
        return userRoles;
    }


    @Override
    public Boolean insertRole(Role role) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getName,role.getName());
        int count = this.count(queryWrapper);
        if (count > 0){
            return false;
        }
        this.save(role);
        return true;
    }

    public R page(Integer page, Integer pageSize, String name) {
        IPage<Role> p = new Page<>(page,pageSize);
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Role::getName, name);
        roleMapper.selectPage(p,queryWrapper);
        return R.success(p);
    }

    @Transactional
    @Override
    public Boolean deleteRole(Integer id) {
        Role role = this.getById(id);
        if (role == null){
            return false;
        }
        boolean flag = this.removeById(id);
        deleteUserRole(id);
        return flag;
    }

    @Override
    public Boolean deleteUserRole(Integer id){
        Boolean flag = roleMapper.deleteUserRole(id);
        if (flag){
            return true;
        }
        return false;
    }
}
