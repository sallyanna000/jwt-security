package com.dx.jwtsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Description
 * @Project jwt-security
 * @Author duxiang
 * @Time 2017/9/11 20:21
 */
@SpringBootApplication
//@EnableJpaAuditing
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
