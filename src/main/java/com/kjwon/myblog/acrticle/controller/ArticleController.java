package com.kjwon.myblog.acrticle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @GetMapping("article/popular_article")
    public String popularArticle(){
        return "article/popular_article";
    }

    @GetMapping("article/article_rank")
    public String articleRank(){
        return "article/article_rank";
    }

    @GetMapping("article/free_article")
    public String freeArticle(){
        return "article/free_article";
    }

    @GetMapping("article/fun_article")
    public String funArticle(){
        return "article/fun_article";
    }

    @GetMapping("article/write_article")
    public String writeArticle(){
        return "article/write_article";
    }
}
