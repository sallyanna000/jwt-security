//package com.dx.jwtsecurity.repository;
//
//import lombok.Data;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @Description
// * @Project jwt-security
// * @Author duxiang
// * @Time 2017/9/11 20:27
// */
//@Data
//@Entity
//@Table(name = "jwt_user")
//@EntityListeners(AuditingEntityListener.class)
//public class User {
//
//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "age")
//    private Integer age;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "created_time")
//    @CreatedDate
//    private Date createdTime;
//
//    @Column(name = "last_modified_time")
//    @LastModifiedDate
//    private Date lastModifiedTime;
//
////    @Column(name = "roles")
////    private List<String> roles;
//
//}
