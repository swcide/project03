package com.project03.dto;

import com.project03.model.Board;
import com.project03.model.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter
@Getter
public class CommentReqeustDto {

    private Board board;
    private Member member;
    private String username;
    private String contents;
}