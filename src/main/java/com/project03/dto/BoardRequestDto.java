package com.project03.dto;

import com.project03.model.Comment;
import com.project03.model.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class BoardRequestDto {
    private Member member;
    private String username;
    private String title;
    private String contents;
    private int count;
    private List<Comment> commentList;;

}
