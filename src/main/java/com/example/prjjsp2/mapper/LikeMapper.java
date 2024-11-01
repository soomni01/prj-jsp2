package com.example.prjjsp2.mapper;

import com.example.prjjsp2.dto.Like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LikeMapper {
    @Insert("""
            INSERT INTO likes(post_id, member_id)
            VALUES (#{postId}, #{memberId})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "likes.id")
    void addLike(Like likes, String postId, String memberId);

    @Select("""
                        SELECT l.id, l.post_id, l.member_id, b.inserted, b.title postTitle, b.writer postWriter
                        FROM likes l JOIN board b
                        ON l.post_id = b.id
                        WHERE l.member_id = #{id}
            """)
    List<Like> likesList(String id);
}
