package com.project03.service;

import com.project03.dto.BoardRequestDto;
import com.project03.model.Board;
import com.project03.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;


    @Transactional
    public Long updateBoard(BoardRequestDto boardRequestDto, Long id) {

        Board board = boardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 아이디가 없습니다.")
        );

        board.updateBoard(boardRequestDto);
        return board.getId() ;
    }



}
