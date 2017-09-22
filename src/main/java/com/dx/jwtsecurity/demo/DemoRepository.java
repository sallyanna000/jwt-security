package com.dx.jwtsecurity.demo;

import com.dx.jwtsecurity.demo.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 14:18
 */
@Repository
public interface DemoRepository  extends MongoRepository<Demo, String> {

//    List<Demo> findByUsername(String username);
}
