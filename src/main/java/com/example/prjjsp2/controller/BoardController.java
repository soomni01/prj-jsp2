package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Board;
import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping("new")
    public String newBoard(@SessionAttribute(value = "loggedInMember", required = false) Member member,
                           RedirectAttributes rttr) {
        if (member == null) {
            rttr.addFlashAttribute("message", Map.of("type", "warning",
                    "text", "로그인한 회원만 글 작성이 가능합니다."));
            return "redirect:/member/login";
        } else {
            return "board/new";
        }
    }

    @PostMapping("new")
    public String newBoard(Board board,
                           RedirectAttributes rttr,
                           @SessionAttribute(value = "loggedInMember") Member member) {

        service.add(board, member);

        rttr.addFlashAttribute("message", Map.of("type", "success", "text", "새 게시물이 등록되었습니다."));
        rttr.addAttribute("id", board.getId());
        return "redirect:/board/view";
    }

    @GetMapping("list")
    public void listBoard(@RequestParam(name = "page", defaultValue = "1") Integer page,
                          Model model,
                          Board board) {
        Map<String, Object> result = service.list(page);
        model.addAllAttributes(result);

    }

    @GetMapping("view")
    public void viewBoard(Model model, Integer id) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id,
                              RedirectAttributes rttr,
                              @SessionAttribute(value = "loggedInMember") Member member) {
        try {
            service.remove(id, member);
            rttr.addFlashAttribute("message", Map.of("type", "success", "text", "게시물이 삭제되었습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/board/list";
        } catch (RuntimeException e) {
            rttr.addFlashAttribute("message",
                    Map.of("type", "danger",
                            "text", id + "번 게시물 삭제중 문제가 발생하였습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/board/view";
        }
    }

    @GetMapping("edit")
    public void editBoard(Model model,
                          Integer id) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("edit")
    public String editBoard(Board board,
                            RedirectAttributes rttr,
                            @SessionAttribute("loggedInMember") Member member) {
        try {
            service.update(board, member);

            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "게시물이 수정되었습니다."));
        } catch (RuntimeException e) {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", board.getId() + "번 게시물 수정중 오류가 발생했습니다."));
        }
        rttr.addAttribute("id", board.getId());
        return "redirect:/board/view";
    }
}
