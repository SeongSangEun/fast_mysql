package com.example.fastcampusmysql.domain.post.dto;

import lombok.Getter;

@Getter
public class PostCommand {
    private Long memberId;
    private String contents;
}
