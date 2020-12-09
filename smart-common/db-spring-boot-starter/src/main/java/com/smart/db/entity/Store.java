package com.smart.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "tb_store")
public class Store {
    @TableId(value = "store_id", type = IdType.AUTO)
    private Long storeId;

    @TableField(value = "quantity")
    private Integer quantity;

    public static final String COL_STORE_ID = "store_id";

    public static final String COL_QUANTITY = "quantity";
}