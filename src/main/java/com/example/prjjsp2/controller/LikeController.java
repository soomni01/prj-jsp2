package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Like;
import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikeController {

    private final LikeService service;

    @PostMapping("add")
    public String addLike(Like like,
                          @RequestParam(defaultValue = "") String postId,
                          @SessionAttribute(value = "loggedInMember") Member member,
                          RedirectAttributes rttr) {
        System.out.println(postId + "," + member.getId() + ",");
        service.addLike(like, postId, member.getId());
        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", postId + "번 게시물에 좋아요 버튼을 눌렀습니다."));
        return "redirect:/board/list";
    }

    @GetMapping("list")
    public String likesList(@SessionAttribute("loggedInMember") Member member,
                            Model model) {
        List<Like> likeList = service.getLikesList(member.getId());

        model.addAttribute("likeList", likeList);
        System.out.println(likeList);
        return null;
    }
}
