package com.dx.jwtsecurity.security;

import com.dx.jwtsecurity.repository.SysUser;
import com.dx.jwtsecurity.security.JwtSysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 11:05
 */
public class JwtSysUserFactory {

    public JwtSysUserFactory() {
    }

    public static JwtSysUser create(SysUser sysUser) {
        return new JwtSysUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(),
                sysUser.getEmail(), mapToGrantedAuthorities(sysUser.getRoles()),
                sysUser.getLastPasswordResetDate());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
