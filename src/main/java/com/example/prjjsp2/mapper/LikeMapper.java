package com.example.prjjsp2.mapper;

import com.example.prjjsp2.dto.Like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface LikeMapper {
    @Insert("""
            INSERT INTO likes(post_id, member_id)
            VALUES (#{postId}, #{memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "likes.id")
    void addLike(Like likes, String postId, String memberId);
}
