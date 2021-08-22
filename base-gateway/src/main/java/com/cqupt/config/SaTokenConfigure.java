package com.cqupt.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        List<String> swagger = new ArrayList<>();
        // config
        swagger.add("/swagger-ui.html");
        swagger.add("/swagger-resources/**");
        swagger.add("/v2/api-docs/**");
        swagger.add("/webjars/springfox-swagger-ui/**");

        List<String> others = new ArrayList<>();
        others.add("/favicon.ico");
        others.add("/user/**");
        others.add("/power/companyUser/gateway/***");
        others.add("/power/companyUser/v2/api-docs");

        List<String> all = new ArrayList<>();
        all.addAll(swagger);
        all.addAll(others);

        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")
            // 开放地址
            .setExcludeList(all)
            // 鉴权方法：每次访问进入
            .setAuth(r -> {
                // 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                SaRouter.match("/**", () -> StpUtil.checkLogin());

                // 权限认证 -- 不同模块, 校验不同权限
                SaRouter.match("/user/**", () -> StpUtil.checkRole("admin"));
                SaRouter.match("/admin/**", () -> StpUtil.checkPermission("admin"));
                SaRouter.match("/goods/**", () -> StpUtil.checkPermission("goods"));
                SaRouter.match("/orders/**", () -> StpUtil.checkPermission("orders"));

                // ...
            })
            // 异常处理方法：每次setAuth函数出现异常时进入
            .setError(e -> SaResult.error(e.getMessage()))
            ;
    }
}