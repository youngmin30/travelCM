package com.jym.travelCM.controller;

import com.jym.travelCM.domain.Member;
import com.jym.travelCM.dto.article.ArticleSaveForm;
import com.jym.travelCM.service.ArticleService;
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
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("/articles/write")
    public String showArticleWrite(Model model) {

        model.addAttribute("articleSaveForm", new ArticleSaveForm());

        return "usr/article/write";

    }

    @PostMapping("/articles/write")
    public String doWrite(@Validated ArticleSaveForm articleSaveForm, BindingResult bindingResult, Model model, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "usr/article/write";
        }

        try {

            Member findMember = memberService.findByLoginId(principal.getName());

            articleService.save(
                    articleSaveForm,
                    findMember
            );

        } catch (IllegalStateException e) {

            model.addAttribute("err_msg", e.getMessage());

            return "usr/article/write";

        }

        return "redirect:/";

    }

}

