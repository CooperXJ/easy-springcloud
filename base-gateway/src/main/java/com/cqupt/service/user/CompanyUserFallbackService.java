package com.cqupt.service.user;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyUserFallbackService implements CompanyUserService {

    @Override
    public String searchUser(String username) {
        return null;
    }

    @Override
    public List<String> getUserRolesByName(String username) {
        return null;
    }

    @Override
    public List<String> getUserPermissionsByName(String username) {
        return null;
    }
}
