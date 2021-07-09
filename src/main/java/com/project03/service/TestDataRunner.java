package com.project03.service;

import com.project03.dto.MemberRequestDto;
import com.project03.dto.SaveBoardDto;
import com.project03.model.Board;
import com.project03.model.Member;
import com.project03.repository.BoardRepository;
import com.project03.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


//@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        MemberRequestDto m = new MemberRequestDto();
        m.setEmail("user1@spartacodingclub.kr");
        m.setUsername("김진태");
        m.setPassword("1234");
        m.setPasswordConfirm("1234");
        m.setPasswordNen("1234");
        m.setUserId("admin2");


        Member testUser = new Member(m);
        testUser = memberRepository.save(testUser);


        Member testUser2 = new Member();
        testUser2.setUserId("admin1");
        testUser2.setEmail("zzzzzzz");
        testUser2.setPassword(passwordEncoder.encode("1234"));
        testUser2.setPasswordConfirm("1234");
        testUser2.setPasswordNen("1234");
        testUser2.setUsername("김진태");
        SaveBoardDto saveBoardDto = new SaveBoardDto();
        saveBoardDto.setContents("테스트");
        saveBoardDto.setTitle("테스트");
        saveBoardDto.setUsername("김진태");
        saveBoardDto.setUserId("admin2");

        Board b = new Board(saveBoardDto,testUser2);

        boardRepository.save(b);
    }
}