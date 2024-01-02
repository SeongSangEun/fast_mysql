package com.example.fastcampusmysql.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DailyPostCountRequest {
    private Long memberId;
    private LocalDate firstDate;
    private LocalDate lastDate;
}
