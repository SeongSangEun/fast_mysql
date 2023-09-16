package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberRegisterCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repo.MemberJPARepository;
import com.example.fastcampusmysql.domain.member.repo.MemberNicknameHistoryJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberJpaService {
    private final MemberJPARepository memberJPARepository;
    private final MemberNicknameHistoryJPARepository memberNicknameHistoryJPARepository;
    private final MapperService mapperService;

    public MemberDto registerMember(MemberRegisterCommand command) {
        Member member = Member.builder()
                .nickname(command.getNickName())
                .email(command.getEmail())
                .birthDay(command.getBirthDay())
                .build();
        Member saveMember = memberJPARepository.save(member);
        return mapperService.toMemberDto(saveMember);
    }

    public void changeNickname(Long memberId, String nickname) {
        Member member = memberJPARepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("찾을 수 없습니다.")
        );
        //더티체킹으로 업데이트
        member.changeNickname(nickname);
        memberNicknameHistoryJPARepository.save(new MemberNicknameHistory(member));
    }
}
