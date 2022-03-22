package com.kjwon.myblog.acrticle.Dto;

import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.admin.dto.CategoryDto;
import com.kjwon.myblog.admin.entity.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String contents;
    private Long categoryId;
    private LocalDateTime regDt;

    public static List<ArticleDto> of (List<Article> article) {
        if (article != null) {
            List<ArticleDto> articleList = new ArrayList<>();
            for(Article x : article) {
                articleList.add(of(x));
            }
            return articleList;
        }

        return null;
    }

    public static ArticleDto of(Article article) {
        return ArticleDto.builder()
                .id(article.getArticleId())
                .title(article.getTitle())
                .contents(article.getContents())
                .categoryId(article.getCategory().getId())
                .regDt(article.getRegDt())
                .build();
    }
}

