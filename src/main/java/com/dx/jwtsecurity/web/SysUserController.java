package com.dx.jwtsecurity.web;

import com.dx.jwtsecurity.repository.SysUser;
import com.dx.jwtsecurity.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 14:12
 */
/**
 * 在 @PreAuthorize 中我们可以利用内建的 SPEL 表达式：比如 'hasRole()' 来决定哪些用户有权访问。
 * 需注意的一点是 hasRole 表达式认为每个角色名字前都有一个前缀 'ROLE_'。所以这里的 'ADMIN' 其实在
 * 数据库中存储的是 'ROLE_ADMIN' 。这个 @PreAuthorize 可以修饰Controller也可修饰Controller中的方法。
 **/
//@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping("/sys/users")
public class SysUserController {

    @Autowired
    private SysUserRepository sysUserRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<SysUser> getSysUsers() {
        return sysUserRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public SysUser addSysUser(@RequestBody SysUser sysUser) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        sysUser.setPassword(encoder.encode(sysUser.getPassword()));
        sysUser.setLastPasswordResetDate(new Date());
//        sysUser.setRoles(Arrays.asList("ROLE_USER"));
        return sysUserRepository.insert(sysUser);
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SysUser getSysUser(@PathVariable String id) {
        return sysUserRepository.findOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SysUser updateUser(@PathVariable String id, @RequestBody SysUser updatedUser) {
        updatedUser.setId(id);
        return sysUserRepository.save(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public SysUser removeUser(@PathVariable String id) {
        SysUser deletedUser = sysUserRepository.findOne(id);
        sysUserRepository.delete(id);
        return deletedUser;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public SysUser getUserByUsername(@RequestParam(value="username") String username) {
        return sysUserRepository.findByUsername(username);
    }
}
