package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    MemberDto toMemberDto(Member member) {
        return new MemberDto(member);
    }
}
