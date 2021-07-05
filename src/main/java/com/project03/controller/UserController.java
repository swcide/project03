package com.project03.controller;

import com.project03.dto.SignupRequestDto;
import com.project03.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "home/user/signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        System.out.println(requestDto.getUsername()+"zzzzzz");
        System.out.println(requestDto.getEmail()+"zzzzzz");

        return "redirect:/";
    }


}
