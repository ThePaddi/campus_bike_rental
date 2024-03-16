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
 * 租赁表
 * @TableName rentals
 */
@TableName(value ="rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rentals implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 租赁方案id
     */
    private Integer programmeId;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 剩余时间
     */
    private Date remainder;

    /**
     * 状态，0禁用，1启用
     */
    private Integer status;


}