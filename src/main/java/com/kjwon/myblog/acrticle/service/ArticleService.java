package com.kjwon.myblog.acrticle.service;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import com.kjwon.myblog.acrticle.Dto.LikeUnlikeDto;
import com.kjwon.myblog.course.dto.CommentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {
    void registerArticle(ArticleDto articleDto, String name);

    Page<ArticleDto> frontList(int page, String articleType);

    ArticleDto detail(Long id);

    List<CommentDto> getComments(Long articleId);

    void registerComment(Long id, CommentDto commentDto, String name);

    void deleteComment(Long commentId);

    void articleLike(Long id, String likeUser);

    LikeUnlikeDto getLikeUnLike(Long id);

    void articleUnlike(Long id, String likeUser);

    void postPopularArticle(Long id);
}
