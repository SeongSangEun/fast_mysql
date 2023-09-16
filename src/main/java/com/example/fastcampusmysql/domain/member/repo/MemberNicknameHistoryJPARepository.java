package com.example.fastcampusmysql.domain.member.repo;

import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberNicknameHistoryJPARepository extends JpaRepository<MemberNicknameHistory, Long> {
}
