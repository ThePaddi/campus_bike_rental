package com.cj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cj.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package com.cj.mapper
 * @company 千锋教育
 * @date 2024/3/16 15:40
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
