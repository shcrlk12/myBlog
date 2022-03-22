package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT distinct a FROM Article a join fetch a.category where a.category.categoryName = :categoryName")
    public List<Article> findByArticleOnCategory(String categoryName);

}
