package com.project03.model;


import com.project03.dto.BoardRequestDto;
import com.project03.dto.SaveBoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@ToString
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Board extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="member_userid")
    private Member member;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ColumnDefault("0")
    private int count;

    @ManyToMany
    private List<Comment> commentList;


    public Board(SaveBoardDto saveBoardDto, Member member){
        this.member = member;
        this.username = saveBoardDto.getUsername();
        this.title = saveBoardDto.getTitle();
        this.contents = saveBoardDto.getContents();
        this.count = 0;
    }

    public void updateBoard(BoardRequestDto boardRequestDto){
        this.title = boardRequestDto.getTitle();
        this.contents = boardRequestDto.getContents();
    }

    public void updateCount(){
        this.count +=1;
    }
}
