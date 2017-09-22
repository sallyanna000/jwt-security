package com.dx.jwtsecurity.auth;

import com.dx.jwtsecurity.repository.SysUser;
import com.dx.jwtsecurity.security.JwtAuthenticationRequest;
import com.dx.jwtsecurity.security.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/19 11:45
 */
@RestController
public class AuthController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest)
            throws AuthenticationException {

        final String token = authService.login(authenticationRequest.getUsername(),
                authenticationRequest.getPassword());

        // return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(
            HttpServletRequest request)
            throws javax.security.sasl.AuthenticationException {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        if (null == refreshedToken) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        }
    }

    @RequestMapping(value = "${jwt.route.authentication.register}", method = RequestMethod.POST)
    public SysUser register(@RequestBody SysUser addedUser)
            throws org.springframework.security.core.AuthenticationException {
        return authService.register(addedUser);
    }
}
