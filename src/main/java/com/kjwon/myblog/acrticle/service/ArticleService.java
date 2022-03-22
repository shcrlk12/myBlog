package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.course.dto.CommentDto;

import java.util.List;

public interface ArticleService {
    void registerArticle(ArticleDto articleDto, String name);

    List<ArticleDto> frontList(String articleType);

    ArticleDto detail(Long id);

    List<CommentDto> getComments(Long articleId);

    void registerComment(Long id, CommentDto commentDto, String name);
}
