package com.project03.controller;

import com.project03.dto.BoardRequestDto;
import com.project03.dto.SaveBoardDto;
import com.project03.model.Board;
import com.project03.model.BoardResponse;
import com.project03.model.Member;
import com.project03.repository.BoardRepository;
import com.project03.repository.MemberRepository;
import com.project03.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    //보드페이지 이동
    @RequestMapping(value = "/board/view")
    public ModelAndView board() {
        return new ModelAndView("home/board/board");
    }

    // 게시글 불러오기
    @GetMapping("/board")
    public Page<BoardResponse> getBoard(@RequestParam("page") int page,
                                        @RequestParam("size") int size
                                        ) {
        page = page - 1;
        Pageable pageable = PageRequest.of(page, size);
        Page<Board> boardList = boardRepository.findAllByOrderByCreatedAtDesc(pageable);
        Page<BoardResponse> pagelist = boardList.map(board -> new BoardResponse(
                board.getId(),
                board.getMember().getUserId(),
                board.getUsername(),
                board.getTitle(),
                board.getContents(),
                board.getCount(),
                board.getCommentList(),
                board.getCreatedAt(),
                board.getModifiedAt()
        ) );
        return pagelist;
    }

    // 글쓰기 이동
    @RequestMapping(value = "/board/write/view")
    public ModelAndView getWrite() {
        return new ModelAndView("home/board/write");
    }

    // 게시판 글쓰기
    @PostMapping("/board/write")
    public Board CreateBoard(@RequestBody SaveBoardDto saveBoardDto) {
        String userId = saveBoardDto.getUserId();
        Member m = memberRepository.findMemberByUserId(userId);
        m.setUserId(userId);
        m.setEmail(m.getEmail());
        Board board = new Board(saveBoardDto,m);
        return boardRepository.save(board);
    }
    // 게시글 디테일
    @RequestMapping(value = "/board/view/detail", method = RequestMethod.GET)
    public ModelAndView detailView(ModelAndView mv) {
        mv.setViewName("home/board/detail");
        return mv;
    }
    // 게시글 디테일
    @GetMapping("/board/detail/{id}")
    public BoardResponse getDetail(@PathVariable Long id) {
        Board b = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("에러입니다 ㅎㅎ"));
        b.updateCount();
        boardRepository.save(b);
        BoardResponse boardResponse = new BoardResponse(
                b.getId(),
                b.getMember().getUserId(),
                b.getUsername(),
                b.getTitle(),
                b.getContents(),
                b.getCount(),
                b.getCommentList(),
                b.getCreatedAt(),
                b.getModifiedAt()
        );
        return boardResponse;
    }
    @RequestMapping(value = "/board/view/update", method = RequestMethod.GET)
    public ModelAndView updateView(ModelAndView mv) {
        mv.setViewName("home/board/update");
        return mv;
    }
    @PutMapping("/board/update/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto){

        return boardService.updateBoard(boardRequestDto,id);
    }
}
