package com.project03.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardResponse extends Timestamped {

    private Long id;
    private String userId;
    private String username;
    private String title;
    private String contents;
    private int count;
    private List<Comment> commentList;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
