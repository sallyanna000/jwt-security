package com.dx.jwtsecurity.security;

import java.io.Serializable;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/19 12:44
 */
public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
