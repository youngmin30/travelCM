package com.jym.travelCM.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String detail;

    private LocalDateTime regDate = LocalDateTime.now();
    private LocalDateTime updateDate = LocalDateTime.now();

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    public static Board createBoard(String name, String detail) {

        Board board = new Board();

        board.name = name;
        board.detail = detail;

        return board;

    }

}
