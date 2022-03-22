package com.kjwon.myblog.acrticle.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleDto {
    private String title;
    private String contents;
    private Long categoryId;
}

