package com.cj.controller;

import com.cj.common.R;
import com.cj.entity.vo.RouterVo;
import com.cj.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/routes")
    public R<List<RouterVo>> findUserMenuListByUserId(Integer userId){
        return R.success(menuService.findUserMenuListByUserId(userId));
    }
}
