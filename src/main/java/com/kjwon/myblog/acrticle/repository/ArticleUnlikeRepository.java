package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.acrticle.entity.ArticleUnlike;
import com.kjwon.myblog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleUnlikeRepository extends JpaRepository<ArticleUnlike, Long> {
    List<ArticleUnlike> findByArticleAndMember(Article article, Member member);

}
