package com.project03.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project03.dto.CommentReqeustDto;
import com.project03.dto.SaveComentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "board_bid")
    private Board board;

    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;


    public Comment (CommentReqeustDto commentReqeustDto){
        this.board = commentReqeustDto.getBoard();
        this.username = commentReqeustDto.getUsername();
        this.contents = commentReqeustDto.getContents();
    }





    public Comment(SaveComentDto saveComentDto,  Member member,Board board) {
        this.username=saveComentDto.getUsername();
        this.contents = saveComentDto.getContents();
        this.member = member;
        this.board = board;



    }
}
