package com.dx.jwtsecurity.demo;

import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/21 16:02
 */
public interface DemoService {

    Demo addDemo(Demo demo);
    Demo deleteDemo(String id);
    List<Demo> findAll();
//    List<Demo> findAll(String username);
    Demo findById(String id);
    Demo update(Demo demo);
}
