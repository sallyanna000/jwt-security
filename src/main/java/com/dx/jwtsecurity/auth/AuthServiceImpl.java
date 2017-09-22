package com.dx.jwtsecurity.auth;

import com.dx.jwtsecurity.auth.AuthService;
import com.dx.jwtsecurity.repository.SysUser;
import com.dx.jwtsecurity.repository.SysUserRepository;
import com.dx.jwtsecurity.security.JwtSysUser;
import com.dx.jwtsecurity.security.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static java.util.Arrays.asList;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/19 11:31
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtils jwtTokenUtils;
    private SysUserRepository sysUserRepository;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           JwtTokenUtils jwtTokenUtils,
                           SysUserRepository sysUserRepository) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtils = jwtTokenUtils;
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    public SysUser register(SysUser sysUser) {
        final String username = sysUser.getUsername();
        if (null != sysUserRepository.findByUsername(username)) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String password = sysUser.getPassword();
        sysUser.setPassword(encoder.encode(password));
        sysUser.setLastPasswordResetDate(new Date());
        sysUser.setRoles(asList("ROLE_USER"));
        return sysUserRepository.insert(sysUser);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtils.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtils.getUsernameFromToken(token);
        JwtSysUser user = (JwtSysUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            return jwtTokenUtils.refreshToken(token);
        }
        return null;
    }
}
