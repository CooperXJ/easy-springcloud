package com.cqupt.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cqupt.constants.RSA;
import com.cqupt.dto.CommonResult;
import com.cqupt.entity.CompanyUser;
import com.cqupt.entity.Permission;
import com.cqupt.entity.Role;
import com.cqupt.service.CompanyUserService;
import com.cqupt.utils.RSAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Api("companyUser")
public class CompanyUserController {
    @Resource
    private CompanyUserService companyUserService;


    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/registered",method = RequestMethod.POST)
    @ApiOperation("用户注册")
    public CommonResult registered(@RequestBody CompanyUser user){
        CompanyUser companyUser = companyUserService.findByUserName(user.getUsername());
        if(companyUser!=null){
            return new CommonResult(-1,"用户名已存在",null);
        }

        //对用户的密码加密
        user.setPassword(RSAUtil.encrypt(user.getPassword(), RSA.publicKey));
        int count = companyUserService.insertUser(user);
        if(count==1) {
            return CommonResult.success("新建用户成功", user);
        }else{
            return CommonResult.fail("新建用户失败");
        }
    }

    /**
     * 超级管理员查询用户
     *
     * @return Response
     */
    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    @ApiOperation("超级管理员查询用户")
    public CommonResult getAllUsers(@RequestParam("current") int current, @RequestParam("size") int size) {
        IPage<CompanyUser> allUsers = companyUserService.getAllUsers(current, size);
        return CommonResult.success("查询成功",allUsers);
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteUser",method = RequestMethod.DELETE)
    @ApiOperation("删除用户")
    public CommonResult deleteUser(@RequestParam("userId") String userId) {
        int count = companyUserService.deleteUser(userId);
        if(count==1){
            return CommonResult.success("删除成功",userId);
        }
        return CommonResult.success("该用户不存在",null);
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @RequestMapping(value = "/searchUser",method = RequestMethod.GET)
    @ApiOperation("根据用户名查找用户")
    public CommonResult searchUser(@RequestParam("username")String username) {
        CompanyUser companyUser = companyUserService.findByUserName(username);
        if(companyUser==null){
            return CommonResult.fail("未查询到该用户");
        }
        return CommonResult.success("查询成功",companyUser);
    }

    /**
     * 查找用户角色
     * @param username
     * @return
     */
    @RequestMapping(value = "/getUserRolesByName",method = RequestMethod.GET)
    @ApiOperation("查找用户角色")
    public CommonResult getUserRolesByName(@RequestParam("username")String username) {
        List<Role> roles = companyUserService.getRoleList(username);
        if(roles==null){
            return CommonResult.fail("未查询到该用户");
        }
        return CommonResult.success("查询成功",roles);
    }

    /**
     * 查找用户权限
     * @param username
     * @return
     */
    @RequestMapping(value = "/getUserPermissionsByName",method = RequestMethod.GET)
    @ApiOperation("查找用户权限")
    public CommonResult getUserPermissionsByName(@RequestParam("username")String username) {
        List<Permission> permissions = companyUserService.getPermissionList(username);
        if(permissions==null){
            return CommonResult.fail("未查询到该用户");
        }
        return CommonResult.success("查询成功",permissions);
    }

    /**
     * 修改用户信息
     * @param companyUser
     * @return
     */
    @RequestMapping(value = "/changeInfo",method = RequestMethod.POST)
    @ApiOperation("修改用户信息")
    public CommonResult changeInfo(@RequestBody CompanyUser companyUser) {
        int count = companyUserService.changeInfo(companyUser);
        if(count==0){
            return CommonResult.fail("未查询到该用户");
        }
        return CommonResult.success("修改成功",companyUser);
    }

    /**
     * 修改用户密码
     * @param userId
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ApiOperation("修改用户密码")
    public CommonResult updatePassword(@RequestParam String userId,@RequestParam String newPassword) {
        int count = companyUserService.changePassword(userId,newPassword);
        if(count==0){
            return CommonResult.fail("未查询到该用户");
        }
        return CommonResult.success("修改成功",newPassword);
    }

    /*
        FOR  GATEWAY
     */

    /**
     * 查找用户权限 For gateway
     * @param username
     * @return
     */
    @RequestMapping(value = "/gateway/getUserPermissionsByNameForGateWay",method = RequestMethod.GET)
    public List<String> getUserPermissionsByNameForGateWay(@RequestParam("username")String username) {
        List<Permission> permissions = companyUserService.getPermissionList(username);
        if(permissions==null){
            return null;
        }
        return permissions.stream().map(permission -> permission.getValue()).collect(Collectors.toList());
    }

    /**
     * 查找用户角色  For gateway
     * @param username
     * @return
     */
    @RequestMapping(value = "/gateway/getUserRolesByNameForGateWay",method = RequestMethod.GET)
    public List<String> getUserRolesByNameForGateWay(@RequestParam("username")String username) {
        List<Role> roles = companyUserService.getRoleList(username);
        if(roles==null){
            return null;
        }
        return roles.stream().map(role -> role.getName()).collect(Collectors.toList());
    }

    /**
     * 根据用户名查找用户 For gateway
     * @param username
     * @return
     */
    @RequestMapping(value = "/gateway/searchUserForGateWay",method = RequestMethod.GET)
    public String searchUserForGateWay(@RequestParam("username")String username) {
        CompanyUser companyUser = companyUserService.findByUserName(username);
        if(companyUser==null){
            return null;
        }
        return companyUser.getPassword();
    }
}
