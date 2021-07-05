package com.project03;

import com.project03.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaAuditing // 시간 자동 변경이 가능하도록 합니다.
public class Project03Application {

    public static void main(String[] args) {
        SpringApplication.run(Project03Application.class, args);


        ArrayList<User> user = new ArrayList<>();
        List<User> l = new List<>();
    }

}
