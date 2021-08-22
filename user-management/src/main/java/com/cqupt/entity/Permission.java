package com.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@TableName(value = "permission")
@Table(name = "permission")
@Entity
@NoArgsConstructor
public class Permission {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField
    private String name;
    @TableField
    private String value;
    @TableField(value = "create_time")
    private Timestamp createTime;
    @TableField
    private boolean status;

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", create_time=" + createTime +
                ", status=" + status +
                '}';
    }
}