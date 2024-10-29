package com.example.prjjsp2.mapper;

import com.example.prjjsp2.dto.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member
            (id, password, nick_name, description)
            VALUES (#{id}, #{password}, #{nickName}, #{description})
            """)
    int addMember(Member member);

    @Select("""
            SELECT id, password, nick_name, description, inserted
            FROM member
            """)
    List<Member> memberList();

    @Select("""
            SELECT id, password, nick_name, description, inserted
            FROM member
            WHERE id=#{id};
            """)
    Member getMemberById(String id);

    @Delete("""
            DELETE FROM member
            WHERE id=#{id}
            AND password=#{password}
            """)
    int deleteMember(String id, String password);
}
