//package com.dx.jwtsecurity.web;
//
//import com.dx.jwtsecurity.repository.User;
//import com.dx.jwtsecurity.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * @Description
// * @Project jwt-security
// * @Author duxiang
// * @Time 2017/9/11 20:33
// */
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @RequestMapping("/list")
//    public List<User> getList() {
//        return userRepository.findAll();
//    }
//}
