package com.cqupt.service.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "user-management",path = "/power/companyUser/",fallback = CompanyUserFallbackService.class)
public interface CompanyUserService {
    @RequestMapping(value = "/gateway/searchUserForGateWay",method = RequestMethod.GET)
    String searchUser(@RequestParam("username")String username);

    @RequestMapping(value = "/gateway/getUserRolesByNameForGateWay",method = RequestMethod.GET)
    List<String> getUserRolesByName(@RequestParam("username")String username);

    @RequestMapping(value = "/gateway/getUserPermissionsByNameForGateWay",method = RequestMethod.GET)
    List<String> getUserPermissionsByName(@RequestParam("username")String username);
}

