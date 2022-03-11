package com.jym.travelCM.dto.board;

import com.jym.travelCM.domain.Board;
import com.jym.travelCM.dto.article.ArticleListDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardDTO {

    private Long id;

    private String name;
    private String detail;

    private List<ArticleListDTO> articleListDTOList;

    private LocalDateTime regDate;
    private LocalDateTime updateDate;


    public BoardDTO(Board board, List<ArticleListDTO> articleListDTO){

        this.id = board.getId();
        this.name = board.getName();
        this.detail = board.getDetail();
        this.articleListDTOList = articleListDTO;
        this.regDate = board.getRegDate();
        this.updateDate = board.getUpdateDate();

    }
}
