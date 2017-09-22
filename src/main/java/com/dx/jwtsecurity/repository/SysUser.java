package com.dx.jwtsecurity.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 10:47
 */
@Data
public class SysUser {

    @Id
    private String id;

    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;
    private String password;
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", roles=" + roles +
                '}';
    }
}
