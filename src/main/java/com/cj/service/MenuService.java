package com.cj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cj.entity.Menu;
import com.cj.entity.vo.RouterVo;

import java.util.List;


public interface MenuService extends IService<Menu> {
    List<Integer> getMenuIdsByRoleId(Integer roleId);

    public List<RouterVo> findUserMenuListByUserId(Integer userId);

    List<String> findMenusByUserId(Integer userId);
}
