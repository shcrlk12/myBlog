package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLike, Long> {
}
