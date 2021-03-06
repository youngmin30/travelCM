package com.jym.travelCM.controller;

import com.jym.travelCM.domain.Member;
import com.jym.travelCM.dto.member.MemberLoginForm;
import com.jym.travelCM.dto.member.MemberModifyForm;
import com.jym.travelCM.dto.member.MemberSaveForm;
import com.jym.travelCM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/join")
    public String showJoin(Model model) {

        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "usr/member/join";

    }

    @PostMapping("/members/join")
    public String doJoin(@Validated MemberSaveForm memberSaveForm, BindingResult bindingResult, Model model) {

        if ( bindingResult.hasErrors() ) {
            return "usr/member/join";
        }

        try {

            memberService.save(memberSaveForm);

        } catch ( Exception e ) {

            model.addAttribute("err_msg", e.getMessage());

            return "usr/member/join";

        }

        return "redirect:/";

    }

    @GetMapping("/members/login")
    public String showLogin(Model model){

        model.addAttribute("memberLoginForm", new MemberLoginForm());

        return "usr/member/login";
    }

    @GetMapping("/members/modify")
    public String showModify(Model model, Principal principal){

        Member findMember = memberService.findByLoginId(principal.getName());

        model.addAttribute("member", findMember);
        model.addAttribute("memberModifyForm", new MemberModifyForm());

        return "usr/member/modify";
    }

    @PostMapping("/members/modify")
    public String doModify(MemberModifyForm memberModifyForm, Principal principal, Model model){

        try {
            memberService.modifyMember(memberModifyForm, principal.getName());
        }catch (Exception e){
            model.addAttribute("err_msg", e.getMessage());
            return "usr/member/modify";
        }
        return "redirect:/";
    }

}