package com.cj.controller;

import com.cj.common.R;
import com.cj.entity.Role;
import com.cj.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    //新增角色
    @PostMapping
    public R<String> insertRole(@RequestBody Role role){
        Boolean flag = roleService.insertRole(role);
        if (flag){
            return R.success("添加成功！");
        }
        return R.error("添加失败！");
    }

    //分页查询角色信息
    @RequiresRoles("角色管理")
    @GetMapping("/page")
    public R page(@RequestParam(value = "page",defaultValue = "1") Integer page,
                 @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                 String name){
        return roleService.page(page,pageSize,name);
    }

    //删除角色
    @DeleteMapping("/{id}")
    public R<String> deleteRole(@PathVariable Integer id){
        Boolean flag = roleService.deleteRole(id);
        if (flag){
            return R.success("删除成功！");
        }
        return R.error("删除失败！");
    }

    //根据id查询角色
    @GetMapping("/{id}")
    public R<Role> findById(@PathVariable Integer id){
        Role role = roleService.getById(id);
        return R.success(role);
    }
}
