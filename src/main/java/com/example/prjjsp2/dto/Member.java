package com.example.prjjsp2.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {
    private String id;
    private String password;
    private String nickName;
    private String description;
    private LocalDateTime inserted;
    // 권한 정보 조회
    private List<String> auth;

}
