package com.example.prjjsp2.mapper;

import com.example.prjjsp2.dto.Member;
import org.apache.ibatis.annotations.*;

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
            SELECT *
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

    @Update("""
            UPDATE member
            SET nick_name=#{nickName}, description=#{description}
            WHERE id=#{id};
            """)
    int updateMember(Member member);

    @Update("""
            UPDATE member
            SET password=#{newPassword}
            WHERE id=#{id}
            AND password=#{oldPassword}
            """)
    int updatePassword(String id, String oldPassword, String newPassword);

    @Select("""
            SELECT *
            FROM member
            WHERE id=#{id}
            AND password=#{password}
            """)
    Member selectByIdAndPassword(String id, String password);

    @Select("""
            SELECT name
            FROM auth
            WHERE id=#{id}
            """)
    List<String> selectAuthById(String id);
}
