package com.project03.repository;



import com.project03.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
        @Query("SELECT c from Comment c where c.board.id=:id and c.id>0 order by  c.createdAt DESC ")
        Page<Comment> getCommentsOfPost(@Param("id") Long id,Pageable pageable);

}