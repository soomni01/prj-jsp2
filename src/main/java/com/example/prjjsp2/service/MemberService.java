package com.example.prjjsp2.service;

import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public void addMember(Member member) {
        mapper.addMember(member);
    }

    public List<Member> memberList() {
        return mapper.memberList();
    }

    public Member getMemberById(String id) {
        return mapper.getMemberById(id);
    }

    public boolean deleteMember(String id, String password) {
        int cnt = mapper.deleteMember(id, password);
        return cnt == 1;
    }

    public void updateMember(Member member) {
        mapper.updateMember(member);
    }

    public boolean updatePassword(String id, String oldPassword, String newPassword) {
        int cnt = mapper.updatePassword(id, oldPassword, newPassword);
        return cnt == 1;
    }

    public Member login(String id, String password) {
        Member member = mapper.selectByIdAndPassword(id, password);
       
        if (member == null) {
            return null;
        } else {
            return member;
        }
    }
}
