package com.cqupt.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.cqupt.constants.RSA;
import com.cqupt.dto.CommonResult;
import com.cqupt.service.user.CompanyUserService;
import com.cqupt.utils.RSAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Resource
    private CompanyUserService companyUserService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        String read_password = companyUserService.searchUser(username);
//        password = RSAUtil.decrypt(password, RSA.privateKey);
        if(read_password == null) {
            return CommonResult.validateFailed("用户不存在");
        }

        if(!read_password.equals(password)){
            return CommonResult.validateFailed("用户名或密码错误");
        }

        StpUtil.login(username);
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", saTokenInfo.getTokenValue());
        tokenMap.put("tokenHead", saTokenInfo.getTokenName());
        return CommonResult.success("登陆成功",tokenMap);
    }
}
