package com.project03.controller;


import com.project03.dto.SaveComentDto;
import com.project03.model.Board;
import com.project03.model.Comment;
import com.project03.model.CommentResponse;
import com.project03.model.Member;
import com.project03.repository.BoardRepository;
import com.project03.repository.CommentRepository;

import com.project03.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class CommentController {
    private final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;
    private final CommentRepository commentRepository;


    @GetMapping("/comment/{id}")
    public Page<CommentResponse> getComment(@PathVariable Long id, @RequestParam("page") int page,
                                            @RequestParam("size") int size
                                    ){
        System.out.println("??");
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> commentList = commentRepository.getCommentsOfPost(id,pageable);
        Page<CommentResponse> pagelist = commentList.map(comment -> new CommentResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getMember().getUserId(),
                comment.getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        ));

        System.out.println(pagelist);
        return pagelist;
    }


    @PostMapping("/comment/write")
    public Comment createComment(@RequestBody SaveComentDto saveComentDto){

        System.out.println(saveComentDto.toString());
        String userId = saveComentDto.getMember();
        Member member = memberRepository.findMemberByUserId(userId);

        member.setUserId(userId);
        member.setEmail(member.getEmail());



        Long id = Long.valueOf(saveComentDto.getBoard());
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("에러입니다 ㅎㅎ"));

        Comment comment = new Comment(saveComentDto, member,board);
        return commentRepository.save(comment);
    }

}