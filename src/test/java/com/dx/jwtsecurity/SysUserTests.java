package com.dx.jwtsecurity;

import com.dx.jwtsecurity.repository.SysUser;
import com.dx.jwtsecurity.repository.SysUserRepository;
import com.dx.jwtsecurity.security.JwtSysUser;
import com.dx.jwtsecurity.security.JwtSysUserFactory;
import com.dx.jwtsecurity.security.JwtTokenUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/19 14:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SysUserTests {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Test
    public void test1() {
        SysUser sysUser = new SysUser();
        sysUser.setEmail("email@163.com");
        sysUser.setPassword("123456");
        sysUser.setUsername("sally");
        sysUser.setRoles(Arrays.asList("ROLE_ADMIN"));
        sysUser.setLastPasswordResetDate(new Date());
        sysUserRepository.save(sysUser);

        List<SysUser> list = sysUserRepository.findAll();
        System.out.println("size: " + list.size());

        list.forEach(i -> System.out.println(i.toString()));

    }

    @Test
    public void test2() {
//        UserDetails userDetails = userDetailsService.loadUserByUsername("sally");
        SysUser sysUser = sysUserRepository.findByUsername("sally");
        UserDetails userDetails = JwtSysUserFactory.create(sysUser);
        String token = jwtTokenUtils.generateToken(userDetails);
        System.out.println(token);
    }
}
