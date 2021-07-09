package com.project03.repository;

import com.project03.model.Board;

import com.project03.model.BoardResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
        Page<Board> findAllByOrderByCreatedAtDesc(Pageable pageable);



}
