package com.example.fastcampusmysql.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MemberRegisterCommand {

    private String email;
    private String nickName;
    private LocalDate birthDay;

    public MemberRegisterCommand(String email, String nickName, LocalDate birthDay) {
        this.email = email;
        this.nickName = nickName;
        this.birthDay = birthDay;
    }
}
