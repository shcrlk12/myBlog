package com.kjwon.myblog.admin.entity;


import com.kjwon.myblog.acrticle.entity.Article;
import com.kjwon.myblog.acrticle.entity.ArticleLike;
import com.kjwon.myblog.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String categoryName;
    String writer;
    String articlePath;

    int sortValue;
    boolean usingYn;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Course> courseList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    private List<Article> articleList = new ArrayList<>();

}
