package com.example.prjjsp2.mapper;

import com.example.prjjsp2.dto.Board;
import com.example.prjjsp2.dto.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board(title,content,writer)
            VALUES (#{board.title}, #{board.content}, #{member.id})
            """)
    // 아래 Options를 설정해야 글 쓰고 바로 border.id를 인식함
    @Options(useGeneratedKeys = true, keyProperty = "board.id")
    int insert(Board board, Member member);

    @Select("""
            <script>
                SELECT b.id,
                       b.title,
                       b.inserted,
                       m.nick_name writerNickName
                FROM board b JOIN member m
                    ON b.writer = m.id
                <trim prefix="WHERE" prefixOverrides="OR">
                    <if test="searchTarget == 'all' or searchTarget == 'title'">
                        title LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'content'">
                        OR content LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                    <if test="searchTarget == 'all' or searchTarget == 'writer'">
                        OR m.nick_name LIKE CONCAT('%', #{keyword}, '%')
                    </if>
                </trim>
                ORDER BY b.id DESC
                LIMIT #{offset}, 10
            </script>
            """)
    List<Board> selectAllPaging(Integer offset, String searchTarget, String keyword);


    @Select("""
            SELECT b.id,
                   b.title,
                   b.content,
                   b.inserted,
                   m.nick_name writerNickName
            FROM board b JOIN member m
            ON b.writer = m.id
            WHERE b.id = #{id}
            """)
    Board selectById(Integer id);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Update("""
            UPDATE board
            SET title=#{title},
                content=#{content}
            WHERE id = #{id}
            """)
    int update(Board board);

    @Select("""
            <script>
                  SELECT COUNT(b.id) 
                  FROM board b JOIN member m
                      ON b.writer = m.id
                  <trim prefix="WHERE" prefixOverrides="OR">
                      <if test="searchTarget == 'all' or searchTarget == 'title'">
                          title LIKE CONCAT('%', #{keyword}, '%')
                      </if>
                      <if test="searchTarget == 'all' or searchTarget == 'content'">
                          OR content LIKE CONCAT('%', #{keyword}, '%')
                      </if>
                      <if test="searchTarget == 'all' or searchTarget == 'writer'">
                          OR m.nick_name LIKE CONCAT('%', #{keyword}, '%')
                      </if>
                  </trim>
              </script>
            """)
    Integer countAll(String searchTarget, String keyword);
}
