package com.cqupt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqupt.entity.CompanyUser;
import com.cqupt.entity.Permission;
import com.cqupt.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CompanyUserMapper extends BaseMapper<CompanyUser> {
    @Update("update company_user set password = #{newPassword} where id = #{userId}")
    int changePassword(@Param("userId") String userId, @Param("newPassword") String newPassword);


    @Select("select * from role where id in " +
            "(select r.role_id from company_user u,user_role_relation r where u.id = r.user_id and u.username=#{username})")
    List<Role> getRoleList(@Param("username") String username);

    @Select("select p.* from permission p , role_permission_relation rp, user_role_relation ur,company_user u where u.username=#{username}" +
            "and u.id=ur.user_id and ur.role_id= rp.role_id and rp.permission_id = p.id")
    List<Permission> getPermissionList(@Param("username") String username);
}
