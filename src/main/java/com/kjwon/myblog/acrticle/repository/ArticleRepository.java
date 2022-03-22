package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
