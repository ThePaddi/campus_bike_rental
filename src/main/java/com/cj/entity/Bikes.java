package com.cj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName bikes
 */
@TableName(value ="bikes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bikes implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 租赁地点
     */
    private Integer pointId;

    /**
     * 二维码
     */
    private String qrCode;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 是否维修，0否，1是
     */
    private Integer maintenance;

    /**
     * 状态，0禁用，1可用，2维修，3正在使用
     */
    private Integer status;


}