package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
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
    public void edit(Member member, Model model) {
        model.addAttribute("member", service.getMemberById(member.getId()));
    }

    @PostMapping("edit")
    public String edit(Member member, RedirectAttributes rttr) {
        try {
            service.updateMember(member);
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "회원정보가 수정되었습니다."));

        } catch (DuplicateKeyException e) {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", STR."\{member.getNickName()}은 이미 사용중인 별명입니다."));

            rttr.addAttribute("id", member.getId());
            return "redirect:/member/edit";
        }

        rttr.addAttribute("id", member.getId());
        return "redirect:/member/view";
    }

    @GetMapping("edit-password")
    public String editPassword(String id, Model model) {
        model.addAttribute("id", id);
//JSP file [/WEB-INF/view/member/edit-password.jsp] not found
        return "/member/editPassword";
    }

    @PostMapping("edit-password")
    public String editPassword(String id, String oldPassword, String newPassword, RedirectAttributes rttr) {
        if (service.updatePassword(id, oldPassword, newPassword)) {
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "암호가 변경되었습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/view";

        } else {
            rttr.addFlashAttribute("message", Map.of("type", "warning",
                    "text", "암호가 변경되지 않았습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/edit-password";
        }
    }
}
