package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.service.MemberJpaService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import com.example.fastcampusmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberWriteService memberWriteService;
    private final MemberJpaService memberJpaService;
    private final MemberReadService memberReadService;

    @PostMapping("/member")
    public MemberDto registerMember(@RequestBody MemberRegisterCommand command) {
        return memberWriteService.register(command);
    }
    @PostMapping("/member/jpa")
    public MemberDto registerMemberJPA(@RequestBody MemberRegisterCommand command) {
        return memberJpaService.registerMember(command);
    }

    @PostMapping("/member/{id}")
    public MemberDto findMemberOne(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }
}
