package com.cj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author 疾风亦有归途
 * @version v1.0
 * @project campus_bike_rental
 * @package com.cj.entity
 * @company 千锋教育
 * @date 2024/3/16 15:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Byte identity;
    private Byte gender;
    private String phone;
    private String image;
    private LocalDateTime created_time;
    private LocalDateTime updated_time;
    private Byte status;
}
