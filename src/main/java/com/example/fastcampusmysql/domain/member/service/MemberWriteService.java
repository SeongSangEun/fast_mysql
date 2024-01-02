package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repo.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repo.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberWriteService {
    private final MemberRepository memberRepository;
    private final MapperService mapperService;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public MemberDto register(MemberRegisterCommand command){

        /**
         * Goal : 회원정보를 등록한다.
         * 회원정보 : EMAIL, 닉네임, 생년월일
         * 닉네임은 10자를 넘길 수 없으며 수정이 가능하다.
         * val member = Member.of(memberRegisterCommand)
         * memberRepository.save()
         */

        Member member = Member.builder()
                .nickname(command.getNickName())
                .email(command.getEmail())
                .birthDay(command.getBirthDay())
                .build();
        Member saveMember = memberRepository.save(member);
        memberNicknameHistoryRepository.save(new MemberNicknameHistory(saveMember));

        return mapperService.toMemberDto(saveMember);
    }

    public void changeNickname(Long memberId, String nickname) {

        /*
            1. 회원 이름을 변경
            2. 변경 내역을 저장한다.
         */
        Member member = memberRepository.findById(memberId).orElseThrow();
        member.changeNickname(nickname);
        memberRepository.save(member);

        //TODO 변경 내역 히스토리를 저장한다.
        memberNicknameHistoryRepository.save(new MemberNicknameHistory(member));
    }


}
