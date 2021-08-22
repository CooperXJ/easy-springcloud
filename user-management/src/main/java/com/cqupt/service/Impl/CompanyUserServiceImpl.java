package com.cqupt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqupt.entity.CompanyUser;
import com.cqupt.entity.Permission;
import com.cqupt.entity.Role;
import com.cqupt.mapper.CompanyUserMapper;
import com.cqupt.service.CompanyUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyUserServiceImpl implements CompanyUserService {
    @Resource
    private CompanyUserMapper companyUserMapper;

    @Override
    public CompanyUser findByUserName(String userName) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username",userName);
        return companyUserMapper.selectOne(wrapper);
    }

    @Override
    public IPage<CompanyUser> getAllUsers(int current, int size) {
        Page<CompanyUser> page = new Page<>(current,size);
        return companyUserMapper.selectPage(page,null);
    }

    @Override
    public int deleteUser(String userId) {
        return companyUserMapper.deleteById(userId);
    }

    @Override
    public int changePassword(String userId, String newPassword) {
        return companyUserMapper.changePassword(userId,newPassword);
    }

    @Override
    public int changeInfo(CompanyUser user) {
        return companyUserMapper.updateById(user);
    }

    @Override
    public int insertUser(CompanyUser companyUser) {
        return companyUserMapper.insert(companyUser);
    }

    @Override
    public List<Role> getRoleList(String username) {
        return companyUserMapper.getRoleList(username);
    }

    @Override
    public List<Permission> getPermissionList(String username) {
        return companyUserMapper.getPermissionList(username);
    }
}
