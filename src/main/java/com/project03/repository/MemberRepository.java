package com.project03.repository;

import com.project03.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findMemberByUserId(String userId);

    Member findByUsername(String username);
}
