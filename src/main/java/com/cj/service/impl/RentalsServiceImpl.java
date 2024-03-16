package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.Rentals;
import com.cj.service.RentalsService;
import com.cj.mapper.RentalsMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【rentals(租赁表)】的数据库操作Service实现
* @createDate 2024-03-16 16:53:12
*/
@Service
public class RentalsServiceImpl extends ServiceImpl<RentalsMapper, Rentals>
    implements RentalsService{

}




