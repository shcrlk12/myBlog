package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;

public interface ArticleService {
    void registerArticle(ArticleDto articleDto, String name);
}
