package com.dx.jwtsecurity.demo;

import com.dx.jwtsecurity.demo.Demo;
import com.dx.jwtsecurity.demo.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/18 14:20
 */
@RestController
@RequestMapping("/demo")
@PreAuthorize("hasRole('USER')")
public class DemoController {

    @Resource
    private DemoService demoService;

    // 哦，这里是因为普通用户只能看到自己的，不能看到全部的demo列表
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Demo> getAll(@RequestHeader(value = "username") String username) {
//        return demoService.findAll(username);
//    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Demo> getAll() {
        return demoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Demo AddDemo(@RequestBody Demo demo) {
        return demoService.addDemo(demo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Demo getDemo(@PathVariable String id) {
        return demoService.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Demo update(@PathVariable String id, @RequestBody Demo demo) {
        demo.setId(id);
        return demoService.update(demo);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Demo delete(@PathVariable String id) {
        return demoService.deleteDemo(id);
    }
}
