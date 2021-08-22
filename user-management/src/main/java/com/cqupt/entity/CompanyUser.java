package com.cqupt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "company_user")
@TableName(value = "company_user")
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "用户")
@Accessors(chain = true)
public class CompanyUser {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(hidden = true)
    private int id;

    @Column(unique = true)
    @ApiModelProperty(name = "username")
    private String username;

    @TableField
    @Column(nullable = false)
    @ApiModelProperty(name = "password")
    private String password;

    @TableField
    @Column(nullable = false)
    @ApiModelProperty(name = "email")
    private String email;

    @TableField(value = "create_time")
    @ApiModelProperty(hidden = true)
    private Timestamp createTime;


    @TableField
    @Column(nullable = false)
    @ApiModelProperty(name = "status")
    private boolean status;


    @TableField(exist = false)
    @ManyToMany(fetch = FetchType.LAZY)
//   JoinTable是中间表表名，joinColumns指定中间表中关联自己ID的字段,joinColumn是列名，inverseJoinColumns表示中间表中关联对方ID的字段。
    @JoinTable(name = "user_role_relation",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", create_time=" + createTime +
                ", status=" + status +
                '}';
    }
}
