package com.jym.travelCM.service;

import com.jym.travelCM.dao.ArticleRepository;
import com.jym.travelCM.domain.Article;
import com.jym.travelCM.domain.Member;
import com.jym.travelCM.dto.article.ArticleDTO;
import com.jym.travelCM.dto.article.ArticleModifyForm;
import com.jym.travelCM.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Transactional
    public void save(ArticleSaveForm articleSaveForm, Member member) {
        Article article = Article.createArticle(
                articleSaveForm.getTitle(),
                articleSaveForm.getBody()
        );
        article.setMember(member);
        articleRepository.save(article);
    }
    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }
    public Article getById(Long id){
        Optional<Article> articleOptional = findById(id);
        articleOptional.orElseThrow(
                () -> new
                        NoSuchElementException("해당 게시물은 존재하지 않습니다.")
        );

        return articleOptional.get();
    }

    @Transactional
    public void modifyArticle(ArticleModifyForm articleModifyForm, Long id){
        Article findArticle = getById(id);
        findArticle.modifyArticle(
                articleModifyForm.getTitle(),
                articleModifyForm.getBody()
        );
    }

    public List<ArticleDTO> getList() {

        List<Article> articleList = articleRepository.findAll();

        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article : articleList) {

            ArticleDTO articleDTO = new ArticleDTO(article);
            articleDTOList.add(articleDTO);
        }

        return articleDTOList;

    }
}