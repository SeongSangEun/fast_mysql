package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repo.MemberJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJpaService {
    private final MemberJPARepository memberJPARepository;

    public Member registerMember(MemberRegisterCommand command) {
        Member member = Member.builder()
                .nickName(command.getNickName())
                .email(command.getEmail())
                .birthDay(command.getBirthDay())
                .build();
        return memberJPARepository.save(member);
    }
}
