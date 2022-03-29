package com.kjwon.myblog.util;

import com.kjwon.myblog.acrticle.Dto.ArticleDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleUtil {
    public static List<String> articleOverview(Page<ArticleDto> articleDtoList) {


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

    public static String pagingUtil(Page<ArticleDto> articleDtoList) {
        int max;

        String result = "<a href = \"?page=1\"><< </a>" +
                "<a href = \"?page=";

        result += Math.max(articleDtoList.getNumber() - 10, 1);
        result += "\">< </a>";

        max = Math.min(articleDtoList.getNumber() + 10, articleDtoList.getTotalPages());

        for(int i = articleDtoList.getNumber() / 10 + 1; i <= max; i++){
            result += "<a href = \"?page=" +
                    i +
                    "\">" +
                    i +
                    " </a>";
        }
        result +="<a href = \"?page=";
        result += Math.min(articleDtoList.getNumber() + 10, articleDtoList.getTotalPages());
        result += "\"> ></a><a href = \"?page=" +
        articleDtoList.getTotalPages() +
        "\"> >></a>";
        return result;

    }

    public static List<String> articleOverview(List<ArticleDto> articleDtoList) {


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

    public static String pagingUtil(List<ArticleDto> articleDtoList) {
        int max;

        String result = "<a href = \"?page=1\"><< </a>" +
                "<a href = \"?page=";
//
//        result += Math.max(articleDtoList.getNumber() - 10, 1);
//        result += "\">< </a>";
//
//        max = Math.min(articleDtoList.getNumber() + 10, articleDtoList.getTotalPages());
//
//        for(int i = articleDtoList.getNumber() / 10 + 1; i <= max; i++){
//            result += "<a href = \"?page=" +
//                    i +
//                    "\">" +
//                    i +
//                    " </a>";
//        }
//        result +="<a href = \"?page=";
//        result += Math.min(articleDtoList.getNumber() + 10, articleDtoList.getTotalPages());
//        result += "\"> ></a><a href = \"?page=" +
//                articleDtoList.getTotalPages() +
//                "\"> >></a>";
        return result;

    }
}
