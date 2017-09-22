package com.dx.jwtsecurity.demo;

import com.dx.jwtsecurity.repository.SysUser;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 14:16
 */
@Data
public class Demo {

    @Id
    private String id;

    private String description;
    private boolean completed;
    private SysUser sysUser;
}
