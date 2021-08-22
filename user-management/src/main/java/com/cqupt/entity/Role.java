package com.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
@TableName(value = "role")
@NoArgsConstructor
public class Role {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField
    private String name;
    @TableField
    private String description;
    @TableField(value = "create_time")
    private Timestamp createTime;
    @TableField
    private boolean status;

    @TableField(exist = false)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "role_permission_relation",joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    @JsonIgnore
    private Set<Permission> permissions;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", create_time=" + createTime +
                ", status=" + status +
                '}';
    }
}