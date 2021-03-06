package com.jym.travelCM.dao;

import com.jym.travelCM.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

    boolean existByLoginId(String loginId);
    boolean existByNickname(String nickname);
    boolean existByEmail(String email);


    boolean existsByLoginId(String loginId);
    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);

}