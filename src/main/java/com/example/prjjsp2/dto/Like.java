package com.example.prjjsp2.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Like {
    private Integer id;
    private Integer postId;
    private String memberId;
    private LocalDateTime inserted;
}
