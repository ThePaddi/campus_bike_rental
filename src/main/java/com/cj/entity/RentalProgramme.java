package com.cj.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 租赁方案
 * @TableName rental_programme
 */
@TableName(value ="rental_programme")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalProgramme implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 方案名称
     */
    private String name;

    /**
     * 租赁价格
     */
    private BigDecimal price;

    /**
     * 状态，0禁用，1启用
     */
    private Integer status;


}