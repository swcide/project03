package com.project03.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
public class SaveComentDto {
    private String board;
    private String member;
    private String username;
    private String contents;
}
