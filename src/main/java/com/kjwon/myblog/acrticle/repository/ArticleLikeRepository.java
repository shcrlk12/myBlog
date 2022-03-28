package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
    List<ArticleLike> findByArticleAndMember(Article article, Member member);
}
