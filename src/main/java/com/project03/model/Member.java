package com.project03.model;

import com.project03.dto.MemberRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@ToString
@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Member extends Timestamped {

    public Member(String userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
    }
    public Member(String username, String password, String email,  Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }

    public Member(MemberRequestDto memberRequestDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //암호화

        this.userId = memberRequestDto.getUserId();
        this.username = memberRequestDto.getUsername();
        this.email = memberRequestDto.getEmail();
        this.password = encoder.encode(memberRequestDto.getPassword());
        this.passwordNen = memberRequestDto.getPasswordNen();
        this.passwordConfirm = memberRequestDto.getPasswordConfirm();

    }

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordNen;

    @Column(nullable = false)
    private String passwordConfirm;
    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private Long kakaoId;
}