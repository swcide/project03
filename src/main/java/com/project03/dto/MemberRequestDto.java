package com.project03.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberRequestDto {
    private String userId;
    private String username;
    private String email;
    private String password;
    private String passwordNen;
    private String passwordConfirm;
    private boolean admin = false;
    private String adminToken = "";
}
