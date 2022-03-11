package com.jym.travelCM.dto.article;

import com.jym.travelCM.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDTO {

    private Long id;

    private String title;
    private String body;

    private String authorName;

    private String boardName;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleDTO(Article article) {

        this.id = article.getId();

        this.title = article.getTitle();

        this.body = article.getBody();
        this.authorName = article.getMember().getNickname();

        this.boardName = article.getBoard().getName();

        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();

    }

}