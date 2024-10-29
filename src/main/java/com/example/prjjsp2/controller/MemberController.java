package com.example.prjjsp2.controller;

import com.example.prjjsp2.dto.Member;
import com.example.prjjsp2.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
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
    public String list(Model model,
                       @SessionAttribute(value = "loggedInMember") Member member,
                       RedirectAttributes rttr) {
        if (member == null) {
            rttr.addFlashAttribute("message", Map.of("type", "warning",
                    "text", "권한이 없습니다."));
            return "redirect:/member/login";
        } else {
            model.addAttribute("memberList", service.memberList());
            return "member/list";
        }
    }

    @GetMapping("view")
    public void view(String id, Model model) {
        model.addAttribute("member", service.getMemberById(id));
    }

    @PostMapping("delete")
    public String delete(String id,
                         String password,
                         RedirectAttributes rttr,
                         HttpSession session,
                         @SessionAttribute("loggedInMember") Member member) {
        if (service.hasAccess(id, member)) {
            if (service.deleteMember(id, password)) {
                rttr.addFlashAttribute("message", Map.of("type", "success",
                        "text", "회원 탈퇴 성공하였습니다."));

                session.invalidate();
                return "redirect:/member/signup";
            } else {
                rttr.addFlashAttribute("message", Map.of("type", "warning",
                        "text", "패스워드가 일치하지 않습니다."));
                rttr.addAttribute("id", id);
                return "redirect:/member/view";
            }
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            session.invalidate();
            return "redirect:/member/login";
        }
    }

    @GetMapping("edit")
    public String edit(String id,
                       Model model,
                       RedirectAttributes rttr,
                       HttpSession session,
                       @SessionAttribute("loggedInMember") Member member) {
        if (service.hasAccess(id, member)) {
            model.addAttribute("member", service.getMemberById(id));
            return null;
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
//            TODO: redirect하면서 session 을 없애거나, 다른 화면으로 이동
            return "redirect:/member/login";
        }
    }

    @PostMapping("edit")
    public String edit(Member member,
                       RedirectAttributes rttr,
                       @SessionAttribute("loggedInMember") Member loggedInMember) {
        if (service.hasAccess(member.getId(), loggedInMember)) {
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
        } else {

            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            return "redirect:/member/login";
        }
    }

    @GetMapping("edit-password")
    public String editPassword(String id,
                               Model model,
                               @SessionAttribute("loggedInMember") Member member,
                               RedirectAttributes rttr) {
        if (service.hasAccess(id, member)) {
            model.addAttribute("id", id);
//JSP file [/WEB-INF/view/member/edit-password.jsp] not found
            return "/member/editPassword";

        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            return "redirect:/member/login";
        }
    }

    @PostMapping("edit-password")
    public String editPassword(String id,
                               String oldPassword,
                               String newPassword,
                               RedirectAttributes rttr,
                               @SessionAttribute("loggedInMember") Member member) {
        if (service.hasAccess(id, member)) {

            if (service.updatePassword(id, oldPassword, newPassword)) {
                rttr.addFlashAttribute("message", Map.of("type", "success",
                        "text", "암호가 변경되었습니다."));
                rttr.addAttribute("id", id);
                return "redirect:/member/view";

            } else {
                rttr.addFlashAttribute("message", Map.of("type", "warning",
                        "text", "암호가 올바르지 않습니다."));
                rttr.addAttribute("id", id);
                return "redirect:/member/edit-password";
            }
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "권한이 없습니다."));
            rttr.addAttribute("id", id);
            return "redirect:/member/edit-password";
        }
    }

    @GetMapping("login")
    public void login() {

    }

    @PostMapping("login")
    public String login(String id,
                        String password,
                        RedirectAttributes rttr,
                        HttpSession session) {
        Member member = service.login(id, password);
        if (member != null) {
            rttr.addFlashAttribute("message", Map.of("type", "success",
                    "text", "로그인에 성공했습니다."));
            session.setAttribute("loggedInMember", member);
            return "redirect:/board/list";
        } else {
            rttr.addFlashAttribute("message", Map.of("type", "danger",
                    "text", "일치하는 패스워드나 아이다가 없습니다."));
            return "redirect:/member/login";
        }
    }

    @PostMapping("logout")
    public String logout(HttpSession session, RedirectAttributes rttr) {
        session.invalidate();

        rttr.addFlashAttribute("message", Map.of("type", "success",
                "text", "로그아웃 되었습니다."));

        return "redirect:/member/login";
    }
}
