package com.kjwon.myblog.util;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleUtil {
    public static List<String> articleOverview(List<ArticleDto> articleDtoList, String articleType) {


        List<String> articleList = new ArrayList<>();

        for (ArticleDto articleDto : articleDtoList) {

            String sb = "<a href = \"" +
                    articleType +
                    "/" +
                    articleDto.getId() +
                    "\">" +
                    articleDto.getTitle() +
                    "</a>" +
                    "<span>" +
                    articleDto.getRegDt() +
                    "</span>";

            articleList.add(sb);
        }

        return articleList;
    }
}
