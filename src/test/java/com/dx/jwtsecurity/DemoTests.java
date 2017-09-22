package com.dx.jwtsecurity;

import com.dx.jwtsecurity.demo.Demo;
import com.dx.jwtsecurity.demo.DemoRepository;
import com.dx.jwtsecurity.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/12 12:27
 */
//@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest/*(classes = Application.class)*/
//@ContextConfiguration
//@DataJpaTest
public class DemoTests {

    @Autowired
    private DemoRepository demoRepository;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void test1() {

        Demo demo = new Demo();
        demo.setDescription("user and demo");
        demo.setCompleted(false);
        demo.setSysUser(sysUserRepository.findByUsername("test"));
        demoRepository.save(demo);

        List<Demo> list = demoRepository.findAll();

        System.out.println(list.size());

        list.forEach(i -> System.out.println(i.toString()));

    }
}
