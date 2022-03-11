package com.jym.travelCM.Config;

import com.jym.travelCM.dao.MemberRepository;
import com.jym.travelCM.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initAdmin();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;

        public void initAdmin() {

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            Member admin = Member.createMember(
                    "admin",
                    bCryptPasswordEncoder.encode("admin"),
                    "관리자",
                    "관리자",
                    "admin@admin.com",
                    Role.ADMIN
            );

            memberRepository.save(admin);

        }

    }

}