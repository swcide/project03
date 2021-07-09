package com.project03.dto;

import com.project03.model.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SaveBoardDto {

    private String userId;
    private String username;
    private String title;
    private String contents;
}
