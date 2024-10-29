package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService service;

    @GetMapping("signup")
    public void signup() {

    }

    @PostMapping("signup")
    public String signup(Member member, RedirectAttributes rttr) {

        service.addMember(member);

        rttr.addFlashAttribute("message", Map.of("type", "success", "text", "회원가입에 성공했습니다."));
        return "redirect:/board/list";
    }

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("memberList", service.memberList());

    }

    @GetMapping("view")
    public void view(String id, Model model) {
        model.addAttribute("member", service.getMemberById(id));
    }

    @PostMapping("delete")
    public String delete(Member member, String id, String password, RedirectAttributes rttr) {
        if (service.deleteMember(id, password)) {
            rttr.addFlashAttribute("message", Map.of("type", "success", "text", "회원 탈퇴에 성공했습니다."));
            return "redirect:/member/signup";
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger", "text", "회원 탈퇴에 실패했습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/view";
        }
    }

    @GetMapping("edit")
    public void edit(Model model) {
        
    }
}
