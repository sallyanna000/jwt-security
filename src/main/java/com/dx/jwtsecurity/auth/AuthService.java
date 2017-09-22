package com.dx.jwtsecurity.auth;

import com.dx.jwtsecurity.repository.SysUser;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/19 9:13
 */
public interface AuthService {

    SysUser register(SysUser sysUser);
    String login(String username, String password);
    String refresh(String oldToken);
}
