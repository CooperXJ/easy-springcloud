package com.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@TableName(value = "role_permission_relation")
@Table(name = "role_permission_relation")
@Entity
public class RolePermissionRelation {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField
    private int role_id;
    @TableField
    private int permission_id;
}