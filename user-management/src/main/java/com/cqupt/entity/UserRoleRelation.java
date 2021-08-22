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
@Table(name = "user_role_relation")
@TableName(value = "user_role_relation")
@Entity
public class UserRoleRelation {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField
    private int user_id;
    @TableField
    private int role_id;
}