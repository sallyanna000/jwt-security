package com.dx.jwtsecurity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/21 16:04
 */
@Service("demoService")
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Autowired
    DemoServiceImpl(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    @Override
    public Demo addDemo(Demo demo) {
        // insert和save 的区别？
        return demoRepository.insert(demo);
    }

    @Override
    public Demo deleteDemo(String id) {
        Demo demo = demoRepository.findOne(id);
        demoRepository.delete(id);
        return demo;
    }

//    @Override
//    public List<Demo> findAll(String username) {
//        return demoRepository.findByUsername(username);
//    }

    @Override
    public List<Demo> findAll() {
        return demoRepository.findAll();
    }

    @Override
    public Demo findById(String id) {
        return demoRepository.findOne(id);
    }

    @Override
    public Demo update(Demo demo) {
        return demoRepository.save(demo);
    }
}
