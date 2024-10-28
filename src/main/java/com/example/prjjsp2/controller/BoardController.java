package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Board;
import com.example.prjjsp2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping("new")
    public void newBoard() {

    }

    @PostMapping("new")
    public String newBoard(Board board,
                           RedirectAttributes rttr) {
        service.add(board);

        rttr.addFlashAttribute("message", Map.of("type", "success", "text", "새 게시물을 등록하였습니다."));

        return "redirect:/board/list";
    }

    @GetMapping("list")
    public void listBoard(Model model, Board board) {
        List<Board> list = service.list();

        model.addAttribute("boardList", list);

    }

    @GetMapping("view")
    public void viewBoard(Model model, Integer id) {
        Board board = service.get(id);
        model.addAttribute("board", board);
    }

    @PostMapping("delete")
    public String deleteBoard(Integer id, RedirectAttributes rttr) {
        service.remove(id);

        rttr.addFlashAttribute("message", Map.of("type", "success", "text", "게시물을 삭제했습니다."));
        rttr.addAttribute("id", id);
        return "redirect:/board/list";
    }

    @GetMapping("edit")
    public void editBoard(Model model, Integer id) {
    }
}
