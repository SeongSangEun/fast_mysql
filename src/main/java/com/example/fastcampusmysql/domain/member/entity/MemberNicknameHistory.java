package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "member_nickname_history")
@NoArgsConstructor
public class MemberNicknameHistory {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long id, Long memberId, String beforeNickname, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    public MemberNicknameHistory(Member member) {
        this.memberId = member.getId();
        this.nickname = member.getNickname();
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
