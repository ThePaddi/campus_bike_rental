package com.cj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cj.entity.RentalPoints;
import com.cj.service.RentalPointsService;
import com.cj.mapper.RentalPointsMapper;
import org.springframework.stereotype.Service;

/**
* @author liu
* @description 针对表【rental_points(租赁地点)】的数据库操作Service实现
* @createDate 2024-03-16 16:52:32
*/
@Service
public class RentalPointsServiceImpl extends ServiceImpl<RentalPointsMapper, RentalPoints>
    implements RentalPointsService{

}




