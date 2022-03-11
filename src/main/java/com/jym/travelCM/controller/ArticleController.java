package com.jym.travelCM.controller;

import com.jym.travelCM.domain.Article;
import com.jym.travelCM.domain.Board;
import com.jym.travelCM.domain.Member;
import com.jym.travelCM.dto.article.ArticleDTO;
import com.jym.travelCM.dto.article.ArticleModifyForm;
import com.jym.travelCM.dto.article.ArticleSaveForm;
import com.jym.travelCM.service.ArticleService;
import com.jym.travelCM.service.BoardService;
import com.jym.travelCM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final BoardService boardService;

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
            Board findBoard = boardService.getBoard(articleSaveForm.getBoard_id());
            Member findMember = memberService.findByLoginId(principal.getName());
            articleService.save(
                    articleSaveForm,
                    findMember,
                    findBoard
            );

        } catch (IllegalStateException e) {
            model.addAttribute("err_msg", e.getMessage());
            return "usr/article/write";
        }
        return "redirect:/";
    }
    @GetMapping("/articles/modify/{id}")
    public String showModify(@PathVariable(name = "id") Long id, Model model){
        try {
            Article article = articleService.getById(id);
            model.addAttribute("articleModifyForm", new ArticleModifyForm(
                    article.getTitle(),
                    article.getBody()
            ));
            return "usr/article/modify";
        }catch (Exception e){
            return "redirect:/";
        }
    }
    @PostMapping("/articles/modify/{id}")
    public String doModify(@PathVariable(name = "id") Long id, ArticleModifyForm articleModifyForm){
        try{
            articleService.modifyArticle(articleModifyForm, id);
            return "redirect:/article/"+ id;
        }catch (Exception e){
            return "usr/article/modify";
        }
    }

    @GetMapping("/articles/")
    public String showList(Model model) {

        List<ArticleDTO> articleList = articleService.getList();

        model.addAttribute("articleList", articleList);

        return "usr/article/list";

    }

}
