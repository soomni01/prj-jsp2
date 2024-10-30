package com.example.prjjsp2.service;

import com.example.prjjsp2.dto.Like;
import com.example.prjjsp2.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper mapper;

    public void addLike(Like like, String postId, String memberId) {
        mapper.addLike(like, postId, memberId);
    }
}
