package com.cj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 租赁地点
 * @TableName rental_points
 */
@TableName(value ="rental_points")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalPoints implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 具体地点名称
     */
    private String name;

    /**
     * 
     */
    private String city;

    /**
     * 校区
     */
    private String campus;

    /**
     * x坐标
     */
    private Integer coordinateX;

    /**
     * y坐标
     */
    private Integer coordinateY;

    /**
     * 状态，0禁用，1启用
     */
    private Integer status;


}