package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    void registerArticle(ArticleDto articleDto, String name);

    List<ArticleDto> frontList(String articleType);

    ArticleDto detail(Long id);
}
