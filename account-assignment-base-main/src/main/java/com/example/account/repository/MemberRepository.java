package com.example.account.repository;

import com.example.account.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId); //userId와 일치하는 entity를 찾아서 반환
}
