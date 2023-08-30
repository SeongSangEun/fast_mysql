package com.example.fastcampusmysql.domain.member.dto;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberDto {

    private Long id;
    private String email;
    private String nickname;
    private LocalDate birthday;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickName();
        this.birthday = member.getBirthDay();
    }
}
