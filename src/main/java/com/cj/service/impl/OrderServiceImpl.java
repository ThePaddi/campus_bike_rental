package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.Order;
import com.cj.service.OrderService;
import com.cj.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【order】的数据库操作Service实现
* @createDate 2024-03-16 16:49:34
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}




