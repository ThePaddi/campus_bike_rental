package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.Bikes;
import com.cj.service.BikesService;
import com.cj.mapper.BikesMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【bikes】的数据库操作Service实现
* @createDate 2024-03-16 16:42:35
*/
@Service
public class BikesServiceImpl extends ServiceImpl<BikesMapper, Bikes>
    implements BikesService{

}




