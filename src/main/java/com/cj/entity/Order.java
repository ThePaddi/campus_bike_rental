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
 * @TableName order
 */
@TableName(value ="order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer bikeId;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态，0禁用，1启用
     */
    private Integer status;

   

}