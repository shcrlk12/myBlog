package com.kjwon.myblog.util;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleUtil {
    public static List<String> articleOverview(List<ArticleDto> articleDtoList, String articleType) {


        List<String> articleList = new ArrayList<>();

        for (ArticleDto articleDto : articleDtoList) {

            String sb = "<td>" +
                    "<a href = \"" +
                    articleDto.getId() +
                    "\">" +
                    articleDto.getTitle() +
                    "</a>" +
                    "</td>" +
                    "<td>" +
                    articleDto.getMember().getUserId() +
                    "</td>" +
                    "<td>" +
                    articleDto.getRegDt() +
                    "</td>";
            articleList.add(sb);
        }

        return articleList;
    }
}
