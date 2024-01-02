package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.DailyPostCount;
import com.example.fastcampusmysql.domain.post.dto.DailyPostCountRequest;
import com.example.fastcampusmysql.domain.post.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostReadService {
    private final PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {
        /**
         * 반환 값 -> 리스트 [작성일자, 작성회원, 작성 게시물 수]
         * 1. group by 를 이용함 ( select createDate, memberId, count(id) from post where memberId = "memberId and createDate between firstDate and lastDate group by createdDate, memberId
         */
        return postRepository.groupByCreatedDate(request);
    }
}
