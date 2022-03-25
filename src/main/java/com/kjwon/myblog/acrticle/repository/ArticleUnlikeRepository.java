package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.acrticle.entity.ArticleUnlike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleUnlikeRepository extends JpaRepository<ArticleUnlike, Long> {
}
