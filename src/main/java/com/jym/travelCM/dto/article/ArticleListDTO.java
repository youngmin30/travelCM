package com.jym.travelCM.dto.article;

import com.jym.travelCM.domain.Article;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleListDTO {

    private Long id;

    private String title;
    private String nickname;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    public ArticleListDTO(Article article){

        this.id = article.getId();
        this.title = article.getTitle();
        this.nickname = article.getMember().getNickname();
        this.regDate = article.getRegDate();
        this.updateDate = article.getUpdateDate();

    }

}
