package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.service.MemberJpaService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberJpaService memberJpaService;

    @PostMapping("/member")
    public Member registerMember(@RequestBody MemberRegisterCommand command) {
        return memberWriteService.create(command);
    }
    @PostMapping("/member/jpa")
    public Member registerMemberJPA(@RequestBody MemberRegisterCommand command) {
        return memberJpaService.registerMember(command);
    }
}
