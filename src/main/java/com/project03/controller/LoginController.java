package com.project03.controller;

import com.project03.model.Member;
import com.project03.repository.MemberRepository;
import com.project03.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RestController("/")
public class LoginController {
    private final LoginService loginService;
    final MemberRepository memberRepository;

    @GetMapping("/member/login/error")
    public ModelAndView loginError(ModelAndView mv) {
        mv.setViewName("/home/fragments/loginerror");

        return mv;
    }



    @GetMapping("/main")
    public ModelAndView login(HttpSession session, HttpServletRequest request, @AuthenticationPrincipal UserDetails userDetails, ModelAndView mv) {

        if(userDetails != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();
            userDetails = (UserDetails) principal;
            String userId = userDetails.getUsername();
            Member m = memberRepository.findMemberByUserId(userId);


            session.setAttribute("m2",m);
            mv.setViewName("home/main");
            return mv;

        }
        mv.setViewName("home/main");
        return mv;
    }
    /*
     * 카카오 토큰 유효성 검증
     */
    @GetMapping(value="/member/kakao/token")
    public int kakaoToken(@RequestParam(value = "token") String token, @RequestParam(value = "username") String username) {
        return loginService.getKakaoTokenCheck(token, username);
    }


}
