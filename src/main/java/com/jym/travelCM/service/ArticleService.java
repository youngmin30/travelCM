package com.jym.travelCM.service;

import com.jym.travelCM.dao.ArticleRepository;
import com.jym.travelCM.domain.Article;
import com.jym.travelCM.domain.Member;
import com.jym.travelCM.dto.article.ArticleSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
