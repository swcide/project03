package com.project03.controller;

import com.project03.dto.MemberRequestDto;
import com.project03.model.Member;
import com.project03.repository.MemberRepository;
import com.project03.service.RegisterService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class RegisterRestController {
    private final RegisterService registerService;
    private final MemberRepository memberRepository;

    @RequestMapping("/member/signup")
    public ModelAndView signup() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home/member/signup");
        return mv;
    }
    
    // 회원 가입 요청 처리
    @PostMapping("/member/signup")
    public Member registerUser(@RequestBody MemberRequestDto requestDto) {
        Member member = new Member(requestDto);
        registerService.checkId(member);
        registerService.checkPassword(member);
        registerService.checkNameDuplication(member);

        return memberRepository.save(member);
    }


}
