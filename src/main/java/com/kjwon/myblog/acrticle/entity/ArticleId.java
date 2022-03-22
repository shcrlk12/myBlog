package com.kjwon.myblog.acrticle.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
public class ArticleId implements Serializable {

    @Column(name= "category_id")
    private Long categoryId;

    @Column(name= "article_id")
    private Long articleId;

}
