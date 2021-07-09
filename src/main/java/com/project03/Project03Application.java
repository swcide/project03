package com.project03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
public class Project03Application {


    public static void main(String[] args) {


        SpringApplication.run(Project03Application.class, args);


    }
//    @PostConstruct
//    public void started(){
//        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
//    }

}
