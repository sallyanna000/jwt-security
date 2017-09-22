package com.dx.jwtsecurity.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 11:12
 */
@Repository
public interface SysUserRepository extends MongoRepository<SysUser, String> {

    SysUser findByUsername(final String username);
}
