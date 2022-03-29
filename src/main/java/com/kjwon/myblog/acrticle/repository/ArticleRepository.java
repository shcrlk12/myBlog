package com.kjwon.myblog.acrticle.repository;

import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query(value = "SELECT distinct a FROM Article a join fetch a.category where a.category.categoryName = :categoryName"
            , countQuery = "select count(a) from Article a where a.category.categoryName = :categoryName")
    Page<Article> findByArticleOnCategory(String categoryName, Pageable pageable);

    @Query("SELECT distinct a FROM Article a WHERE a.isPopularArticle = true")
    Page<Article> findByAllIsPopularArticle(Pageable pageable);

}
