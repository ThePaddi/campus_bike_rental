package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.Menu;
import com.cj.service.MenuService;
import com.cj.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2024-03-16 16:44:41
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

}




