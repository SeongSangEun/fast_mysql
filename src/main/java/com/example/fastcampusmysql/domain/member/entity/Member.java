package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Member {
    private final Long id;
    private String nickName;
    private String email;
    private LocalDate birthDay;
    private final LocalDateTime createdAt;

    final private static Long NAME_MAX_LENGTH = 10L;

    @Builder
    public Member(Long id, String nickName, String email, LocalDate birthDay, LocalDateTime createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email);
        this.birthDay = Objects.requireNonNull(birthDay);

        validateNickName(nickName);
        this.nickName = Objects.requireNonNull(nickName);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    void validateNickName(String nickName) {
        Assert.isTrue(nickName.length() <= NAME_MAX_LENGTH, "닉네임 최대 길이를 초과 했습니다.");

    }
}
