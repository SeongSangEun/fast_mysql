package com.example.fastcampusmysql.domain.post.service;

import com.example.fastcampusmysql.domain.post.dto.PostCommand;
import com.example.fastcampusmysql.domain.post.entity.Post;
import com.example.fastcampusmysql.domain.post.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteService {
    private final PostRepository postRepository;

     public Long create(PostCommand postCommand) {
         var post = Post.builder()
                 .memberId(postCommand.getMemberId())
                 .contents(postCommand.getContents())
                 .build();
         return postRepository.save(post).getId();
     }
}
