package com.cqupt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.entity.CompanyUser;
import com.cqupt.entity.Permission;
import com.cqupt.entity.Role;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface CompanyUserService {
    /**
     * 通过用户(电话号码)查询用户
     * 原则不允许重复注册,只有测试阶段才会出现,上线时不允许的
     * @param userName
     * @return
     */
    CompanyUser findByUserName(String userName);

    /**
     * 获取所有用户
     * @return
     */
    IPage<CompanyUser> getAllUsers(int current, int size);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    int deleteUser(String userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    int changePassword(String userId,String newPassword);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    int changeInfo(CompanyUser user);

    /**
     * 新建用户
     * @param companyUser
     * @return
     */
    int insertUser(CompanyUser companyUser);

    /**
     * 获取用户的角色
     * @param username
     * @return
     */
    List<Role> getRoleList(@Param("username") String username);

    /**
     * 获取用户的权限
     * @param username
     * @return
     */
    List<Permission> getPermissionList(@Param("username") String username);
}
